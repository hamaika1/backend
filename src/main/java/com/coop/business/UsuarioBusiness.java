package com.coop.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.coop.model.Usuario;
import com.coop.model.persistence.UsuarioRepository;

@Service
public class UsuarioBusiness implements IUsuarioBusiness {

	@Autowired
	private UsuarioRepository usuarioDAO;

	@Override
	public Usuario load(long id) throws BusinessException, NotFoundException {
		Optional<Usuario> u;

		try {
			u = usuarioDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (u.isPresent()) {
			return u.get();
		} else
			throw new NotFoundException();

	}

	@Override
	public Usuario add(Usuario usuario) throws BusinessException {
		try {
			return usuarioDAO.save(usuario);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public void delete(long id) throws BusinessException {
		try {
			usuarioDAO.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Usuario update(Usuario usuario) throws BusinessException {
		try {
			return usuarioDAO.save(usuario);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Usuario> list() throws BusinessException {
		try {
			return usuarioDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Usuario> filterList(String partName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
