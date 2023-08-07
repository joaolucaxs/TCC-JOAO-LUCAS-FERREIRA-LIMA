package com.joaolucas.mapp.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaolucas.mapp.dtos.ArtistaDTO;
import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.services.ArtistaService;

@Controller
@RequestMapping(value = "/artistas")
public class ArtistaResource {

	@Autowired
	private ArtistaService service;

	@GetMapping
	public ResponseEntity<List<ArtistaDTO>> findAll() {

		List<Artista> artistas = service.findAll();
		List<ArtistaDTO> artistasDto = artistas.stream().map(artista -> new ArtistaDTO(artista)).collect(Collectors.toList());
		return ResponseEntity.ok().body(artistasDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Artista> findById(@PathVariable String id) {

		Artista artista = service.findById(id);
		return ResponseEntity.ok().body(artista);
	}

//	@PostMapping
//	public ResponseEntity<Artista> insert(@RequestBody ArtistaDTO objDTO) {
//		Artista obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		return ResponseEntity.ok().body(obj);
//
//		/*
//		 * URI uri =
//		 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
//		 * (obj.getId()).toUri(); return ResponseEntity.created(uri).body(obj);
//		 */
//
//	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Artista> update(@PathVariable String id, @RequestBody Artista obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/obras")
	public ResponseEntity<List<Peca>> obras(@PathVariable String id) {
		Artista artista = service.findById(id);
		return ResponseEntity.ok().body(artista.getListaObras());
	}
	
	
}
