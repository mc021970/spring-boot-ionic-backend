package br.com.mc.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Entity
public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int LIMITE = 1048576;

	@Id
	private Integer id;
	private String nome;
	
	@Lob
	@Column(length = 1048576)
	@Size(max = 1048576)
	private byte[] conteudo;
	
	public Arquivo() {
		super();
	}

	public Arquivo(Integer id, String nome, byte[] conteudo) {
		super();
		this.id = id;
		this.nome = nome;
		this.conteudo = conteudo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getConteudo() {
		return conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquivo other = (Arquivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Arquivo [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", tamanho=");
		builder.append(conteudo == null ? 0 : conteudo.length);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
