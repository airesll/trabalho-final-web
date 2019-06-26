package com.br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.model.Prato;

@Controller
//@RequestMapping("/gerente")
public class GerenteController {
	@Autowired
	private PratoController pratoController;
	
	//formulario para criar prato
	@RequestMapping("gerente/cadastrarNovoPrato")
	public ModelAndView cadastrarPrato() {
		//modelAndView mapeado para a pagina html formulario
		ModelAndView mv = new ModelAndView("cadastroDePrato");
		//mandando uma instancia de pessoa para o formulario
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	//salvar prato no banco de dados
	@RequestMapping("gerente/salvarPrato")
	public ModelAndView salvarPrato(@Validated Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("cadastroDePrato");
		if(result.hasErrors()) {
			return mv;
		}
		//chama a funçao cadastrar que implementa o save(salva no banco de dados)
		ModelAndView mv2 = new ModelAndView("redirect:/gerente/listaDePratos");
		pratoController.cadastrarPrato(prato, imagem);
		//mv2.addObject("mensagem", "prato cadastrado");
		return mv2;
	}
	
	//mostrar os pratos do banco de dados
	@RequestMapping("/gerente/listaDePratos")
	public ModelAndView mostrarPratos() {
		List<Prato> pratos = pratoController.listar();
		ModelAndView mv = new ModelAndView("listaPratos");
		mv.addObject("listaDePratos",pratos);
		return mv;
	}
	
	//excluir um prato do banco de dados
	@RequestMapping("/gerente/excluirPrato/{id}")
	public ModelAndView excluirPrato(@PathVariable Long id) {
		pratoController.excluirPrato(id);
		ModelAndView mv = new ModelAndView("redirect:/gerente/listaDePratos");
		return mv;
	}
	
	//atualizar um prato
	@RequestMapping("/atualizarPrato/{id}")
	public ModelAndView atualizarPrato(@PathVariable Long id) {
		Prato prato = pratoController.buscarPorId(id);
		ModelAndView mv = new ModelAndView("cadastroDePrato");
		mv.addObject("prato", prato);
		return mv;
	}
}