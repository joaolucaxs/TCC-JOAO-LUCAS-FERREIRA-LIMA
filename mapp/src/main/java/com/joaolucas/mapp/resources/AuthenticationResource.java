package com.joaolucas.mapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.joaolucas.mapp.dtos.LoginResponseDTO;
import com.joaolucas.mapp.dtos.UsuarioDTO;
import com.joaolucas.mapp.infra.security.TokenService;
import com.joaolucas.mapp.model.Usuario;
import com.joaolucas.mapp.repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/gerenciamento")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository repository;
	@Autowired
	private TokenService tokenService;

	@GetMapping("/login")
	public ModelAndView paginaLogin() {
		ModelAndView mv = new ModelAndView("autorizacao/login");
		return mv;
	}

	@PostMapping("/login")
	public String login(@Valid UsuarioDTO user) {
		var emailSenha = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getSenha());
		var auth = authenticationManager.authenticate(emailSenha);
		var token = tokenService.generateToken((Usuario) auth.getPrincipal());
		ResponseEntity.ok(new LoginResponseDTO(token));
		boolean isAdmin = auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
		if (isAdmin) {
			return "redirect:/pecas";
		} else {
			return "redirect:/apresentacoes";
		}
	}

	@GetMapping("/criarUsuario")
	public ModelAndView paginaCriarUsuario() {
		ModelAndView mv = new ModelAndView("autorizacao/registrar");
		return mv;
	}

	@PostMapping("/criarUsuario")
	public String criarUsuario(@Valid UsuarioDTO data) {

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
		Usuario newUser = new Usuario(data.getEmail(), encryptedPassword, data.getRole());

		this.repository.save(newUser);

		return "redirect:/gerenciamento/login";
	}
}
