package com.crowdlib.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crowdlib.api.event.RecursoCriadoEvent;
import com.crowdlib.api.model.Troca;
import com.crowdlib.api.model.Usuario;
import com.crowdlib.api.repository.TrocaRepository;
import com.crowdlib.api.repository.UsuarioRepository;
import com.crowdlib.api.service.TrocaService;

@RestController
@RequestMapping("/trocas")
public class TrocaResource {

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TrocaService trocaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Troca> listar() {
		return trocaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Troca> criar(@Valid @RequestBody Troca troca, HttpServletResponse response) {
		Usuario usuarioSolicitante = this.usuarioRepository.findById(troca.getUsuarioSolicitante().getId())
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		Usuario usuarioSolicitado = this.usuarioRepository.findById(troca.getUsuarioSolicitado().getId())
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		troca.setUsuarioSolicitante(usuarioSolicitante);
		troca.setUsuarioSolicitado(usuarioSolicitado);

		Troca trocaSalva = trocaRepository.save(troca);

		troca.setUsuarioSolicitante(null);
		troca.setUsuarioSolicitado(null);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, trocaSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(trocaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPeloId(@Valid @PathVariable Long id) {
		Optional troca = this.trocaRepository.findById(id);
		return troca.isPresent() ? ResponseEntity.ok(troca.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/usuarioSolicitante/{usuarioSolicitante}")
	public ResponseEntity buscarPeloUsuarioSolicitante(@Valid @PathVariable Long usuarioSolicitante) {
		Optional troca = this.trocaRepository.findByUsuarioSolicitanteId(usuarioSolicitante);
		return troca.isPresent() ? ResponseEntity.ok(troca.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/usuarioSolicitado/{usuarioSolicitado}")
	public ResponseEntity buscarPeloUsuarioSolicitado(@Valid @PathVariable Long usuarioSolicitado) {
		Optional troca = this.trocaRepository.findByUsuarioSolicitadoId(usuarioSolicitado);
		return troca.isPresent() ? ResponseEntity.ok(troca.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		trocaRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Troca> atualizar(@PathVariable Long id, @Valid @RequestBody Troca troca) {
		Troca trocaSalva = trocaService.atualizar(id, troca);
		return ResponseEntity.ok(trocaSalva);
	}

}
