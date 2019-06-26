package com.br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.br.ufc.model.Pedido;
import com.br.ufc.model.Prato;
import com.br.ufc.model.Usuario;
import com.br.ufc.service.PedidoService;

@Controller
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PratoController pratoController;
	
	//criar pedido
	public void criarPedido(Usuario usuario) {
		pedidoService.criarPedido(usuario);
	}
	
	//buscar pedido
	public Pedido buscarPedido(Long idPedido) {
		return pedidoService.buscarPedido(idPedido);
	}
	
	//listar
	public List<Pedido> listarPedidos(Usuario usuario) {
		return pedidoService.listarPedido(usuario);
	}
	
	//fechar
	public void fecharPedido(Long idPedido) {
		Pedido pedido = pedidoService.buscarPedido(idPedido);
		if(pedido != null) {
			pedido.setStatusPedido(false);
		}
		pedidoService.fecharPedido(pedido);
	}
	
	//adcionar prato em pedido
	public void adcionarPratoAoPedido(Usuario usuario, Long idPrato) {
		Pedido pedidoAberto = pedidoService.buscarPedidoAberto(usuario);
		Prato prato = pratoController.buscarPorId(idPrato);
		if(pedidoAberto != null) {
			pedidoAberto.setValorTotal(pedidoAberto.getValorTotal() + prato.getPrecoPrato());
			pedidoService.adcionarPratoAoPedidoAberto(pedidoAberto,prato);			
		}
		else {
			this.criarPedido(usuario);
			this.adcionarPratoAoPedido(usuario, idPrato);
		}		
	}

	public void removerPratoDoPedido(Usuario usuario, Long idPrato) {
		Pedido pedidoAberto = pedidoService.buscarPedidoAberto(usuario);
		Prato prato = pratoController.buscarPorId(idPrato);
		pedidoAberto.getPratos().remove(prato);
		pedidoAberto.setValorTotal(pedidoAberto.getValorTotal() - prato.getPrecoPrato());
		pedidoService.atualizarPedido(pedidoAberto);
	}
	
	public Pedido buscarPedidoAberto(Usuario usuario) {
		return pedidoService.buscarPedidoAberto(usuario);
	}

//	public List<Prato> mostrarPratosDoPedido(Pedido pedido) {
//		return pedidoService.mostrarPratosDoPedido(pedido);
//	}
}