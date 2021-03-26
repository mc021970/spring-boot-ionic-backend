package br.com.mc.cursomc.resources;

import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mc.cursomc.domain.Arquivo;
import br.com.mc.cursomc.services.ArquivoService;

@RestController
@RequestMapping(value = "/arquivos")
public class ArquivoResource {
	
	@Autowired
	private ArquivoService arqserv;

	@GetMapping(value = "{id}")
	public ResponseEntity<byte[]> getArquivo(@PathVariable Integer id) {
		Arquivo arq = arqserv.find(id);
		String mimeType = URLConnection.guessContentTypeFromName(arq.getNome());
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(arq.getConteudo());
	}

}
