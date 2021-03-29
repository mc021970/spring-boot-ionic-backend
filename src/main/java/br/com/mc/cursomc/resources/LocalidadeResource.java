package br.com.mc.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mc.cursomc.domain.Cidade;
import br.com.mc.cursomc.domain.Estado;
import br.com.mc.cursomc.services.LocalidadeService;

@RestController
@RequestMapping(value = "/estados")
public class LocalidadeResource {
	
	@Autowired
	private LocalidadeService lserv;


	@GetMapping
	public ResponseEntity<List<Estado>> findEstados() {
		System.out.println("Estados: buscando todos");
		List<Estado> lista = lserv.getEstados();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Estado> findEstado(@PathVariable Integer id) {
		System.out.println("Estados: buscando: " + id);
		Estado est = lserv.getEstado(id);
		return ResponseEntity.ok(est);
	}
	
	@GetMapping("/{id}/cidades")
	public ResponseEntity<List<Cidade>> findCidades(@PathVariable Integer id) {
		System.out.println("Cidades: buscando: " + id);
		Estado est = lserv.getEstado(id);
		List<Cidade> lista = lserv.getCidades(est.getId());
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/cidades/{id}")
	public ResponseEntity<Cidade> findCidade(@PathVariable Integer id) {
		System.out.println("Cidade: buscando: " + id);
		Cidade cid = lserv.getCidade(id);
		return ResponseEntity.ok(cid);
	}
}
