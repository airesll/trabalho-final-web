package com.br.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.ufc.model.Prato;

public interface PratoRepository extends JpaRepository<Prato, Long>{
	void findByNomePrato(String nomePrato);
}
