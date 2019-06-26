package com.br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.br.ufc.model.Prato;
import com.br.ufc.service.PratoService;

@Controller
public class PratoController {

	@Autowired
	private PratoService pratoService;

	//salvar prato
	public void cadastrarPrato(Prato prato, MultipartFile imagem) {
		pratoService.cadastrarPrato(prato,imagem);
	}
	
	//listar prato
	public List<Prato> listar() {
		return pratoService.listar();
	}

	//excluir prato
	public void excluirPrato(Long id) {
		pratoService.excluirPrato(id);		
	}

	//buscar prato
	public Prato buscarPorId(Long id) {
		return pratoService.buscarPorId(id);
	}
}