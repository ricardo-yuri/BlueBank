package com.br.panacademy.devcompilers.bluebank.repository;

import com.br.panacademy.devcompilers.bluebank.entity.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

    Optional<Historic> findByIdAndIdAccount(Long id, Long idAccount);

    Optional<List<Historic>> findAllByIdAccount(Long idAccount);
/*
    @Query("select * from historic e where year(e.eventDate) = ?1 and month(e.eventDate) = ?2")
    List<Historic> getByYearAndMonth(int year, int month, int day);
*/
}
