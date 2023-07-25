package com.joaolucas.mapp.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.PecaRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private PecaRepository pecaRepository;

	@Override
	public void run(String... args) throws Exception {

		pecaRepository.deleteAll();
		
		Peca peca1 = new Peca(null,
				new Artista(null, "Lenilda Leal", "lele", "(28) 98123-1675", "lenilda@gmail.com", "Campina Grande"),
				"Vendedor de Algodão Doce", "Fios", "", "", "", "Arte Popular", "Decorativo",
				new FichaTecnicaObra(null, "Imagem", "FP 001", new Date(), true, true, "07x18x07", "2kg"));

		Peca peca2 = new Peca(null, 
				new Artista(null, null, null, null, null, null), "Santa Ceia", "Fios", "", "", "",
				"Arte Popular", "Decorativo",
				new FichaTecnicaObra(null, "Imagem", "FP 002", new Date(), false, false, "65x16x11", "10kg"));

		pecaRepository.saveAll(Arrays.asList(peca1, peca2));

	}

}