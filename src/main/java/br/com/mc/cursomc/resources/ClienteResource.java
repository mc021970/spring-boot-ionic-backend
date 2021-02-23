package br.com.mc.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mc.cursomc.domain.Cliente;
import br.com.mc.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService cliserv;

	@GetMapping
	public List<Cliente> findAll() {
		System.out.println("Clientes: buscando todos");
		return cliserv.todos();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		System.out.println("Clientes: buscando: " + id);
		Cliente cli = cliserv.buscar(id);
		return ResponseEntity.ok(cli);
	}
	
	@PostMapping
	public Cliente novo(@RequestBody Cliente cli) {
		System.out.println("Cliente: " + cli);
		return cliserv.criar(cli);
	}
	
}
