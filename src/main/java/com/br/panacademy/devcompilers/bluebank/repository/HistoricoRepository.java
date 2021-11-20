package com.br.panacademy.devcompilers.bluebank.repository;

import com.br.panacademy.devcompilers.bluebank.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}
