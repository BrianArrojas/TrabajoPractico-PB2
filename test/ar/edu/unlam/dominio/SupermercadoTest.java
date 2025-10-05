package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SupermercadoTest {

	@Test
	public void dadoQueExisteUnSupermercadoAlConsultarSusEmpleadosObtengoLosMismos() {

		Supermercado dia = new Supermercado();
		String nombre = "Lucas";
		String apellido = "Gomez";
		Integer dni = 123456;
		Integer legajo = 123;

		Cajero cajero = new Cajero(nombre, apellido, dni, legajo);

		Boolean seAgrego = dia.agregarCajero(cajero);
		assertTrue(seAgrego);

		Integer empleadosEsperados = 1;
		Integer empleadosObtenidos = dia.obtenerEmpleados().size();

		assertEquals(empleadosEsperados, empleadosObtenidos);

	}

	@Test
	public void dadoQueExisteUnSupermercadoYUnCajeroAlBuscarloEnLaListaDePersonasObtendoTrue() {

		Supermercado dia = new Supermercado();
		String nombre = "Lucas";
		String apellido = "Gomez";
		Integer dni = 123456;
		Integer legajo = 123;

		Cajero cajero = new Cajero(nombre, apellido, dni, legajo);

		Boolean seAgrego = dia.agregarCajero(cajero);
		assertTrue(seAgrego);

		Persona canjeroEncontrado = dia.obtenerCajero(dni);

		assertEquals(cajero, canjeroEncontrado);
	}

}
