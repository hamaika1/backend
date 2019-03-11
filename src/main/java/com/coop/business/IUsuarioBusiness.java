package com.coop.business;

import java.util.List;

import com.coop.model.Usuario;

public interface IUsuarioBusiness {

	public Usuario load(long id) throws BusinessException, NotFoundException;

	public Usuario add(Usuario usuario) throws BusinessException;

	public void delete(long id) throws BusinessException;

	public Usuario update(Usuario usuario) throws BusinessException;

	public List<Usuario> list() throws BusinessException;

	List<Usuario> filterList(String partName) throws BusinessException;

}
