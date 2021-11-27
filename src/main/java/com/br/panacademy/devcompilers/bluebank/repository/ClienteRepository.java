package com.br.panacademy.devcompilers.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.panacademy.devcompilers.bluebank.entity.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findByCpf(String cpf);

}
