package com.coop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.coop.model.dto.ProductoSintetico;

@NamedNativeQueries({
		@NamedNativeQuery(name = "Sintetico", query = "SELECT p.id,p.descripcion,r.rubro,precio FROM productos p INNER JOIN rubros r ON p.id=r.id WHERE precio>? ORDER BY rubro", 
						resultSetMapping = "productoSinteticoMapper") })

@SqlResultSetMappings({ @SqlResultSetMapping(name = "productoSinteticoMapper", classes = {
		@ConstructorResult(targetClass = ProductoSintetico.class, columns = {
				@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "descripcion", type = String.class),
				@ColumnResult(name = "rubro", type = String.class),
				@ColumnResult(name = "precio", type = Double.class) }) }) })
@Entity
@Table(name = "Productos")
public class Producto implements Serializable {

	private static final long serialVersionUID = 6567964114155098009L;

	private Date fechaVencimiento;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double precio;
	@Column(length = 100, name = "Description")
	private String producto;
	@ManyToMany
	@JoinTable(name = "productos_proveedores", joinColumns = {
			@JoinColumn(name = "id_producto", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_proveedor", referencedColumnName = "id") })
	private List<Proveedor> proveedores;
	@ManyToOne
	@JoinColumn(name = "id_rubro")
	private Rubro rubro;

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public long getId() {
		return id;
	}

	public double getPrecio() {
		return precio;
	}

	// JPA Hace la Vista GORDA ¬
	@Transient
	public double getPrecioDoble() {
		return getPrecio() * 2;
	}

	public String getProducto() {
		return producto;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

}
