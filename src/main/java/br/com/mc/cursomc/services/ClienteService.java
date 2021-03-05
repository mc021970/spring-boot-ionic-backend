package br.com.mc.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.mc.cursomc.dao.ClienteDAO;
import br.com.mc.cursomc.domain.Cliente;
import br.com.mc.cursomc.dto.ClienteDTO;
import br.com.mc.cursomc.services.exception.DataIntegrityException;
import br.com.mc.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO dao;
	
	public Cliente find(Integer id) {
		 Optional<Cliente> obj = dao.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
		  "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return dao.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pr = PageRequest.of(page, linesPerPage, Direction.fromString(direction), orderBy);
		return dao.findAll(pr);
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente objdb = find(obj.getId());
		updateData(objdb, obj);
		return dao.save(objdb);
	}
	
	private void updateData(Cliente objdb, Cliente obj) {
		objdb.setNome(obj.getNome());
		objdb.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			dao.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir Cliente com pedidos");
		}
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
}
