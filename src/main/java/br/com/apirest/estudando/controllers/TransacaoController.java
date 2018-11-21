package br.com.apirest.estudando.controllers;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest.estudando.exceptions.ContaBlockedException;
import br.com.apirest.estudando.exceptions.ContaDoesntExistsException;
import br.com.apirest.estudando.exceptions.InsufficientFundsException;
import br.com.apirest.estudando.exceptions.InvalidValueException;
import br.com.apirest.estudando.exceptions.LimiteSaqueDiarioException;
import br.com.apirest.estudando.services.transacao.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
	
	@PatchMapping(value = "/deposito", consumes = "application/json")
	public ResponseEntity<String> deposito(@RequestBody String payload) {
		System.out.println(payload);
		
		try {
			return ResponseEntity.ok(transacaoService.deposito(payload));
		}catch(ContaDoesntExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta para deposito não existe!");
		}catch(ContaBlockedException e2) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Conta bloqueada");
		}catch(InvalidValueException e3) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Valor Inválido");
		}
	}
	
	@PatchMapping(value = "/saque", consumes = "application/json")
	public ResponseEntity<String> saque(@RequestBody String payload){
		System.out.println(payload);
		
		try {
			return ResponseEntity.ok(transacaoService.saque(payload));
		}catch(ContaDoesntExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta para saque não existe!");
		}catch(ContaBlockedException e2) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Conta bloqueada");
		}catch(InvalidValueException e3) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Valor Inválido");
		}catch(InsufficientFundsException e4) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Saldo insuficiente");
		}catch(LimiteSaqueDiarioException e5) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Limite para saque excedito");
		}
	}
	
	@GetMapping(value = "/extratoCompleto")
	public ResponseEntity<?> extrato(@RequestParam("id") Long id){
		try {
			return ResponseEntity.ok(transacaoService.consultaExtrato(id));
		}catch(ContaDoesntExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta informada não existe");
		}
	}
	
	@ResponseBody
	@GetMapping(value = "/extratoPorPeriodo")
	public ResponseEntity<?> extratoPorPeriodo(@RequestParam("id") Long id, @RequestParam("dataInicio") Calendar dataInicio, 
						@RequestParam("dataFim") Calendar dataFim ){
		try {
			return ResponseEntity.ok(transacaoService.consultaExtratoPorPeriodo(id, dataInicio, dataFim));
		}catch(ContaDoesntExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta informada não existe");
		}
		
	}
	
	

}
