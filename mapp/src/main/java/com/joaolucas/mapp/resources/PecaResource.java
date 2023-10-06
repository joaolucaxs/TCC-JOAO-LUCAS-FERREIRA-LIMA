package com.joaolucas.mapp.resources;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joaolucas.mapp.dtos.FileDTOShow;
import com.joaolucas.mapp.dtos.PecaDTOForm;
import com.joaolucas.mapp.dtos.PecaDTOShow;
import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.resources.util.MD5Util;
import com.joaolucas.mapp.services.ArtistaService;
import com.joaolucas.mapp.services.FileService;
import com.joaolucas.mapp.services.PecaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/pecas")
public class PecaResource {

	@Autowired
	private PecaService obraService;

	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private FileService fileService;

	private Peca auxPecaEdition;

	private Peca auxPecaNew = new Peca();

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

		List<Peca> pecas = obraService.filtrarPorCampo(filtro, pesquisa);
		List<PecaDTOShow> pecasDto = pecas.stream().map(peca -> new PecaDTOShow(peca)).collect(Collectors.toList());

		ModelAndView mv = new ModelAndView("obras/listarObras");
		mv.addObject("pecas", pecasDto);
		return mv;
	}

	@GetMapping(value = "/{id}")
	public ModelAndView visualizarObra(@PathVariable String id) {
		Peca obra = obraService.findById(id);
		List<File> files = fileService.listAllFilesByIdObra(id);
		List<FileDTOShow> filesDto = files.stream().map(file -> new FileDTOShow(file)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("obras/visualizarObra");
		mv.addObject("files", filesDto);
		mv.addObject("peca", obra);
		return mv;
	}

	@GetMapping(value = "/novaObra")
	public ModelAndView novaObra() {
		ModelAndView mv = new ModelAndView("obras/novaObra");
		return mv;
	}

	@PostMapping(value = "/novaObra")
	public String inserirObra(@Valid PecaDTOForm objDTO,
			@RequestParam("imagemPecaFile") @Valid MultipartFile imagemPecaFile, BindingResult result)
			throws IOException {
		if (result.hasErrors()) {
			return "redirect:/pecas";
		}
		Peca newObra = obraService.fromDTOFormulario(objDTO);
		auxPecaNew = newObra;
		return "redirect:/pecas/novaObra/associarArtista";
	}

	@GetMapping(value = "/novaObra/associarArtista")
	public ModelAndView novaObraFormulario() {
		ModelAndView mv = new ModelAndView("artistas/novaObraArtistaForm");
		List<Artista> artistas = artistaService.findAll();
		Peca newObra = auxPecaNew;
		mv.addObject("artesaos", artistas);
		mv.addObject("peca", newObra);
		return mv;
	}

	@PostMapping(value = "/novaObra/associarArtista")
	public String inserirArtistaObra(@Valid Artista artista, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/pecas";
		}

		Peca newObra = auxPecaNew;

		if (artista.getId().isBlank()) {
			artista.setId(null);
			artistaService.insert(artista);
		}

		var artistaAssociado = artistaService.findById(artista.getId());

		obraService.novaObra(newObra);
		artistaAssociado.getListaObras().addAll(Arrays.asList(newObra));
		artistaService.updateObras(artistaAssociado);
		newObra.setArtesao(artistaAssociado);
		obraService.novaObra(newObra);
		artistaService.insert(artistaAssociado);
		return "redirect:/pecas";
	}

	@GetMapping(value = "/novaObra/associarArtista/artista/editarArtista/{idArtista}")
	public ModelAndView editarArtista(@PathVariable String idArtista) {
		ModelAndView mv = new ModelAndView("artistas/editarArtistaFormNewObra");
		Artista artista = artistaService.findById(idArtista);
		Peca obra = auxPecaNew;
		mv.addObject("artesao", artista);
		mv.addObject("obra", obra);
		return mv;
	}

	@PostMapping(value = "/novaObra/associarArtista/artista/editarArtista/{idArtista}")
	public String editarArtista(@Valid Artista artista, @PathVariable String idArtista, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/pecas";
		}

		artistaService.update(idArtista, artista);
		return "redirect:/pecas/novaObra/associarArtista";
	}

	@GetMapping(value = "/editarObra/{id}")
	public ModelAndView editarObra(@PathVariable String id) {
		Peca editObra = obraService.findById(id);
		ModelAndView mv = new ModelAndView("obras/editarObra");
		mv.addObject("peca", editObra);
		return mv;
	}

	@PostMapping(value = "/editarObra/{id}")
	public String editarObra(@PathVariable String id, @Valid PecaDTOForm objDTO,
			@RequestParam("imagemPecaFile") @Valid MultipartFile imagemPecaFile, BindingResult result)
			throws IOException {

		if (result.hasErrors()) {
			return "redirect:/pecas";
		}

		Peca obraPersisted = obraService.findById(id);
		auxPecaEdition = obraPersisted;
		Peca newObraEdited = obraService.fromDTOFormulario(objDTO);
		if (imagemPecaFile.isEmpty()) {
			newObraEdited.getFichatecnica().setImagemCapa(obraPersisted.getFichatecnica().getImagemCapa());
		}
		auxPecaEdition = newObraEdited;
		auxPecaEdition.setArtesao(obraPersisted.getArtesao());
		auxPecaEdition.setId(id);
		return "redirect:/pecas/editarObra/" + id + "/associarArtista";
	}

	@GetMapping(value = "/editarObra/{id}/associarArtista")
	public ModelAndView editarObraFormulario(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("artistas/editarObraArtistaForm");
		List<Artista> artistas = artistaService.findAll();
		Peca obraEdited = auxPecaEdition;
		mv.addObject("artesaos", artistas);
		mv.addObject("peca", obraEdited);
		return mv;
	}

	@PostMapping(value = "/editarObra/{id}/associarArtista")
	public String editarArtistaObra(@Valid Artista artista, @PathVariable String id, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/pecas";
		}

		Peca editedObra = auxPecaEdition;

		if (artista.getId().isBlank()) {
			artista.setId(null);
			artistaService.insert(artista);
		}

		var artistaAssociado = artistaService.findById(artista.getId());
		var artistaOld = editedObra.getArtesao();

		if (!artistaOld.equals(artistaAssociado)) {
			editedObra.getArtesao().getListaObras().remove(editedObra);
		}

		if (!artistaAssociado.getListaObras().contains(editedObra)) {
			artistaAssociado.getListaObras().addAll(Arrays.asList(editedObra));
		}

		artistaService.updateObras(artistaOld);
		artistaService.updateObras(artistaAssociado);
		artistaService.insert(artistaOld);
		artistaService.insert(artistaAssociado);

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
	public String editarArtista2(@Valid Artista artista, @PathVariable String idObra, @PathVariable String idArtista,
			BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/pecas";
		}

		artistaService.update(idArtista, artista);

		return "redirect:/pecas/editarObra/" + idObra + "/associarArtista";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable String id) {
		obraService.delete(id);
		return "redirect:/pecas";
	}

	@GetMapping(value = "/enviarMidia/{id}")
	public ModelAndView novoArquivo(@PathVariable String id) {
		Peca obra = obraService.findById(id);
		ModelAndView mv = new ModelAndView("arquivos/novoArquivo");
		mv.addObject("peca", obra);
		return mv;
	}

	@PostMapping(value = "/enviarMidia/{idObra}")
	public String novoArquivo(@RequestParam("file") @Valid MultipartFile file, @PathVariable String idObra,
			@RequestParam("nomeArquivo") String nomeArquivo) throws IOException {
		try {

			File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),
					new Binary(file.getBytes()));
			f.setMd5(MD5Util.getMD5(file.getInputStream()));
			f.setIdObra(idObra);
			f.setName(nomeArquivo);
			f.setUploadDate(LocalDate.now());
			fileService.saveFile(f);
			return "redirect:/pecas/" + idObra;

		} catch (IOException | NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return "redirect:/pecas";
		}
	}

	@GetMapping(value = "/deletarMidia/{idObra}/{idMidia}")
	public String deleteArquivo(@PathVariable String idObra, @PathVariable String idMidia) {
		fileService.deleteFile(idMidia);
		return "redirect:/pecas/" + idObra;
	}

}
