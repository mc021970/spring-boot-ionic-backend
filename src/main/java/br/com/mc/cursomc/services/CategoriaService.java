package br.com.mc.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mc.cursomc.dao.CategoriaDAO;
import br.com.mc.cursomc.domain.Categoria;
import br.com.mc.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDAO dao;
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = dao.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
		  "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll() {
		return dao.findAll();
	}
	
	public Categoria insert(Categoria cat) {
		cat.setId(null);
		return dao.save(cat);
	}
	
	public Categoria update(Categoria cat) {
		find(cat.getId());
		return dao.save(cat);
	}
}
