package ar.edu.unlam.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Cliente {

	private String nombre;
	private Integer numeroCliente;
	private Boolean tarjetaSocial;
	private Double descuentaPorTarjeta = 0.10;
	private List<Producto> carrito;
	
	public Cliente(String nombre, Integer numeroCliente, Boolean tarjetaSocial) {
		this.nombre = nombre;
		this.numeroCliente = numeroCliente;
		this.tarjetaSocial = tarjetaSocial;
		this.carrito = new ArrayList<>();
	}

	public Integer getNumeroCliente() {
		return numeroCliente;
	}

	public Boolean getTarjetaSocial() {
		return tarjetaSocial;
	}

	public Double getDescuentaPorTarjeta() {
		return descuentaPorTarjeta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(numeroCliente, other.numeroCliente);
	}

	public List<Producto> getCarrito() {
		return carrito;
	}
	
	
}
