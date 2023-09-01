package com.joaolucas.mapp.resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joaolucas.mapp.dtos.PecaDTOForm;
import com.joaolucas.mapp.dtos.PecaDTOShow;
import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.services.ArtistaService;
import com.joaolucas.mapp.services.PecaService;

@Controller
@RequestMapping(value = "/pecas")
public class PecaResource {

	@Autowired
	private PecaService obraService;

	@Autowired
	private ArtistaService artistaService;

	@GetMapping()
	public ModelAndView findAll() {
		List<Peca> pecas = obraService.findAll();
		List<PecaDTOShow> pecasDto = pecas.stream().map(peca -> new PecaDTOShow(peca)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("obras/listarObras");
		mv.addObject("pecas", pecasDto);
		return mv;
	}

	@GetMapping(value = "/filtrar")
	public ModelAndView filtrarObras(@RequestParam(value = "filtro") String filtro,
			@RequestParam(value = "pesquisa") String pesquisa) {

		List<Peca> pecas = obraService.filtrarPorCampo(pesquisa);

		if (filtro.equals("dataAquisicao")) {
			pecas = obraService.filtrarPorDataAquisicao(obraService.stringToLocalDate(pesquisa));
		}
		if (filtro.equals("assinada")) {
			pecas = obraService.filtrarPorAssinada(obraService.stringToBoolean(pesquisa));
		}
		if (filtro.equals("datada")) {
			pecas = obraService.filtrarPorDatada(obraService.stringToBoolean(pesquisa));
		}

		List<PecaDTOShow> pecasDto = pecas.stream().map(peca -> new PecaDTOShow(peca)).collect(Collectors.toList());

		ModelAndView mv = new ModelAndView("obras/listarObras");
		mv.addObject("pecas", pecasDto);
		return mv;
	}

	@GetMapping(value = "/novaObra")
	public ModelAndView novaObra() {
		ModelAndView mv = new ModelAndView("obras/novaObra");
		return mv;
	}

	@PostMapping(value = "/novaObra")
	public String inserirObra(PecaDTOForm objDTO, @RequestParam("imagemPecaFile") MultipartFile imagemPecaFile)
			throws IOException {

		Peca newObra = obraService.fromDTOFormulario(objDTO);
		obraService.novaObra(newObra);
		return "redirect:/pecas/novaObra/" + newObra.getId() + "/associarArtista";
	}

	@GetMapping(value = "/novaObra/{id}/associarArtista")
	public ModelAndView novaObraFormulario(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("artistas/novaObraArtistaForm");
		List<Artista> artistas = artistaService.findAll();
		Peca newObra = obraService.findById(id);
		mv.addObject("artesaos", artistas);
		mv.addObject("peca", newObra);
		return mv;
	}

	@PostMapping(value = "/novaObra/{id}/associarArtista")
	public String inserirArtistaObra(Artista artista, @PathVariable String id) {
		System.out.println(artista.getId());
		Peca newObra = obraService.findById(id);
		if (artista.getId().isBlank()) {
			artista.setId(null);
			artistaService.insert(artista);
		}
		var artistaAssociado = artistaService.findById(artista.getId());
		artistaAssociado.getListaObras().addAll(Arrays.asList(newObra));
		artistaService.insert(artistaAssociado);
		newObra.setArtesao(artistaAssociado);
		obraService.novaObra(newObra);
		return "redirect:/pecas";
	}

	@GetMapping(value = "/novaObra/{idObra}/associarArtista/artista/editarArtista/{idArtista}")
	public ModelAndView editarArtista(@PathVariable String idObra, @PathVariable String idArtista) {
		ModelAndView mv = new ModelAndView("artistas/editarArtistaFormNewObra");
		Artista artista = artistaService.findById(idArtista);
		Peca obra = obraService.findById(idObra);
		mv.addObject("artesao", artista);
		mv.addObject("obra", obra);
		return mv;
	}

	@PostMapping(value = "/novaObra/{idObra}/associarArtista/artista/editarArtista/{idArtista}")
	public String editarArtista(Artista artista, @PathVariable String idObra, @PathVariable String idArtista) {

		artistaService.update(idArtista, artista);
		return "redirect:/pecas/novaObra/" + idObra + "/associarArtista";
	}

	@GetMapping(value = "/editarObra/{id}")
	public ModelAndView editarObra(@PathVariable String id) {
		Peca editObra = obraService.findById(id);
		ModelAndView mv = new ModelAndView("obras/editarObra");
		mv.addObject("peca", editObra);
		return mv;
	}

	@PostMapping(value = "/editarObra/{id}")
	public String editarObra(@PathVariable String id, PecaDTOForm objDTO,
			@RequestParam("imagemPecaFile") MultipartFile imagemPecaFile) throws IOException {

		Peca obraPersisted = obraService.findById(id);
		Peca newObraEdited = obraService.fromDTOFormulario(objDTO);
		if (imagemPecaFile.isEmpty()) {
			newObraEdited.getFichatecnica().setImagemCapa(obraPersisted.getFichatecnica().getImagemCapa());
		}
		obraService.update(id, newObraEdited);
		return "redirect:/pecas/editarObra/" + id + "/associarArtista";
	}

	@GetMapping(value = "/editarObra/{id}/associarArtista")
	public ModelAndView editarObraFormulario(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("artistas/editarObraArtistaForm");
		List<Artista> artistas = artistaService.findAll();
		Peca newObra = obraService.findById(id);
		mv.addObject("artesaos", artistas);
		mv.addObject("peca", newObra);
		return mv;
	}

	@PostMapping(value = "/editarObra/{id}/associarArtista")
	public String editarArtistaObra(Artista artista, @PathVariable String id) {

		Peca editedObra = obraService.findById(id);

		if (artista.getId().isBlank()) {
			artista.setId(null);
			artistaService.insert(artista);
		}

		var artistaAssociado = artistaService.findById(artista.getId()); // Verifica se mudou o artesão
		var artistaOld = editedObra.getArtesao();
		
		if (!editedObra.getArtesao().equals(artistaAssociado)) {
			editedObra.getArtesao().getListaObras().remove(editedObra);
		}

		if (!artistaAssociado.getListaObras().contains(editedObra)) {
			artistaAssociado.getListaObras().addAll(Arrays.asList(editedObra));
		}

		artistaService.insert(artistaAssociado);
		artistaService.insert(editedObra.getArtesao());
		artistaService.insert(artistaOld);
		artistaService.updateObras(artistaAssociado);
		artistaService.updateObras(editedObra.getArtesao());
		artistaService.updateObras(artistaOld);

		editedObra.setArtesao(artistaAssociado);
		obraService.novaObra(editedObra);
		return "redirect:/pecas";
	}

	@GetMapping(value = "/editarObra/{idObra}/associarArtista/artista/editarArtista/{idArtista}")
	public ModelAndView editarArtista2(@PathVariable String idObra, @PathVariable String idArtista) {
		ModelAndView mv = new ModelAndView("artistas/editarArtistaFormEditObra");
		Artista artista = artistaService.findById(idArtista);
		Peca obra = obraService.findById(idObra);
		mv.addObject("artesao", artista);
		mv.addObject("obra", obra);
		return mv;
	}

	@PostMapping(value = "/editarObra/{idObra}/associarArtista/artista/editarArtista/{idArtista}")
	public String editarArtista2(Artista artista, @PathVariable String idObra, @PathVariable String idArtista) {

		artistaService.update(idArtista, artista);
		return "redirect:/pecas/editarObra/" + idObra + "/associarArtista";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable String id) {
		obraService.delete(id);
		return "redirect:/pecas";
	}

//	@PostMapping(value = "/novaObra")
//	public String inserirObra(PecaDTOForm objDTO, @RequestParam("imagemPecaFile") MultipartFile imagemPecaFile)
//			throws IOException {
//
//		ArtistaDTO artistaDTO = artistaService.findByNome(objDTO.getArtesao());
//		Artista artista = artistaService.fromDTO(artistaDTO);
//		artistaService.insert(artista);
//
//		Peca newObra = obraService.fromDTOFormulario(objDTO, artistaDTO);
//		obraService.novaObra(newObra);
//		artista.getListaObras().add(newObra);
//		artistaService.insert(artista);
//		return "redirect:/pecas";
//	}

//	@PostMapping
//	public ResponseEntity<Peca> insert(@RequestBody PecaDTO objDTO) {
//		
//		
//		
//		Artista artista = obraService.fromDTO(objDTO.getArtesao());
//		artistaService.insert(artista);
//
//		Peca obj = obraService.fromDTO(objDTO);
//		obj.getArtesao().setId(artista.getId());
//		;
//		obj = obraService.insert(obj);
//		artista.getListaObras().add(obj);
//		return ResponseEntity.ok().body(obj);
//	}
//	
//
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<PecaDTOShow> findById(@PathVariable String id) {
//
//		Peca peca = obraService.findById(id);
//		return ResponseEntity.ok().body(new PecaDTOShow(peca));
//	}
//
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable String id) {
//		obraService.delete(id);
//		return ResponseEntity.noContent().build();
//
//	}
//
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Peca> update(@PathVariable String id, @RequestBody Peca obj) {
//		obj = obraService.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}

}
