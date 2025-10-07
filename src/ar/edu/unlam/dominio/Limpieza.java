package ar.edu.unlam.dominio;

import java.time.LocalDate;

public class Limpieza extends Producto {

	private Boolean esToxico;
	private Boolean esBiodegradable;

	public Limpieza(Integer id, String nombre, String marca, Double precioUnitario, Integer cantidadDiponible,
			Boolean esToxico, Boolean esBiodegradable) {
		super(id, nombre, marca, precioUnitario, cantidadDiponible);
		this.esToxico = esToxico;
		this.esBiodegradable = esBiodegradable;
	}

	@Override
	public Double obtenerPrecioFinal(Integer cantidad, LocalDate fechaVenta) {
		Double precioBase = getPrecioUnitario() * cantidad;

		if (cantidad >= getCantidadDisponible()) {
			return null;
		}

		if (esBiodegradable) {
			precioBase = precioBase - precioBase * 0.10;

		}

		if (esToxico) {
			precioBase = precioBase + precioBase * 0.05;

		}
		return precioBase;

	}
}

