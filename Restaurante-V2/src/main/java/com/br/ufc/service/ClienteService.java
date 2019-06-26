package com.br.ufc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.ufc.controller.PedidoController;
import com.br.ufc.model.Role;
import com.br.ufc.model.Usuario;
import com.br.ufc.repository.RoleRepository;
import com.br.ufc.repository.UsuarioRepository;

@Service
public class ClienteService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PedidoController pedidoController;
	
	//salvar cliente
	public void novoCadastro(Usuario cliente) {
		List<Role> papeis = new ArrayList<Role>();
		Role papel = roleRepository.findByNomeRole("ROLE_CLIENTE");
		papeis.add(papel);
		cliente.setRoles(papeis);
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		usuarioRepository.save(cliente);
	}
	
	//deletar cliente
	public void apagarCadastro(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	//buscasr cliente
	public Usuario buscarCliente (Long id) {
		return usuarioRepository.getOne(id);
	}
	
	public Usuario buscarClienteUsername(String username) {
		return usuarioRepository.findByLogin(username);
	}

	//criar pedido
	public void criarPedido(String usuarioLogado) {
		Usuario usuario = usuarioRepository.findByLogin(usuarioLogado);
		pedidoController.criarPedido(usuario);
	}

	public void fecharPedido(Long idPedido) {
		pedidoController.fecharPedido(idPedido);
	}

	//adcionar prato ao pedido
	public void pedirPrato(Usuario usuario, Long idPrato) {
		//Usuario usuario = this.buscarClienteUsername(userName);
		pedidoController.adcionarPratoAoPedido(usuario,idPrato);
	}

	//remover prato do pedido
	public void removerPratoDoPedido(Usuario usuario, Long idPrato) {
		pedidoController.removerPratoDoPedido(usuario,idPrato);
	}
}