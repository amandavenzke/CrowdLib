package com.crowdlib.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.crowdlib.api.model.Troca;
import com.crowdlib.api.repository.TrocaRepository;

@Service
public class TrocaService {
	
	@Autowired
	private TrocaRepository trocaRepository;

	public Troca atualizar(Long id, Troca troca) {
		Troca trocaSalva = this.trocaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(troca, trocaSalva, "id");

		return this.trocaRepository.save(trocaSalva);
	}

}