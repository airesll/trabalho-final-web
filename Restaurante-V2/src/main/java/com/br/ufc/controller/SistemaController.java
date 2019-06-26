package com.br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.model.Prato;

@Controller
public class SistemaController {
	@Autowired
	PratoController pratoController;
	
	@Autowired
	ClienteController clienteController;
	
	@RequestMapping("/")
	public ModelAndView mostrarPaginaInicial() {
		//String username = clienteController.getUserName();
		List<Prato> pratos = pratoController.listar();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratos);
		//mv.addObject("username", username);
		return mv;
	}
}