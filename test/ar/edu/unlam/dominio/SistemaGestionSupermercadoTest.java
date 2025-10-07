package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class SistemaGestionSupermercadoTest {

	
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
}
