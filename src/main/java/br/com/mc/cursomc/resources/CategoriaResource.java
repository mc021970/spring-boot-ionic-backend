package br.com.mc.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mc.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	public static List<Categoria> categorias = new ArrayList<>();
	
	static {

		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(2, "Escritório");
		categorias.add(c1);
		categorias.add(c2);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		return categorias;
	}
	
}
