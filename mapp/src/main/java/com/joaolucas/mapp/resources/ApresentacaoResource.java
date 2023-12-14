package com.joaolucas.mapp.resources;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joaolucas.mapp.dtos.ApresentacaoDTOForm;
import com.joaolucas.mapp.dtos.FileApresentacaoDTOShow;
import com.joaolucas.mapp.dtos.FileDTOShow;
import com.joaolucas.mapp.model.Apresentacao;
import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.services.ApresentacaoService;
import com.joaolucas.mapp.services.FileService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/apresentacoes")
public class ApresentacaoResource {

	@Autowired
	private ApresentacaoService apresentacaoService;
	
	@Autowired
	private FileService fileService;

	private Apresentacao auxApresentacaoNew = new Apresentacao();
	
	@GetMapping()
	public ModelAndView findAll() {
		List<Apresentacao> apresentacoes = apresentacaoService.findAll();
		ModelAndView mv = new ModelAndView("apresentacoes/listarApresentacoes");
		mv.addObject("apresentacoes", apresentacoes);
		return mv;
	}
	
	@GetMapping(value = "/filtrar")
	public ModelAndView filtrarApresentacoes(@RequestParam(value = "filtro") String filtro,
			@RequestParam(value = "pesquisa") String pesquisa) {

		List<Apresentacao> apresentacoes = apresentacaoService.filtrarPorCampo(filtro, pesquisa);
		ModelAndView mv = new ModelAndView("apresentacoes/listarApresentacoes");
		mv.addObject("apresentacoes", apresentacoes);
		return mv;
	}
	
	@GetMapping(value = "/criarApresentacao")
	public ModelAndView novaObra() {
		ModelAndView mv = new ModelAndView("apresentacoes/criarApresentacao");
		return mv;
	}

	@PostMapping(value = "/criarApresentacao")
	public String criarApresentacao(@Valid ApresentacaoDTOForm apresentacao,
			@RequestParam("imagemApresentacaoFile") @Valid MultipartFile imagemPecaFile, BindingResult result)
			throws IOException {
		if (result.hasErrors()) {
			return "redirect:/apresentacoes";
		}
		Apresentacao newApresentacao = apresentacaoService.fromDTOFormulario(apresentacao);
		auxApresentacaoNew = newApresentacao;
		return "redirect:/apresentacoes/criarApresentacao/montagem";
	}

	@GetMapping(value = "/criarApresentacao/montagem")
	public ModelAndView montagemApresentacao() {
		ModelAndView mv = new ModelAndView("apresentacoes/montagemApresentacao");
		Apresentacao montagemApresentacao = auxApresentacaoNew;
		List<FileApresentacaoDTOShow> imagensDTO = fileService.listAllImagesDTO();
		List<FileApresentacaoDTOShow> videosDTO = fileService.listAllVideosDTO();
		List<FileApresentacaoDTOShow> audiosDTO = fileService.listAllAudiosDTO();
		mv.addObject("apresentacao", montagemApresentacao);
		mv.addObject("audios", audiosDTO);
		mv.addObject("videos", videosDTO);
		mv.addObject("imagens", imagensDTO);
		return mv;
	}

	@PostMapping(value = "/criarApresentacao/montagem")
	public String inserirMontagem(@Valid Apresentacao apresentacao, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/apresentacoes";
		}
		Apresentacao newApresentacao = auxApresentacaoNew;

		apresentacaoService.novaApresentacao(newApresentacao);
		return "redirect:/apresentacoes";
	}

}
