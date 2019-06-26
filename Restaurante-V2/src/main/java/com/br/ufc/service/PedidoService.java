package com.br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ufc.model.Pedido;
import com.br.ufc.model.Prato;
import com.br.ufc.model.Usuario;
import com.br.ufc.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public void criarPedido(Usuario usuario) {
		Pedido pedido = new Pedido(usuario);
		pedidoRepository.save(pedido);
	}

	public void atualizarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	public List<Pedido> listarPedido(Usuario usuario) {
		return pedidoRepository.findAllByUsuario(usuario);
		
	}

	public Pedido buscarPedido(Long idPedido) {
		return pedidoRepository.getOne(idPedido);
	}
	
	public void fecharPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	public Pedido buscarPedidoAberto(Usuario usuario) {
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(usuario);
		for (Pedido pedido : pedidos) {
			if(pedido.isStatusPedido()) {
				return pedido;
			}
		}
		return null;
	}

	public void adcionarPratoAoPedidoAberto(Pedido pedidoAberto, Prato prato) {
		List<Prato> pratos = pedidoAberto.getPratos();
		pratos.add(prato);
		pedidoAberto.setPratos(pratos);
		pedidoRepository.save(pedidoAberto);
	}

//	public List<Prato> mostrarPratosDoPedido(Pedido pedido) {
//		return pedido.getPratos();
//	}
}