package com.desafio.pubfuture.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.pubfuture.dto.ContaDTO;
import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.entities.Transferencia;
import com.desafio.pubfuture.services.implementation.ContaServiceIMPL;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

	@Autowired
	private ContaServiceIMPL service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarContasPorId(@PathVariable Long id) {
		ContaDTO conta = service.findById(id);
		return new ResponseEntity<>(conta, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> listarContas() {
		List<ContaDTO> contas = service.findAll();
		return new ResponseEntity<>(contas, HttpStatus.OK);
	}

	@GetMapping(value = "/saldoConta/{id}")
	public ResponseEntity<?> saldoTotal(@PathVariable Long id) {
		Double saldo =  service.mostrarSaldoTotal(id);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtroPorInstituicao")
	public ResponseEntity<?> buscarContasPorInstituicao(@RequestParam("instituicao") String instituicao){
		List<ContaDTO> contas = service.findByInstituicaoFinanceira(instituicao);
		return new ResponseEntity<>(contas, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> cadastrarConta(@RequestBody Conta conta) {
		ContaDTO contaSalva = service.save(conta);
		return new ResponseEntity<>(contaSalva, HttpStatus.CREATED);
	}
	
	@PostMapping("/transferencia")
	public ResponseEntity<?> tranferencia(@RequestBody Transferencia transferencia) {
		service.transferencia(transferencia);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> editarConta(@RequestBody Conta conta) {
		ContaDTO contaAtualizada = service.update(conta);
		return new ResponseEntity<>(contaAtualizada, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> excluirConta(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
