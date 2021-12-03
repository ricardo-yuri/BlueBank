package com.br.panacademy.devcompilers.bluebank.repository;

import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByIdAndDeletadoIsFalse(Long id);
}
