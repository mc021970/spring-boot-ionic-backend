package br.com.mc.cursomc.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mc.cursomc.domain.Arquivo;
import br.com.mc.cursomc.domain.Cliente;
import br.com.mc.cursomc.dto.ClienteDTO;
import br.com.mc.cursomc.dto.ClienteNewDTO;
import br.com.mc.cursomc.security.UserSS;
import br.com.mc.cursomc.services.ClienteService;
import br.com.mc.cursomc.services.ImagemService;
import br.com.mc.cursomc.services.UserService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService cliserv;

	@Autowired
	private ImagemService imgserv;
	
	

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		System.out.println("Clientes: buscando todas");
		List<Cliente> lista = cliserv.findAll();
		//List<ClienteDTO> listadto = lista.stream().map(cli -> new ClienteDTO(cli)).collect(Collectors.toList());
		List<ClienteDTO> listadto = new ArrayList<>();
		for (Cliente cli : lista) {
			listadto.add(new ClienteDTO(cli));
		}
		return ResponseEntity.ok(listadto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		System.out.println("Clientes: buscando todas");
		Page<Cliente> lista = cliserv.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listadto = lista.map(cli -> new ClienteDTO(cli));
		return ResponseEntity.ok(listadto);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		System.out.println("Clientes: buscando: " + id);
		Cliente cli = cliserv.find(id);
		return ResponseEntity.ok(cli);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clidto) {
		Cliente cli = cliserv.fromDTO(clidto);
		System.out.println("Clientes: Criando: " + cli);
		Cliente cliNova = cliserv.insert(cli);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliNova.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clidto, @PathVariable Integer id) {
		Cliente cli = cliserv.fromDTO(clidto);
		System.out.println("Clientes: Atualizando: " + cli);
		cli.setId(id);
		cli = cliserv.update(cli);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		System.out.println("Clientes: Removendo: " + id);
		cliserv.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/foto")
	public ResponseEntity<String> uploadPicture(@RequestParam(name = "arquivo") MultipartFile arqParam) {
		UserSS user = UserService.authenticated();
		try {

			System.out.println("Clientes: foto: " + arqParam.getOriginalFilename() + ", tam.: " + arqParam.getSize() + ", limite: " + Arquivo.LIMITE);
			if (arqParam.getSize() > Arquivo.LIMITE) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("O tamanho da imagem não pode ser maior que " + Arquivo.LIMITE);
			}

			String ext = FilenameUtils.getExtension(arqParam.getOriginalFilename());
			byte[] b = imgserv.createProfilePicture(arqParam.getBytes(), ext);
			
			Arquivo arquivo = new Arquivo(user.getId(), arqParam.getOriginalFilename(), b);
			arquivo = imgserv.guardarImagem(arquivo);
			System.out.println("Clientes: Foto: " + arquivo);
			URI uri = ServletUriComponentsBuilder.fromPath(imgserv.getCaminhoImagem(arquivo)).build().toUri();
			return ResponseEntity.created(uri).build();
		}
		catch (Exception e) {
			System.out.println("Clientes: Foto: Erro: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	


	@GetMapping("/foto")
	public ResponseEntity<byte[]> getPicture() {
		UserSS user = UserService.authenticated();
		Arquivo arq = imgserv.obterImagem(user.getId());
		String mimeType = URLConnection.guessContentTypeFromName(arq.getNome());
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(arq.getConteudo());
	}
	
}
