package com.financeiro.exceptionHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageResource;
		
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String menssagemUsuario = messageResource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor =  ex.getCause() !=null ? ex.getCause().toString() : ex.toString();
		
		List<Erro> erros =  Arrays.asList( new Erro(menssagemUsuario,menssagemDesenvolvedor));
		return handleExceptionInternal(ex,erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object>handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex,WebRequest request){
		
		String menssagemUsuario = messageResource.getMessage("recurso.nao-encontrado",null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor =  ex.toString();
		
		List<Erro> erros =  Arrays.asList(new Erro(menssagemUsuario,menssagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(),HttpStatus.NOT_FOUND, request);
		
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros =  listaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro>listaDeErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError: bindingResult.getFieldErrors() ) {
			
			String mensagemUsuario =messageResource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor=fieldError.toString();
			
			erros.add(new Erro(mensagemUsuario,mensagemDesenvolvedor));
			
		}
		
		return erros;
		
	}
	
	public static class Erro{
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

		public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		
	}
	
}
