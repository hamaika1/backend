package com.coop.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.coop.model.Producto;
import com.coop.model.dto.ProductoSintetico;
import com.coop.model.persistence.ProductoRepository;

@Service
public class ProductoBusiness implements IProductoBusiness {

	@Autowired
	private ProductoRepository productoDAO;

	@Override
	public Producto load(long id) throws BusinessException, NotFoundException {
		Optional<Producto> o;

		try {
			o = productoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (o.isPresent()) {
			return o.get();
		} else
			throw new NotFoundException();

	}

	@Override
	public Producto add(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(long id) throws BusinessException {
		try {
			productoDAO.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Producto update(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> filterList(String partName, double precioDesde, double precioHasta) throws BusinessException {
		try {
			if (partName != null && partName.trim().length() > 2) {
				return productoDAO.findByProductoLike("%" + partName + "%");
			} else if (precioDesde <= precioHasta && precioDesde > 0) {
				return productoDAO.findByPrecioBetween(precioDesde, precioHasta);
			} else {
				throw new BusinessException("Parametros Incorrectos");
			}
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<ProductoSintetico> listadoSintetico(double precioMinimo) throws BusinessException {
		try {
			return productoDAO.listadoSintetico(precioMinimo);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public long cantidadProductosMasCarosQue(double precioMinimo) throws BusinessException {
		try {
			return productoDAO.cantidadProductosMasCarosQue(precioMinimo);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public int updatePrecio(double precio, long id) throws BusinessException {
		try {
			return productoDAO.updatePrecio(precio, id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listPageable(int pagina, int tamanio) throws BusinessException {
		try {
			return productoDAO.findAll(PageRequest.of(pagina, tamanio)).getContent();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listSortable() throws BusinessException {
		try {
			return productoDAO.findAll(Sort.by("producto").ascending().and(Sort.by("precio").descending()));
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
