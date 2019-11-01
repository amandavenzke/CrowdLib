package com.crowdlib.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "troca")
public class Troca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_usuario_solicitante")
	private Usuario usuarioSolicitante;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_usuario_solicitado")
	private Usuario usuarioSolicitado;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "troca_realizada")
	private TrocaRealizada trocaRealizada;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "troca_livro", joinColumns = @JoinColumn(name = "id_troca"), inverseJoinColumns = @JoinColumn(name = "id_livro"))
	private List<Livro> livros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public Usuario getUsuarioSolicitado() {
		return usuarioSolicitado;
	}

	public void setUsuarioSolicitado(Usuario usuarioSolicitado) {
		this.usuarioSolicitado = usuarioSolicitado;
	}

	public TrocaRealizada getTrocaRealizada() {
		return trocaRealizada;
	}

	public void setTrocaRealizada(TrocaRealizada trocaRealizada) {
		this.trocaRealizada = trocaRealizada;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

}
