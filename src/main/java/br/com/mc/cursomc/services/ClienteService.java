package br.com.mc.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mc.cursomc.dao.ClienteDAO;
import br.com.mc.cursomc.domain.Categoria;
import br.com.mc.cursomc.domain.Cliente;
import br.com.mc.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO dao;
	
	public Cliente buscar(Integer id) {
		 Optional<Cliente> obj = dao.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
		  "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> todos() {
		return dao.findAll();
	}
	
	public Cliente criar(Cliente cli) {
		return dao.save(cli);
	}
}
