package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class SistemaGestionSupermercadoTest {

	@Test
	public void agregarAlimentoAlSupermercadoDevuelveTrue() {

		Supermercado supermercado = new Supermercado();

		Integer id = 1;
		String nombre = "medallonCarne";
		String marca = "Paty";
		Double precioUnitario = 15.0;
		Integer cantidadDisponible = 20;
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto medallonCarne = new Alimento(id, nombre, marca, precioUnitario, cantidadDisponible, fechaVenta);

		Boolean seAgrego = supermercado.agregarProducto(medallonCarne);

		assertTrue(seAgrego);
	}

	@Test
	public void agregarAlimentoDuplicadoAlSupermercadoDevuelveFalse() {

		Supermercado supermercado = new Supermercado();

		Integer id = 1;
		String nombre = "medallonCarne";
		String marca = "Paty";
		Double precioUnitario = 15.0;
		Integer cantidadDisponible = 20;
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto medallonCarne = new Alimento(id, nombre, marca, precioUnitario, cantidadDisponible, fechaVenta);

		Producto medallonCarne2 = new Alimento(id, nombre, marca, precioUnitario, cantidadDisponible, fechaVenta);

		supermercado.agregarProducto(medallonCarne);

		Boolean seAgrego2 = supermercado.agregarProducto(medallonCarne2);

		assertFalse(seAgrego2);
	}

	@Test
	public void conseguirPrecioUnitarioDeUnProductoEspecifico() {

		Supermercado supermercado = new Supermercado();

		Integer id = 1;
		String nombre = "medallonCarne";
		String marca = "Paty";
		Double precioUnitario = 15.0;
		Integer cantidadDisponible = 20;
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto medallonCarne = new Alimento(id, nombre, marca, precioUnitario, cantidadDisponible, fechaVenta);

		Integer id2 = 2;
		String nombre2 = "Gaseosa Coca-Cola Original";
		String marca2 = "Coca-Cola";
		Double precioUnitario2 = 20.0;
		Integer cantidadDisponible2 = 10;
		Double litro = 2.0;
		Boolean esAlcoholica = false;

		Producto cocaCola = new Bebida(id2, nombre2, marca2, precioUnitario2, cantidadDisponible2, litro, esAlcoholica,
				fechaVenta);

		Integer id3 = 3;
		String nombre3 = "Lavandina";
		String marca3 = "Ayudin";
		Double precioUnitario3 = 75.0;
		Integer cantidadDisponible3 = 8;
		Boolean esToxico = true;
		Boolean esBiodegradable = false;

		Producto lavandina = new Limpieza(id3, nombre3, marca3, precioUnitario3, cantidadDisponible3, esToxico,
				esBiodegradable);

		supermercado.agregarProducto(medallonCarne);
		supermercado.agregarProducto(cocaCola);
		supermercado.agregarProducto(lavandina);

		Double valorEsperado = 20.0;
		Double valor = supermercado.precioUnitarioProducto(2);

		assertEquals(valorEsperado, valor);
	}

	@Test
	public void conseguirPrecioFinalDeUnAlimentoConDescuentoPorCantidad() {

		LocalDate fechaVencimiento = LocalDate.of(2025, 12, 2);

		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto pan = new Alimento(1, "Pan", "Bimbo", 15.0, 10, fechaVencimiento);

		Producto agua = new Bebida(2, "Agua", "Villavicencio", 20.0, 15, 1.0, false, fechaVencimiento);

		Producto jabon = new Limpieza(3, "Jabón", "Ala", 5.0, 12, true, false);

		Cliente cliente = new Cliente("juan", 12, false);

		Supermercado supermercado = new Supermercado();

		supermercado.agregarProducto(jabon);
		supermercado.agregarProducto(agua);
		supermercado.agregarProducto(pan);
		supermercado.agregarCliente(cliente);

		Integer cantidadRequerida = 5;
		Integer productoElegido = 1;
		Double precioEsperado = 77.1375;
		Double precio = supermercado.precioFinalDeUnProducto(cantidadRequerida, productoElegido, 12, fechaVenta);

		assertEquals(precioEsperado, precio, 0.0001);

	}

	@Test
	public void conseguirPrecioFinalDeUnaBebidaConAumentoPorAlcoholica() {
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);
		LocalDate fechaVencimiento = LocalDate.of(2025, 4, 3);

		Producto pan = new Alimento(1, "Pan", "Bimbo", 15.0, 10, fechaVencimiento);

		Producto cerveza = new Bebida(2, "Cerveza Rubia Clásica", "Quilmes", 50.0, 15, 1.5, true, fechaVencimiento);

		Producto jabon = new Limpieza(3, "Jabón", "Ala", 5.0, 12, true, false);

		Cliente cliente = new Cliente("juan", 12, false);

		Supermercado supermercado = new Supermercado();

		supermercado.agregarProducto(jabon);
		supermercado.agregarProducto(cerveza);
		supermercado.agregarProducto(pan);
		supermercado.agregarCliente(cliente);

		Integer cantidadRequerida = 3;
		Integer productoElegido = 2;
		Double precioEsperado = 78.75;
		Double precio = supermercado.precioFinalDeUnProducto(cantidadRequerida, productoElegido, 12, fechaVenta);

		assertEquals(precioEsperado, precio, 0.001);

	}

	@Test
	public void conseguirPrecioFinalDeUnaBebidaConDescuentoyAumentoPortoxicayBiodegradable() {
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto pan = new Alimento(1, "Pan", "Bimbo", 15.0, 10, fechaVenta);

		Producto cerveza = new Bebida(2, "Cerveza Rubia Clásica", "Quilmes", 50.0, 15, 1.5, true, fechaVenta);

		Producto lavandina = new Limpieza(3, "Lavandina Concentrada", "Ayudin", 500.0, 12, true, true);

		Cliente cliente = new Cliente("juan", 12, false);

		Supermercado supermercado = new Supermercado();

		supermercado.agregarProducto(lavandina);
		supermercado.agregarProducto(cerveza);
		supermercado.agregarProducto(pan);
		supermercado.agregarCliente(cliente);

		Integer cantidadRequerida = 2;
		Integer productoElegido = 3;
		Double precioEsperado = 945.00;
		Double precio = supermercado.precioFinalDeUnProducto(cantidadRequerida, productoElegido, 12, fechaVenta);

		assertEquals(precioEsperado, precio, 0.001);

	}

	@Test
	public void conseguirPrecioFinalDeUnAlimentoATresDiasDeVencer() {
		LocalDate fechaVencimiento = LocalDate.of(2025, 4, 5);
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto pan = new Alimento(1, "Pan", "Bimbo", 15.0, 10, fechaVencimiento);

		Cliente cliente = new Cliente("alex", 12, false);

		Supermercado supermercado = new Supermercado();
		supermercado.agregarProducto(pan);
		supermercado.agregarCliente(cliente);

		Double precioEsperado = 18.15;
		Double precio = supermercado.precioFinalDeUnProducto(2, 1, 12, fechaVenta);

		assertEquals(precioEsperado, precio, 0.001);

	}

	@Test
	public void conseguirPrecioFinalDeUnAlimentoASieteDiasDeVencer() {
		LocalDate fechaVencimiento = LocalDate.of(2025, 4, 9);
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto pan = new Alimento(1, "Pan", "Bimbo", 15.0, 10, fechaVencimiento);

		Cliente cliente = new Cliente("alex", 12, false);

		Supermercado supermercado = new Supermercado();
		supermercado.agregarProducto(pan);
		supermercado.agregarCliente(cliente);

		Double precioEsperado = 25.41;
		Double precio = supermercado.precioFinalDeUnProducto(2, 1, 12, fechaVenta);

		assertEquals(precioEsperado, precio, 0.001);

	}

	@Test
	public void conseguirPrecioFinalDeUnaBebidaA14DiasDeVencer() {
		LocalDate fechaVencimiento = LocalDate.of(2025, 4, 16);
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto cerveza = new Bebida(2, "Cerveza Rubia Clásica", "Quilmes", 50.0, 15, 1.5, true, fechaVencimiento);

		Cliente cliente = new Cliente("alex", 12, false);

		Supermercado supermercado = new Supermercado();
		supermercado.agregarProducto(cerveza);
		supermercado.agregarCliente(cliente);

		Double precioEsperado = 89.25;
		Double precio = supermercado.precioFinalDeUnProducto(2, 2, 12, fechaVenta);

		assertEquals(precioEsperado, precio, 0.001);

	}
	@Test
	public void conseguirPrecioFinalDeUnClienteConTarjetaSocial() {
		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto cerveza = new Bebida(2, "Cerveza Rubia Clásica", "Quilmes", 50.0, 15, 1.5, true, fechaVenta);
		Cliente cliente = new Cliente("alex", 12, true);

		Supermercado supermercado = new Supermercado();
		supermercado.agregarProducto(cerveza);
		supermercado.agregarCliente(cliente);

		Double precioEsperado = 47.25;
		Double precio = supermercado.precioFinalDeUnProducto(2, 2, 12, fechaVenta);

		assertEquals(precioEsperado, precio, 0.001);
	}
	@Test
	public void dadoQueExisteUnSupermercadoConProductosPodremosObtenerTodosLosProductosEnElStockConPrecioMenorA200() {

		LocalDate fechaVenta = LocalDate.of(2025, 4, 2);

		Producto pan = new Alimento(1, "Pan", "Bimbo", 15.0, 10, fechaVenta);

		Producto cerveza = new Bebida(2, "Cerveza Rubia Clásica", "Quilmes", 50.0, 15, 1.5, true, fechaVenta);

		Producto lavandina = new Limpieza(3, "Lavandina Concentrada", "Ayudin", 500.0, 12, true, true);

		Cliente cliente = new Cliente("juan", 12, false);
		Supermercado supermercado = new Supermercado();

		supermercado.agregarProducto(lavandina);
		supermercado.agregarProducto(cerveza);
		supermercado.agregarProducto(pan);
		supermercado.agregarCliente(cliente);

		List<Producto> listaFiltrada = supermercado.productosConPrecioMenorA(200.0);

		Integer productosEsperado = 2;
		Integer lista = listaFiltrada.size();

		assertEquals(productosEsperado, lista);
	}

}
