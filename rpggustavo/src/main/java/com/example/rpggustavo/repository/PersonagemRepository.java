package com.example.rpggustavo.repository;

import com.example.rpggustavo.Model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}