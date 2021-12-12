package com.br.panacademy.devcompilers.bluebank.repository;

import com.br.panacademy.devcompilers.bluebank.entity.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

    Optional<Historic> findByIdAndIdAccount(Long id, Long idAccount);

    Optional<List<Historic>> findAllByIdAccount(Long idAccount);

    Optional<List<Historic>> findByIdAndOperationDate(Long idAccount, LocalDate date);

}
