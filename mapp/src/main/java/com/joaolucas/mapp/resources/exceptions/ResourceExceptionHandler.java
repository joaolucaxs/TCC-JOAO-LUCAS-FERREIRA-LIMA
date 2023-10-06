package com.joaolucas.mapp.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.joaolucas.mapp.services.exceptions.AccessDeniedException;
import com.joaolucas.mapp.services.exceptions.AuthenticationException;
import com.joaolucas.mapp.services.exceptions.DataBaseException;
import com.joaolucas.mapp.services.exceptions.MissingServletRequestParameterException;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;
import com.joaolucas.mapp.services.exceptions.ValidationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Página não encontrada";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return createErrorModelAndView(err);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView resourceNotFound(MissingServletRequestParameterException e, HttpServletRequest request) {
		String error = "Requisição Inválida";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return createErrorModelAndView(err);
	}

	@ExceptionHandler(DataBaseException.class)
	public ModelAndView databaseError(DataBaseException e, HttpServletRequest request) {
		String error = "Erro interno";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return createErrorModelAndView(err);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ModelAndView authenticationError(AuthenticationException e, HttpServletRequest request) {
		String error = "Erro de autenticação";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return createErrorModelAndView(err);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView acessError(AccessDeniedException e, HttpServletRequest request) {
		String error = "Acesso negado";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return createErrorModelAndView(err);
	}

	@ExceptionHandler(ValidationException.class)
	public ModelAndView validationError(ValidationException e, HttpServletRequest request) {
		String error = "Erro de validação";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return createErrorModelAndView(err);
	}

	private ModelAndView createErrorModelAndView(StandardError err) {
		ModelAndView mv = new ModelAndView("erros/paginaErro");
		mv.addObject("errorResponse", err);
		return mv;
	}
}
