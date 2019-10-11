package com.crowdlib.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.crowdlib.api.model.Livro;
import com.crowdlib.api.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	public Livro atualizar(Long id, Livro livro) {
		Livro livroSalvo = this.livroRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(livro, livroSalvo, "id");

		return this.livroRepository.save(livroSalvo);
	}

}
