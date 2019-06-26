package com.br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.model.Pedido;
import com.br.ufc.model.Usuario;
import com.br.ufc.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoController pedidoController;
	
	//formulario de cadastro cliente
	@RequestMapping("/novoCadastro")
	public ModelAndView cadastrarCliente (Usuario cliente) {
		ModelAndView mv = new ModelAndView("contato");
		mv.addObject("cliente", new Usuario());
		return mv;
	}
	
	//salvar no banco de dados
	@RequestMapping("cliente/salvar")
	public ModelAndView salvandoCliente(@Validated Usuario novoCliente, BindingResult result) {
		ModelAndView mv = new ModelAndView("contato");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		ModelAndView mv2 = new ModelAndView("index");
		clienteService.novoCadastro(novoCliente);
		//mv2.addObject("mensagem", "Cliente cadastrado");
		return mv2;
	}
	
	//excluir um cliente
	@RequestMapping("cliente/excluir/{id}")
	//pega o codigo da instancia que esta na URI
	public ModelAndView excluindoCliente(@PathVariable Long id) {
		clienteService.apagarCadastro(id);
		ModelAndView mv = new ModelAndView("redirect:/paginaInicial");
		return mv;
	}
	
	//atualizar um cliente
	@RequestMapping("cliente/atualizar/{id}")
	public ModelAndView atualizarCliente(@PathVariable Long id) {
		Usuario cliente = clienteService.buscarCliente(id);
		ModelAndView mv = new ModelAndView("contato");
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	//criar pedido
	//@RequestMapping("cliente/fazerPedido")
	public ModelAndView criarPedido() {
		String usuarioLogado = this.getUserName();
		clienteService.criarPedido(usuarioLogado);
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	//listar pedidos
	@RequestMapping("cliente/listarPedidos")
	public ModelAndView listarPedidos () {
		String usuarioLogado = this.getUserName();
		Usuario usuario = clienteService.buscarClienteUsername(usuarioLogado);
		pedidoController.listarPedidos(usuario);
		
		List<Pedido> pedidos = pedidoController.listarPedidos(usuario);
		ModelAndView mv = new ModelAndView("listaPedido");
		mv.addObject("listaPedidos", pedidos);
		return mv;
	}
	
	//fechar pedido
	@RequestMapping("cliente/fecharPedido/{idPedido}")
	public ModelAndView fecharPedido(@PathVariable Long idPedido) {
		pedidoController.fecharPedido(idPedido);
		ModelAndView mv = new ModelAndView("redirect:/cliente/listarPedidos");
		return mv;
		
	}
	
	//adionar prato
	@RequestMapping("cliente/adicionarPrato/{idPrato}")
	public ModelAndView pedirPrato(@PathVariable Long idPrato) {
		Usuario usuario = clienteService.buscarClienteUsername(this.getUserName());
		clienteService.pedirPrato(usuario,idPrato);
		ModelAndView mv = new ModelAndView("redirect:/cliente/listarPedidos");
		return mv;
	}
	
	//ver Pratos dos pedidos
	@RequestMapping("/cliente/listaPratosPedido/{idPedido}")
	public ModelAndView mostrarPratosDoPedido(@PathVariable Long idPedido) {
		//Usuario usuario = clienteService.buscarClienteUsername(this.getUserName());
		Pedido pedido = pedidoController.buscarPedido(idPedido);
		//List<Prato> pratos = pedidoController.mostrarPratosDoPedido(pedido);
		ModelAndView mv = new ModelAndView("listaPratosPedidos");
		mv.addObject("pedido", pedido);
		mv.addObject("listaDePratos", pedido.getPratos());
		return mv;
	}
	
	//remover prato do pedido
	@RequestMapping("cliente/removerPratoDoPedido/{idPrato}")
	public ModelAndView removerPratoDoPedido(@PathVariable Long idPrato) {
		Usuario usuario = clienteService.buscarClienteUsername(this.getUserName());
		clienteService.removerPratoDoPedido(usuario,idPrato);
		Pedido pedido = pedidoController.buscarPedidoAberto(usuario);
		ModelAndView mv = new ModelAndView("redirect:/cliente/listaPratosPedido/"+pedido.getIdPedido());
		return mv;
	}
	
	//pegar usuario logado
	public String getUserName() {
		UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuario.getUsername();
	}
}