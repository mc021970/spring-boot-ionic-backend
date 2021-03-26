package br.com.mc.cursomc.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mc.cursomc.dao.ArquivoDAO;
import br.com.mc.cursomc.domain.Arquivo;
import br.com.mc.cursomc.services.exception.ObjectNotFoundException;

public class DBArquivoService implements ArquivoService {
	
	private static final Logger log = LoggerFactory.getLogger(DBArquivoService.class);

	@Autowired
	private ArquivoDAO arqdao;

	@Override
	public String getFileAccessPath() {
		return "/arquivos";
	}


	@Override
	public Arquivo uploadFile(Arquivo arq) {
		log.info("Iniciando upload: " + arq);
		Arquivo a1 = arqdao.save(arq);
		log.info("Upload concluído: " + a1);
		return a1;
	}


	@Override
	public Arquivo find(Integer id) {
		Optional<Arquivo> obj = arqdao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				  "Objeto não encontrado! Id: " + id + ", Tipo: " + Arquivo.class.getName()));
	}


	@Override
	public Arquivo findByNome(String nome) {
		Optional<Arquivo> obj = arqdao.findByNome(nome);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				  "Objeto não encontrado! nome: " + nome + ", Tipo: " + Arquivo.class.getName()));
	}

}
