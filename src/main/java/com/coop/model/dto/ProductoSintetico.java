package com.coop.model.dto;

import java.io.Serializable;

public class ProductoSintetico implements Serializable {

	private static final long serialVersionUID = 7240338664780599669L;

	private long id;
	private double precio;
	private String producto;
	private String rubro;

	public ProductoSintetico(long id, String producto, String rubro, double precio) {
		super();
		this.id = id;
		this.producto = producto;
		this.rubro = rubro;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public double getPrecio() {
		return precio;
	}

	public String getProducto() {
		return producto;
	}

	public String getRubro() {
		return rubro;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

}
