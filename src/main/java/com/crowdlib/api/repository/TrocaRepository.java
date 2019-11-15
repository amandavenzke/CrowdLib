package com.crowdlib.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowdlib.api.model.Troca;

public interface TrocaRepository extends JpaRepository<Troca, Long> {

	public Optional<Troca> findByUsuarioSolicitanteId(Long usuarioSolicitante);

	public Optional<Troca> findByUsuarioSolicitadoId(Long usuarioSolicitado);

}
