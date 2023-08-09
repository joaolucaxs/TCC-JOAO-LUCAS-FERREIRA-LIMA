package com.joaolucas.mapp.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaolucas.mapp.dtos.ArtistaDTO;
import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.Peca;
import com.joaolucas.mapp.repositories.ArtistaRepository;
import com.joaolucas.mapp.repositories.PecaRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private PecaRepository pecaRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;

	@Override
	public void run(String... args) throws Exception {

		pecaRepository.deleteAll();
		artistaRepository.deleteAll();
		
		Artista artista1 = new Artista(null, "Lenilda Leal", "lele", "(28) 98123-1675", "lenilda@gmail.com",
				"Campina Grande");
		Artista artista2 = new Artista(null, "Dogival Silva", "dodo", "(87) 96125-5655", "dogival@gmail.com", "Sousa");

		artistaRepository.saveAll(Arrays.asList(artista1, artista2));
		
		Peca peca1 = new Peca(null, new ArtistaDTO(artista1), "Vendedor de Algodão Doce", "Fios", "", "", "", "Arte Popular",
				"Decorativo", new FichaTecnicaObra("Imagem", "FP 001", LocalDate.of(2023, 8, 15), true, true, "07x18x07", "2kg"));

		Peca peca2 = new Peca(null, new ArtistaDTO(artista2), "Santa Ceia", "Fios", "", "", "",
				"Arte Popular", "Decorativo",
				new FichaTecnicaObra("Imagem", "FP 002", LocalDate.of(2023, 8, 16), false, false, "65x16x11", "10kg"));
		
		Peca peca3 = new Peca(null, new ArtistaDTO(artista1), "Sitio", "Papel", "", "", "", "Arte Popular",
				"Decorativo", new FichaTecnicaObra("Imagem", "FP 001", LocalDate.of(2023, 8, 17), false, true, "53x73x80", "7kg"));
		
		Peca peca4 = new Peca(null, new ArtistaDTO(artista1), "Sitio", "Papel", "", "", "", "Arte Popular",
				"Decorativo", new FichaTecnicaObra("Imagem", "FP 001", LocalDate.of(2023, 8, 18), false, false, "53x73x80", "7kg"));
		
		Peca peca5 = new Peca(null, new ArtistaDTO(artista1), "Sitio", "Papel", "", "", "", "Arte Popular",
				"Decorativo", new FichaTecnicaObra("Imagem", "FP 001", LocalDate.of(2023, 8, 18), false, false, "53x73x80", "7kg"));
		

		pecaRepository.saveAll(Arrays.asList(peca1, peca2, peca3,peca4, peca5));
		
		artista1.getListaObras().addAll(Arrays.asList(peca1, peca3));
		artista2.getListaObras().addAll(Arrays.asList(peca2, peca4, peca5));
		
		artistaRepository.saveAll(Arrays.asList(artista1, artista2));


		

	}

}