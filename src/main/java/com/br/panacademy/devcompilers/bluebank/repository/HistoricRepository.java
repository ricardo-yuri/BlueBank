package com.br.panacademy.devcompilers.bluebank.repository;

import com.br.panacademy.devcompilers.bluebank.entity.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

    Optional<Historic> findByIdAndIdAccount(Long id, Long idAccount);

    Optional<List<Historic>> findAllByIdAccount(Long idAccount);
/*
    @Query(value = "SELECT * FROM historic u WHERE id = ?1 AND createAt = ?2", nativeQuery = true)
    Optional<List<Historic>> findByCreationDate(Long idAccount, LocalDate date);
*/
}
