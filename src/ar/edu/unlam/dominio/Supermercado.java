package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Supermercado {
	
	private Set<Producto> stockDisponible;
	private Set<Cliente> clientesActivos;

	public Supermercado() {
		this.stockDisponible = new HashSet<>();
		this.clientesActivos = new HashSet<>();
	}

	public Double precioUnitarioProducto(Integer id) {
		for (Producto p : stockDisponible) {
			if (p.getId().equals(id)) {
				return p.getPrecioUnitario();
			}
		}
		return null;
	}
  public Boolean agregarProducto(Producto producto) {
		return stockDisponible.add(producto);
	}
  public Boolean agregarCliente(Cliente cliente) {
		return clientesActivos.add(cliente);
	}
  public Double precioFinalDeUnProducto(Integer cantidad, Integer id, Integer numeroCliente, LocalDate fechaVenta) {

		Cliente cliente = encontrarCliente(numeroCliente);
		Double precioFinal = null;
		boolean seEncontro = false;

		for (Producto producto : stockDisponible) {
			if (producto.getId().equals(id)) {
				precioFinal = producto.obtenerPrecioFinal(cantidad, fechaVenta);
				seEncontro = true;
				break;
			}
		}
    if (!seEncontro || cliente == null || precioFinal == null) {
			return null;
		}

		if (cliente.getTarjetaSocial()) {
			precioFinal -= precioFinal * cliente.getDescuentaPorTarjeta();
		}

		return precioFinal;
	}

	public Cliente encontrarCliente(Integer numeroCliente) {
		for (Cliente cliente : clientesActivos) {
			if (cliente.getNumeroCliente().equals(numeroCliente)) {
				return cliente;
			}
		}
		return null;
	}
  public ArrayList<Producto> productosConPrecioMenorA(Double precio) {
		ArrayList<Producto> lista = new ArrayList<>();

		for (Producto producto : this.stockDisponible) {
			if (producto.getPrecioUnitario() < precio) {
				lista.add(producto);
			}
		}

		return lista;
	}
}

