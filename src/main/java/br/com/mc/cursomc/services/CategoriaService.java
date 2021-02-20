package br.com.mc.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mc.cursomc.dao.CategoriaDAO;
import br.com.mc.cursomc.domain.Categoria;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDAO dao;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = dao.findById(id);
		return cat.orElse(null);
	}
	
	public List<Categoria> todos() {
		return dao.findAll();
	}
	
	public Categoria criar(Categoria cat) {
		return dao.save(cat);
	}
}
