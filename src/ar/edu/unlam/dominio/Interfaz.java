package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.util.Scanner;

public class Interfaz {

    private static Scanner scanner = new Scanner(System.in);
    private static Supermercado supermercado = new Supermercado();
    private static int ultimoNumeroCliente = 1000;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuProductos();
                    break;
                case 2:
                    verPromociones();
                    break;
                case 3:
                    crearUsuario();
                    break;
                case 4:
                    ingresarTarjetaSocial();
                    break;
                case 5:
                    System.out.println("¡Gracias por usar el sistema del supermercado!");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== SUPERMERCADO GRUPO 7 ===");
        System.out.println("1 - Ver / Agregar / Eliminar productos");
        System.out.println("2 - Ver promociones por fecha de vencimiento");
        System.out.println("3 - Crear usuario");
        System.out.println("4 - Ingresar tarjeta social");
        System.out.println("5 - Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void menuProductos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1 - Ver productos");
            System.out.println("2 - Agregar producto");
            System.out.println("3 - Eliminar producto (por ID)");
            System.out.println("4 - Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verProductos();
                    break;
                case 2:
                    agregarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private static void verProductos() {
        System.out.println("\n--- STOCK DISPONIBLE ---");
        if (supermercado.productosConPrecioMenorA(Double.MAX_VALUE).isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        for (Producto p : supermercado.productosConPrecioMenorA(Double.MAX_VALUE)) {
            System.out.println("ID: " + p.getId() + " | " + p.getNombre() + " (" + p.getMarca() + ") - $" + p.getPrecioUnitario());
        }
    }

    private static void agregarProducto() {
        System.out.println("\nIngrese tipo de producto (1-Alimento, 2-Bebida, 3-Limpieza): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Precio unitario: ");
        Double precio = scanner.nextDouble();
        System.out.print("Cantidad disponible: ");
        Integer cantidad = scanner.nextInt();
        scanner.nextLine();

        Producto nuevo = null;

        switch (tipo) {
            case 1:
                System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
                LocalDate fechaVtoAlimento = LocalDate.parse(scanner.nextLine());
                nuevo = new Alimento((int) (Math.random() * 1000), nombre, marca, precio, cantidad, fechaVtoAlimento);
                break;
            case 2:
                System.out.print("Litros: ");
                Double litro = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("¿Es alcohólica? (Si/No): ");
                String respuestaAlcohol = scanner.nextLine().trim();
                Boolean alcoholica = respuestaAlcohol.equalsIgnoreCase("si");
                System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
                LocalDate fechaVtoBebida = LocalDate.parse(scanner.nextLine());
                nuevo = new Bebida((int) (Math.random() * 1000), nombre, marca, precio, cantidad, litro, alcoholica, fechaVtoBebida);
                break;
            case 3:
                System.out.print("¿Es tóxico? (Si/No): ");
                String respuestaToxico = scanner.nextLine().trim();
                Boolean toxico = respuestaToxico.equalsIgnoreCase("si");
                System.out.print("¿Es biodegradable? (Si/No): ");
                String respuestaBio = scanner.nextLine().trim();
                Boolean bio = respuestaBio.equalsIgnoreCase("si");
                nuevo = new Limpieza((int) (Math.random() * 1000), nombre, marca, precio, cantidad, toxico, bio);
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        supermercado.agregarProducto(nuevo);
        System.out.println("Producto agregado correctamente.");
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean eliminado = supermercado.productosConPrecioMenorA(Double.MAX_VALUE)
                .removeIf(p -> p.getId().equals(id));

        if (eliminado) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No se encontró un producto con ese ID.");
        }
    }

    private static void verPromociones() {
        System.out.println("\n--- PROMOCIONES POR FECHA ---");
        System.out.print("Ingrese días hasta el vencimiento: ");
        int dias = scanner.nextInt();
        scanner.nextLine();

        DescuentoFechaVencimiento d = DescuentoFechaVencimiento.obtenerDescuentoCorrespondiente(dias);
        System.out.println("Descuento correspondiente: " + (d.getPorcentaje() * 100) );
    }

    private static void crearUsuario() {
        System.out.print("\nIngrese su nombre: ");
        String nombre = scanner.nextLine();
        int numeroCliente = ++ultimoNumeroCliente;
        Cliente nuevo = new Cliente(nombre, numeroCliente, false);
        supermercado.agregarCliente(nuevo);
        System.out.println("Usuario creado con éxito. Su número de cliente es: " + numeroCliente);
    }

    private static void ingresarTarjetaSocial() {
        System.out.print("\nIngrese su número de cliente: ");
        int numeroCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = supermercado.encontrarCliente(numeroCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado. Primero cree su usuario.");
            return;
        }

        System.out.println("Cliente encontrado. Tarjeta social registrada con éxito.");
        Cliente clienteConTarjeta = new Cliente(cliente.getNumeroCliente().toString(), numeroCliente, true);
        supermercado.agregarCliente(clienteConTarjeta);
    }
}
