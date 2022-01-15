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

import com.desafio.pubfuture.dto.DespesaDTO;
import com.desafio.pubfuture.model.entities.Despesa;
import com.desafio.pubfuture.services.implementation.DespesaServiceIMPL;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/despesas")
public class DespesaController {

	@Autowired
	private DespesaServiceIMPL service;

	@GetMapping(value = "/{id}")
	@Transactional(readOnly = true)
	@Operation(summary = "Buscar despesa por ID", description = "Retorna uma única despesa", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarDespesaPorId(@PathVariable Long id) {
		DespesaDTO despesa = service.findById(id);
		return new ResponseEntity<>(despesa, HttpStatus.OK);
	}

	@GetMapping
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as despesas", description = "Retorna um array com todas as despesas", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> listarDespesas() {
		List<DespesaDTO> despesas = service.findAll();
		return new ResponseEntity<>(despesas, HttpStatus.OK);
	}

	@GetMapping(value = "/saldoDespesas")
	@Operation(summary = "Retona o saldo total de todas as despesas", description = "Retorna o saldo de todas despesas cadastradas", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> saldoTotal() {
		Double saldoTotal = service.mostrarSaldoTotal();
		return new ResponseEntity<>(saldoTotal, HttpStatus.OK);
	}

	@GetMapping(value = "/filtroPorTipo")
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as despesas correspondentes ao filtro", description = "Retorna um array com todas as despesas que correspondem com o filtro de Tipo Despesa", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarDespesaPorTipo(@RequestParam("tipoDespesa") String tipoDespesa) {
		List<DespesaDTO> despesas = service.findByTipoDespesa(tipoDespesa);
		return new ResponseEntity<>(despesas, HttpStatus.OK);
	}

	@GetMapping(value = "/filtroPorData")
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as despesas correspondentes ao filtro de data", description = "Retorna um array com todas as despesas que correspondem com o filtro de data", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarDespesaPorDataPagamento(@RequestParam("dataInicial") String dataInicial,
			@RequestParam("dataFinal") String dataFinal) {
		List<DespesaDTO> despesas = service.findByDate(dataInicial, dataFinal);
		return new ResponseEntity<>(despesas, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Cadastra/Salva uma nova despesa", description = "Cria uma nova despesa", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "201", description = "Despesa criada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar o cadastro.") })
	public ResponseEntity<?> cadastrarDespesa(@RequestBody Despesa despesa) {
		DespesaDTO despesaSalva = service.save(despesa);
		return new ResponseEntity<>(despesaSalva, HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Altera uma despesa", description = "Altera uma despesa", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Despesa alterada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar alteração.") })
	public ResponseEntity<?> editarDespesa(@RequestBody Despesa despesa) {
		DespesaDTO despesaAtualizada = service.update(despesa);
		return new ResponseEntity<>(despesaAtualizada, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Exclui uma despesa", description = "Exclui uma despesa", tags = { "Despesas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Despesa excluida o com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar exclusão.") })
	public ResponseEntity<?> excluirDespesa(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
