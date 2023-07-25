package com.joaolucas.mapp.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaolucas.mapp.model.Artista;
import com.joaolucas.mapp.model.FichaTecnicaObra;
import com.joaolucas.mapp.model.Peca;

@RestController
@RequestMapping(value="/pecas")
public class PecaResource {

	@GetMapping
	public ResponseEntity<List<Peca>> findAll(){
		
		
		Peca peca1 = new Peca(1l, 
				new Artista(1l, "Lenilda Leal", "lele", "(28) 98123-1675", "lenilda@gmail.com", "Campina Grande"),
				"Vendedor de Algodão Doce", "Fios", "", "", "", "Arte Popular", "Decorativo", 
				new FichaTecnicaObra(1l, "Imagem", "FP 001", new Date(), true, true, "07x18x07", "2kg"));
		
		Peca peca2 = new Peca(2l, 
				new Artista(null, null, null, null, null, null),
				"Santa Ceia", "Fios", "", "", "", "Arte Popular", "Decorativo", 
				new FichaTecnicaObra(2l, "Imagem", "FP 002", new Date(), false, false, "65x16x11", "10kg"));
		
		List<Peca> pecas = new ArrayList<>();
		pecas.addAll(Arrays.asList(peca1,peca2));
		
		return ResponseEntity.ok().body(pecas);
	}
}
