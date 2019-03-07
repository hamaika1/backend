package com.coop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coop.business.BusinessException;
import com.coop.business.IProductoBusiness;
import com.coop.business.NotFoundException;
import com.coop.model.Producto;

@RestController
public class ProductoRestService {

	@Autowired
	private IProductoBusiness productoBusiness;

	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> list() {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> load(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/productos")
	public ResponseEntity<Producto> add(Producto producto) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.add(producto), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
