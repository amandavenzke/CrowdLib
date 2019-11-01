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
import com.crowdlib.api.model.Genero;
import com.crowdlib.api.model.Idioma;
import com.crowdlib.api.model.Livro;
import com.crowdlib.api.model.Usuario;
import com.crowdlib.api.repository.GeneroRepository;
import com.crowdlib.api.repository.IdiomaRepository;
import com.crowdlib.api.repository.LivroRepository;
import com.crowdlib.api.repository.UsuarioRepository;
import com.crowdlib.api.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResource {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private IdiomaRepository idiomaRepository;

	@Autowired
	private LivroService livroService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Livro> listar() {
		return livroRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Livro> criar(@Valid @RequestBody Livro livro, HttpServletResponse response) {
		Usuario usuario = this.usuarioRepository.findById(livro.getUsuario().getId())
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		Genero genero = this.generoRepository.findById(livro.getGenero().getId())
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		Idioma idioma = this.idiomaRepository.findById(livro.getIdioma().getId())
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		livro.setUsuario(usuario);
		livro.setGenero(genero);
		livro.setIdioma(idioma);

		Livro livroSalvo = livroRepository.save(livro);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, livroSalvo.getId()));

		//livroSalvo.setUsuario(null);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPeloId(@Valid @PathVariable Long id) {
		Optional livro = this.livroRepository.findById(id);
		return livro.isPresent() ? ResponseEntity.ok(livro.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		livroRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
		Livro livroSalvo = livroService.atualizar(id, livro);
		return ResponseEntity.ok(livroSalvo);
	}

}
