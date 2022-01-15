package com.desafio.pubfuture.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

	@Autowired
	private ContaServiceIMPL service;

	@GetMapping(value = "/{id}")
	@Transactional(readOnly = true)
	@Operation(summary = "Buscar conta por ID", description = "Retorna uma única conta", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarContasPorId(@PathVariable Long id) {
		ContaDTO conta = service.findById(id);
		return new ResponseEntity<>(conta, HttpStatus.OK);
	}

	@GetMapping
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as contas", description = "Retorna um array com todas as contas", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> listarContas() {
		List<ContaDTO> contas = service.findAll();
		return new ResponseEntity<>(contas, HttpStatus.OK);
	}

	
	@GetMapping(value = "/saldoConta/{id}")
	@Transactional(readOnly = true)
	@Operation(summary = "Retona o saldo de uma conta", description = "Retorna o saldo de uma única conta corresponde ao ID passado como parâmetro", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> saldoTotal(@PathVariable Long id) {
		Double saldo = service.mostrarSaldoTotal(id);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}

	@GetMapping(value = "/filtroPorInstituicao")
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as contas correspondentes ao filtro", description = "Retorna um array com todas as contas que correspondem com o filtro", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarContasPorInstituicao(@RequestParam("instituicao") String instituicao) {
		List<ContaDTO> contas = service.findByInstituicaoFinanceira(instituicao);
		return new ResponseEntity<>(contas, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Cadastra/Salva uma nova conta", description = "Cria uma nova conta", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "201", description = "Conta criada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar o cadastro.") })
	public ResponseEntity<?> cadastrarConta(@RequestBody Conta conta) {
		ContaDTO contaSalva = service.save(conta);
		return new ResponseEntity<>(contaSalva, HttpStatus.CREATED);
	}

	@PostMapping("/transferencia")
	@Operation(summary = "Realiza transferência", description = "Realiza transferência entre duas contas", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a transferência.") })
	public ResponseEntity<?> tranferencia(@RequestBody Transferencia transferencia) {
		service.transferencia(transferencia);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	@Operation(summary = "Altera uma conta", description = "Altera uma conta", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Conta alterada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar alteração.") })
	public ResponseEntity<?> editarConta(@RequestBody Conta conta) {
		ContaDTO contaAtualizada = service.update(conta);
		return new ResponseEntity<>(contaAtualizada, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Exclui uma conta", description = "Exclui uma conta", tags = { "Contas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Conta excluida o com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar exclusão.") })
	public ResponseEntity<?> excluirConta(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
