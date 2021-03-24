package br.com.mc.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mc.cursomc.domain.Pedido;
import br.com.mc.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedserv;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/all")
	public List<Pedido> findAll() {
		System.out.println("Pedidos: buscando todos");
		return pedserv.todos();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		System.out.println("Pedidos: buscando: " + id);
		Pedido cli = pedserv.find(id);
		return ResponseEntity.ok(cli);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
		System.out.println("Pedidos: Criando: " + pedido);
		Pedido pedNovo = pedserv.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedNovo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}


	@GetMapping
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "instante") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "DESC") String direction) {
		System.out.println("Pedidos: buscando pagina");
		Page<Pedido> lista = pedserv.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(lista);
	}
}
