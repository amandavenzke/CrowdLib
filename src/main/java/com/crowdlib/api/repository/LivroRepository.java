package com.crowdlib.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowdlib.api.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
