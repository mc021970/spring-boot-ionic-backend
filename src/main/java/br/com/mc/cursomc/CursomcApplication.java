package br.com.mc.cursomc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mc.cursomc.dao.CategoriaDAO;
import br.com.mc.cursomc.dao.ProdutoDAO;
import br.com.mc.cursomc.domain.Categoria;
import br.com.mc.cursomc.domain.Produto;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	CategoriaDAO catdao;
	@Autowired
	ProdutoDAO proddao;
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", "Computador Desktop completo", 2599.99);
		p1.addCategorias(cat1);
		Produto p2 = new Produto(null, "Impressora", "Impressora jato de tinta", 34.99);
		p2.addCategorias(cat1, cat2);
		Produto p3 = new Produto(null, "Mouse", "Mouse ótico com fio, USB", 24.99);
		p3.addCategorias(cat1);
		
		catdao.saveAll(Arrays.asList(cat1, cat2));
		proddao.saveAll(Arrays.asList(p1, p2, p3));
	}

}
