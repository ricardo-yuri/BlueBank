package com.br.panacademy.devcompilers.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.panacademy.devcompilers.bluebank.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
