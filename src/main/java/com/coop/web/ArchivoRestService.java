package com.coop.web;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coop.business.BusinessException;
import com.coop.business.IArchivoBusiness;
import com.coop.business.NotFoundException;
import com.coop.model.Archivo;

@RestController
@RequestMapping(Constantes.URL_ARCHIVOS)
public class ArchivoRestService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IArchivoBusiness archivoBusiness;

	@PostMapping("")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
		try {
			Archivo archivo = new Archivo();
			archivo.setContenido(file.getBytes());
			archivo.setLength(archivo.getContenido().length);
			archivo.setFecha(new Date());
			archivo.setName(StringUtils.cleanPath(file.getOriginalFilename()));
			archivo.setMime(file.getContentType());
			archivoBusiness.add(archivo);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_ARCHIVOS + "/" + archivo.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> download(@PathVariable("id") long id) {

		try {
			Archivo archivo = archivoBusiness.load(id);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.parseMediaType(archivo.getMime()));
			responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getName() + "\"");
			return new ResponseEntity<byte[]>(archivo.getContenido(), responseHeaders, HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}

	}
}
