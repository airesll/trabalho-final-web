package com.br.ufc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPedido;
	private float valorTotal;
	private boolean statusPedido;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataPedido;
	@ManyToOne
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(name = "pratos_pedidos", 
			joinColumns = @JoinColumn(
			name = "pedido_id",referencedColumnName =  "idPedido"),
			inverseJoinColumns = @JoinColumn(
			name ="prato_id",referencedColumnName = "idPrato"))
	private List<Prato> pratos;
	
	//construtor 2
	public Pedido () {	
	}
	
	public Pedido (Usuario usuario) {	
		this.statusPedido = true;
		this.dataPedido = new Date();
		this.usuario = usuario;
		this.pratos = new ArrayList<Prato>();
	}
	
	//GETS AND SETS
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public boolean isStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(boolean statusPedido) {
		this.statusPedido = statusPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
}