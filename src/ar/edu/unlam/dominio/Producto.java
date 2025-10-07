package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.util.Objects;


public abstract class Producto {

	private Integer id;
	private String nombre;
	private String marca;
	private Double precioUnitario;
	private Integer cantidadDisponible;

	public Producto(Integer id, String nombre, String marca, Double precioUnitario, Integer cantidadDiponible) {
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
		this.precioUnitario = precioUnitario;
		this.cantidadDisponible = cantidadDiponible;

	}

	// Metodo para obtener el valor

	public abstract Double obtenerPrecioFinal(Integer Cantidad, LocalDate fechaVenta);

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}

	// Saber el precio unitario de un producto especifico
	public Integer getId() {
		return id;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public Integer getCantidadDisponible() {
		return cantidadDisponible;
	}

	public String getNombre() {
		return nombre;
	}

	public String getMarca() {
		return marca;
	}
	
	
}
