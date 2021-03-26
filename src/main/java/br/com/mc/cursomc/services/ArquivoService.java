package br.com.mc.cursomc.services;

import br.com.mc.cursomc.domain.Arquivo;

public interface ArquivoService {

	String getFileAccessPath(Arquivo arq);
	
	Arquivo uploadFile(Arquivo arq);
	
	Arquivo find(Integer id);
	
	Arquivo findByNome(String nome);
}
