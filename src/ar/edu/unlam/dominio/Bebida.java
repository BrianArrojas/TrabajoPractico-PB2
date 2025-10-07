package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Bebida extends Producto {

	private Double litro;
	private Boolean esAlcoholica;
	private Double impuestoAlcoholica = 0.05;
	private LocalDate fechaVencimiento;

	public Bebida(Integer id, String nombre, String marca, Double precioUnitario, Integer cantidadDiponible,
			Double litro, Boolean esAlcholica, LocalDate fechaVencimiento) {
		super(id, nombre, marca, precioUnitario, cantidadDiponible);
		this.litro = litro;
		this.esAlcoholica = esAlcholica;
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public Double obtenerPrecioFinal(Integer cantidad, LocalDate fechaVenta) {
		Double precioFinal = 0.0;
		if (cantidad <= getCantidadDisponible()) {
			if (esAlcoholica) {
				Double precio = getPrecioUnitario() * cantidad;
				 precioFinal = precio + precio * impuestoAlcoholica;

			} else {
				precioFinal= getPrecioUnitario() * cantidad;
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

	public Double getLitro() {
		return litro;
	}

	public Boolean getEsAlcoholica() {
		return esAlcoholica;
	}

	public Double getImpuestoAlcoholica() {
		return impuestoAlcoholica;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	
}
