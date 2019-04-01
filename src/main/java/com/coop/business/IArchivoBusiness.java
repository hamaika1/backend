package com.coop.business;

import java.util.List;

import com.coop.model.Archivo;

public interface IArchivoBusiness {

	public Archivo load(long id) throws BusinessException, NotFoundException;

	public Archivo add(Archivo archivo) throws BusinessException;

	public void delete(long id) throws BusinessException;

	public Archivo update(Archivo archivo) throws BusinessException;

	public List<Archivo> list() throws BusinessException;

	List<Archivo> filterList(String partName) throws BusinessException;

}
