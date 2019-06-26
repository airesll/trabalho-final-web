package com.br.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.ufc.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
	Role findByNomeRole(String nomeRole);
}
