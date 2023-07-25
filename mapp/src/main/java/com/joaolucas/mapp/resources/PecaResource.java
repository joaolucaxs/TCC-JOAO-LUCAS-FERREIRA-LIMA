package com.joaolucas.mapp.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.services.PecaService;

@RestController
@RequestMapping(value = "/pecas")
public class PecaResource {

	@Autowired
	private PecaService service;

	@GetMapping
	public ResponseEntity<List<Peca>> findAll() {

		List<Peca> pecas = service.findAll();
		return ResponseEntity.ok().body(pecas);
	}
}
