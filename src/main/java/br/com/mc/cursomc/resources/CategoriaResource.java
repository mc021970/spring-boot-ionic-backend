package br.com.mc.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mc.cursomc.domain.Categoria;
import br.com.mc.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService catserv;

	@GetMapping
	public List<Categoria> findAll() {
		System.out.println("Categorias: buscando todas");
		return catserv.todos();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		System.out.println("Categorias: buscando: " + id);
		Categoria cat = catserv.buscar(id);
		return ResponseEntity.ok(cat);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria cat) {
		System.out.println("Categoria: " + cat);
		Categoria catNova = catserv.criar(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(catNova.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
