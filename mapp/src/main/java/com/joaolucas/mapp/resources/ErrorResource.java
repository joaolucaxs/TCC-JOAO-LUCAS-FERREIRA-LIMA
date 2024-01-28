package com.joaolucas.mapp.resources;

import java.time.Instant;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.joaolucas.mapp.resources.exceptions.StandardError;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorResource implements ErrorController {

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
		String error = "Requisição Inválida";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, "URL inválida",
				request.getRequestURI());

		ModelAndView mv = new ModelAndView("erros/paginaErro");
		mv.addObject("errorResponse", err);
		return mv;
	}
}
