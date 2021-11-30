package com.br.panacademy.devcompilers.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.panacademy.devcompilers.bluebank.entity.Conta;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByIdAndDeletadaIsFalse(Long id);
}
