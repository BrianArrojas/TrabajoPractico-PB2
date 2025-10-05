package ar.edu.unlam.dominio;

public class Cajero extends Persona{

	private Integer legajo;

	public Cajero(String nombre, String apellido, Integer dni, Integer legajo) {
		super(nombre, apellido, dni);
		this.legajo = legajo;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}
	
	
}
