package com.coop.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coop.business.BusinessException;
import com.coop.business.IProductoBusiness;
import com.coop.business.NotFoundException;
import com.coop.model.Producto;
import com.coop.model.dto.ProductoSintetico;

@RestController
@RequestMapping(Constantes.URL_PRODCUTOS)
public class ProductoRestService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IProductoBusiness productoBusiness;

	@GetMapping("")
	public ResponseEntity<List<Producto>> list(
			@RequestParam(required = false, defaultValue = "@*@", value = "q") String partName,
			@RequestParam(required = false, defaultValue = "-1", value = "pdesde") double precioDesde,
			@RequestParam(required = false, defaultValue = "-1", value = "phasta") double precioHasta) {
		try {
			List<Producto> lista;
			if (!partName.equals("@*@")) {
				lista = productoBusiness.filterList(partName, 0, 0);
			} else if (precioDesde != -1 && precioHasta != -1) {
				lista = productoBusiness.filterList(null, precioDesde, precioHasta);
			} else {
				lista = productoBusiness.list();
			}
			return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> load(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Producto> add(@RequestBody Producto producto) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.add(producto), HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public ResponseEntity<Producto> update(@RequestBody Producto producto) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.update(producto), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			productoBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * GET /poductos <- Obtener la lista de productos GET /productos/{id} <- Obtener
	 * un producto cuyo id sea {id} POST /productos <- Agregar un nuveo producto (va
	 * en el body) PUT /productos <- Modificar un producto existente(va en el body)
	 * DELETE /productos/{id} <- Elimina un producto cuyo id sea {id}
	 */
	@GetMapping("/sintetico")
	public ResponseEntity<List<ProductoSintetico>> listadoSintetico(
			@RequestParam(value = "precio_minimo") double precioMinimo) {
		try {
			return new ResponseEntity<List<ProductoSintetico>>(productoBusiness.listadoSintetico(precioMinimo),
					HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<ProductoSintetico>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cantidad_mas_caros_que")
	public ResponseEntity<Long> cantidadProductosMasCarosQue(
			@RequestParam(value = "precio_minimo") double precioMinimo) {
		try {
			return new ResponseEntity<Long>(productoBusiness.cantidadProductosMasCarosQue(precioMinimo), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}/precio")
	public ResponseEntity<String> updatePrecio(@RequestParam(value = "precio") double precio,
			@PathVariable("id") long id) {
		try {
			productoBusiness.updatePrecio(precio, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pageable")
	public ResponseEntity<List<Producto>> listPageable(@RequestParam(value = "pagina") int pagina,
			@RequestParam(value = "tamanio") int tamanio) {
		log.info("Se esta ejecutando el proceso");
		log.debug("pagina={},tamanio={}", pagina, tamanio);
		log.trace("tiempo={},pagina={},tamanio={}", System.currentTimeMillis(), pagina, tamanio);
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.listPageable(pagina, tamanio), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sorted")
	public ResponseEntity<List<Producto>> listSorted() {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.listSortable(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
