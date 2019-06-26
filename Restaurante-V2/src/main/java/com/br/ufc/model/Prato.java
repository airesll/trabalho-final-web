package com.br.ufc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Prato {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idPrato;
	@NotBlank(message = "informe o nome do prato")
	private String nomePrato;
	private float precoPrato;
	
	@ManyToMany(mappedBy = "pratos")
	private List<Pedido> pedidos;
	//@NotBlank(message = "informe o nome do prato")
	//private String caminhoImagem;
	
	//GETS AND SETS
	public Long getIdPrato() {
		return idPrato;
	}
	public void setIdPrato(Long idPrato) {
		this.idPrato = idPrato;
	}
	public String getNomePrato() {
		return nomePrato;
	}
	public void setNomePrato(String nomePrato) {
		this.nomePrato = nomePrato;
	}
	public float getPrecoPrato() {
		return precoPrato;
	}
	public void setPrecoPrato(float precoPrato) {
		this.precoPrato = precoPrato;
	}
	/*
	 * public String getCaminhoImagem() { return caminhoImagem; } public void
	 * setCaminhoImagem(String caminhoImagem) { this.caminhoImagem = caminhoImagem;
	 * }
	 */
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}