package br.com.apirest.estudando.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest.estudando.exceptions.ContaDoesntExistsException;
import br.com.apirest.estudando.exceptions.PessoaDoesntExistsException;
import br.com.apirest.estudando.services.conta.ContaService;


@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	
	@ResponseBody
	@PostMapping(value = "/cadastarConta", consumes = "application/json")
	public ResponseEntity<String> cadastrar(@RequestBody String payload) {
		System.out.println(payload);
		
		try {
			return ResponseEntity.ok(contaService.criaConta(payload));
		}catch(PessoaDoesntExistsException e ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não existe para cadastrar uma conta!");
		}
	}
	
	
	@ResponseBody
	@GetMapping("/consulta")
	public ResponseEntity<String> consulta(@RequestParam("id") Long id){
		System.out.println(id);
		
		try {
			return ResponseEntity.ok(contaService.consultaSaldo(id));
		}catch(ContaDoesntExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta informada não existe!");
		}
	}
	
	@PatchMapping(value = "/bloquearOuDesbloquearConta")
	public ResponseEntity<String> bloquearOuDesbloquearConta(@RequestParam("id") Long id){
		try {
			return ResponseEntity.ok(contaService.bloquearOuDesbloquearConta(id));
		}catch (ContaDoesntExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta informada nao existe");
		}
	}
}
