package br.com.mc.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mc.cursomc.dao.CidadeDAO;
import br.com.mc.cursomc.dao.EstadoDAO;
import br.com.mc.cursomc.domain.Cidade;
import br.com.mc.cursomc.domain.Estado;
import br.com.mc.cursomc.services.exception.ObjectNotFoundException;

@Service
public class LocalidadeService {

	@Autowired
	private EstadoDAO estdao;

	@Autowired
	private CidadeDAO ciddao;

	public List<Estado> getEstados() {
		return estdao.findAllByOrderByUf();
	}

	public Estado getEstado(Integer id) {
		Optional<Estado> obj = estdao.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));

	}

	public Cidade getCidade(Integer id) {
		Optional<Cidade> obj = ciddao.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));

	}

	public List<Cidade> getCidades(Integer idEstado) {
		Estado est = this.getEstado(idEstado);
		return ciddao.findByEstadoOrderByNome(est);
	}
}
