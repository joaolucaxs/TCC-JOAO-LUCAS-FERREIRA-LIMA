package com.joaolucas.mapp.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaolucas.mapp.dtos.PecaDTO;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.services.PecaService;


@RestController
@RequestMapping(value = "/pecas")
public class PecaResource {

	@Autowired
	private PecaService service;

	@GetMapping
	public ResponseEntity<List<PecaDTO>> findAll() {

		List<Peca> pecas = service.findAll();
		List<PecaDTO> pecasDto = pecas.stream().map(peca -> new PecaDTO(peca)).collect(Collectors.toList());
		return ResponseEntity.ok().body(pecasDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PecaDTO> findById(@PathVariable String id) {

		Peca peca = service.findById(id);
		return ResponseEntity.ok().body(new PecaDTO(peca));
	}

	@PostMapping
	public ResponseEntity<Peca> insert(@RequestBody PecaDTO objDTO) {
		Peca obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);

		/*
		 * or URI uri =
		 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
		 * (obj.getId()).toUri(); return ResponseEntity.created(uri).body(obj);
		 */

	}
}
