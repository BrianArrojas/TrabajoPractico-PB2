package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alimento extends Producto{

	private Double iva = 1.21;
	private Double descuentoPorCantidad = 0.15;
	private LocalDate fechaVencimiento;

	public Alimento(Integer id, String nombre, String marca, Double precioUnitario, Integer cantidadDiponible,
			LocalDate fechaVencimiento) {
		super(id, nombre, marca, precioUnitario, cantidadDiponible);
		this.fechaVencimiento = fechaVencimiento;

	}

	@Override
	public Double obtenerPrecioFinal(Integer cantidad, LocalDate fechaVenta) {

		Double precioFinal = 0.0;

		if (cantidad <= getCantidadDisponible()) {
			if (cantidad >= 5) {
				Double precio = getPrecioUnitario() * cantidad;
				precioFinal = (precio - precio * descuentoPorCantidad) * iva;

			} else {
				precioFinal = (getPrecioUnitario() * cantidad) * iva;
			}

			DescuentoFechaVencimiento enumEncontrado = DescuentoFechaVencimiento
					.obtenerDescuentoCorrespondiente(obtenerDias(fechaVenta));

			if (enumEncontrado != DescuentoFechaVencimiento.NINGUNO) {
				precioFinal = precioFinal - precioFinal * enumEncontrado.getPorcentaje();
			}

		}
		return precioFinal;
	}

	public Integer obtenerDias(LocalDate fechaVenta) {

		Integer diasParaVencimiento = (int) ChronoUnit.DAYS.between(fechaVenta,this.fechaVencimiento );

		return diasParaVencimiento;

	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
