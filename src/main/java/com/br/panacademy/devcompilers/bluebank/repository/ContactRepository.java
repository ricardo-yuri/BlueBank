package com.br.panacademy.devcompilers.bluebank.repository;

import com.br.panacademy.devcompilers.bluebank.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
