package com.br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.ufc.model.Prato;
import com.br.ufc.repository.PratoRepository;
import com.br.ufc.util.AulaFileUtils;

@Service
public class PratoService {
	
	@Autowired
	private PratoRepository pratoRepository;
	
	// cadastrar prato
	public void cadastrarPrato(Prato prato, MultipartFile imagem) {
		pratoRepository.save(prato);
		pratoRepository.findByNomePrato(prato.getNomePrato());
		String caminho = "img/" + prato.getIdPrato() + ".png";
		AulaFileUtils.salvarImagem(caminho, imagem);
	}
	
	// listar pratos
	public List<Prato> listar() {
		return pratoRepository.findAll();
	}
	
	// excluir pratos
	public void excluirPrato(Long id) {
		pratoRepository.deleteById(id);
	}
	
	//buscar um prato por id
	public Prato buscarPorId(Long id) {
		return pratoRepository.getOne(id);
	}

	public void buscarPorNomePrato(String nomePrato) {
		pratoRepository.findByNomePrato(nomePrato);
	}
}