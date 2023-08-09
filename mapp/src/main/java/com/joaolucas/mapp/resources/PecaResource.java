package com.joaolucas.mapp.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joaolucas.mapp.dtos.PecaDTO;
import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.services.ArtistaService;
import com.joaolucas.mapp.services.PecaService;

@Controller
@RequestMapping(value = "/pecas")
public class PecaResource {

	@Autowired
	private PecaService service;

	@Autowired
	private ArtistaService artistaService;

	@GetMapping()
	public ModelAndView findAll() {
		List<Peca> pecas = service.findAll();
		List<PecaDTO> pecasDto = pecas.stream().map(peca -> new PecaDTO(peca)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("obras/listarObras");
		mv.addObject("pecas", pecasDto);
		return mv;
	}

	@GetMapping(value = "/filtrar")
	public ModelAndView filtrarObras(@RequestParam(value = "filtro") String filtro,
			@RequestParam(value = "pesquisa") String pesquisa) {
		
		List<PecaDTO> pecasDto = service.filtrarPorCampo(pesquisa);

		if (filtro.equals("dataAquisicao")) {
			pecasDto = service.filtrarPorDataAquisicao(service.stringToLocalDate(pesquisa));
		}

		ModelAndView mv = new ModelAndView("obras/listarObras");
		mv.addObject("pecas", pecasDto);
		return mv;
	}

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PecaDTO> findById(@PathVariable String id) {

		Peca peca = service.findById(id);
		return ResponseEntity.ok().body(new PecaDTO(peca));
	}

	@PostMapping
	public ResponseEntity<Peca> insert(@RequestBody PecaDTO objDTO) {
		Artista artista = service.fromDTO(objDTO.getArtesao());
		artistaService.insert(artista);

		Peca obj = service.fromDTO(objDTO);
		obj.getArtesao().setId(artista.getId());
		;
		obj = service.insert(obj);
		artista.getListaObras().add(obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Peca> update(@PathVariable String id, @RequestBody Peca obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
