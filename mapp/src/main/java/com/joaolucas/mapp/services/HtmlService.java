package com.joaolucas.mapp.services;

import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.model.Html;
import com.joaolucas.mapp.repositories.HtmlRepository;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

@Service
public class HtmlService {

	@Autowired
	private HtmlRepository htmlRepository;

	public Html saveHtml(Html html) {
		return htmlRepository.save(html);
	}

	public Html getHtmlById(String id) {
		Optional<Html> obj = htmlRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Conteúdo com ID " + id + " não foi encontrado."));
	}

	public String limparConteudo(String html) {
		// Parse do HTML usando o Jsoup
		Document doc = Jsoup.parse(html);

		// Remover espaços em branco desnecessários
		doc.outputSettings().prettyPrint(false);

		// Remover ícones e botões indesejados
		doc.select("i, button").remove();

//		Elements elementsToRemove = doc.select("[ondrop], [ondragover], [ondragenter], [ondragleave]");

//		elementsToRemove.remove();

		doc.select("[contenteditable]").removeAttr("contenteditable");

		// Obter o conteúdo limpo
		String conteudoLimpo = doc.select("div.presentation-content").html();

		return conteudoLimpo;
	}

}
