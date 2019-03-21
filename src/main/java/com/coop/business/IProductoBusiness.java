package com.coop.business;

import java.util.List;



import com.coop.model.Producto;
import com.coop.model.dto.ProductoSintetico;

public interface IProductoBusiness {

	public Producto load(long id) throws BusinessException, NotFoundException;

	public Producto add(Producto producto) throws BusinessException;

	public void delete(long id) throws BusinessException;

	public Producto update(Producto producto) throws BusinessException;

	public List<Producto> list() throws BusinessException;

	public List<Producto> filterList(String partName, double precioDesde, double precioHasta) throws BusinessException;

	public List<ProductoSintetico> listadoSintetico(double precioMinimo) throws BusinessException;

	public long cantidadProductosMasCarosQue(double precioMinimo) throws BusinessException;

}
