package br.com.mc.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mc.cursomc.dao.PedidoDAO;
import br.com.mc.cursomc.domain.Pedido;
import br.com.mc.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoDAO dao;
	
	public Pedido buscar(Integer id) {
		 Optional<Pedido> obj = dao.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
		  "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public List<Pedido> todos() {
		return dao.findAll();
	}
	
	public Pedido criar(Pedido ped) {
		return dao.save(ped);
	}
}
