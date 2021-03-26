package br.com.mc.cursomc.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mc.cursomc.domain.Arquivo;

@Repository
public interface ArquivoDAO extends JpaRepository<Arquivo, Integer> {

	@Transactional(readOnly = true)
	Optional<Arquivo> findByNome(String nome);
}
