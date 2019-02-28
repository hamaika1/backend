package com.coop.business;

import java.util.List;

import com.coop.model.Producto;

public interface IProductoBusiness {

	public Producto load(long id) throws BusinessException, NotFoundException;

	public Producto add(Producto producto) throws BusinessException;

	public void delete(long id) throws BusinessException;

	public Producto update(Producto producto) throws BusinessException;

	public List<Producto> list() throws BusinessException;
}
