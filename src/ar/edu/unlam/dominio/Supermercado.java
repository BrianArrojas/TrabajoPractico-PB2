package ar.edu.unlam.dominio;

import java.util.ArrayList;

public class Supermercado {

		private ArrayList<Persona> personas;

		public Supermercado() {
			this.personas = new ArrayList<>();
		}

		public ArrayList<Persona> obtenerEmpleados() {

			return this.personas;
		}

		public Persona obtenerPersona(Integer dni) {
			Persona personaEncontrada = null;
			for (Persona persona : this.personas) {
				if (persona.getDni().equals(dni)) {
					personaEncontrada = persona;
				}
			}
			return personaEncontrada;
		}
		
		public Persona obtenerCajero(Integer dni) {
			Persona empleadoEncontrado = null;
			for (Persona empleado : this.personas) {
				if (empleado instanceof Cajero && empleado.getDni().equals(dni)) {
					empleadoEncontrado = empleado;
				}
			}
			return empleadoEncontrado;
		}

		public Boolean agregarCajero(Persona empleado) {
			Boolean seAgrego = false;

			if (obtenerCajero(empleado.getDni()) == null) {

				this.personas.add(empleado);
				seAgrego = true;
			}

			return seAgrego;

		}
	
}
