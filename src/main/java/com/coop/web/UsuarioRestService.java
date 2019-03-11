package com.coop.web;

import java.util.List;

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
import com.coop.business.IUsuarioBusiness;
import com.coop.business.NotFoundException;
import com.coop.model.Usuario;

@RestController
@RequestMapping(Constantes.URL_USUARIOS)
public class UsuarioRestService {

	@Autowired
	private IUsuarioBusiness usuarioBusiness;

	@GetMapping("")
	public ResponseEntity<List<Usuario>> list(
			@RequestParam(required = false, defaultValue = "@*@", value = "q") String partName) {
		try {
			List<Usuario> lista;
			if (!partName.equals("@*@")) {
				lista = usuarioBusiness.filterList(partName);
			} else {
				lista = usuarioBusiness.list();
			}
			return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/id}")
	public ResponseEntity<Usuario> load(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Usuario>(usuarioBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario) {
		try {

			return new ResponseEntity<Usuario>(usuarioBusiness.add(usuario), HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		try {
			return new ResponseEntity<Usuario>(usuarioBusiness.update(usuario), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			usuarioBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
