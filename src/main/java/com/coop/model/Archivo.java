package com.coop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "archivos")
public class Archivo implements Serializable {

	private static final long serialVersionUID = 470915192177123515L;
	@JsonIgnore
	@Lob
	private byte[] contenido;
	private Date fecha;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long length;
	@Column(length = 50)
	private String mime;

	@Column(length = 300, nullable = false)
	private String name;

	public byte[] getContenido() {
		return contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public long getId() {
		return id;
	}

	public long getLength() {
		return length;
	}

	public String getMime() {
		return mime;
	}

	public String getName() {
		return name;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public void setName(String name) {
		this.name = name;
	}

}
