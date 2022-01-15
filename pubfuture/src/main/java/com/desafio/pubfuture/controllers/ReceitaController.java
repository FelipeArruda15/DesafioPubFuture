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

import com.desafio.pubfuture.dto.ReceitaDTO;
import com.desafio.pubfuture.model.entities.Receita;
import com.desafio.pubfuture.services.interfaces.ReceitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService service;

	@GetMapping(value = "/{id}")
	@Transactional(readOnly = true)
	@Operation(summary = "Buscar receita por ID", description = "Retorna uma única receita", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarReceitaPorId(@PathVariable Long id) {
		ReceitaDTO receita = service.findById(id);
		return new ResponseEntity<>(receita, HttpStatus.OK);
	}

	@GetMapping
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as receitas", description = "Retorna um array com todas as receitas", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> listarReceita() {
		List<ReceitaDTO> receitas = service.findAll();
		return new ResponseEntity<>(receitas, HttpStatus.OK);
	}

	@GetMapping(value = "/saldoReceitas")
	@Transactional(readOnly = true)
	@Operation(summary = "Retona o saldo total de todas as receitas", description = "Retorna o saldo de todas receitas cadastradas", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> saldoTotal() {
		Double saldoTotal = service.mostrarSaldoTotal();
		return new ResponseEntity<>(saldoTotal, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtroPorTipo")
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as receitas correspondentes ao filtro", description = "Retorna um array com todas as receitas que correspondem com o filtro de Tipo Receita", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarReceitaPorTipo(@RequestParam("tipoReceita") String tipoReceita){
		List<ReceitaDTO> receitas = service.findByTipoReceita(tipoReceita);
		return new ResponseEntity<>(receitas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtroPorData")
	@Transactional(readOnly = true)
	@Operation(summary = "Listar todas as receitas correspondentes ao filtro de data", description = "Retorna um array com todas as receitas que correspondem com o filtro de data", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar a busca.") })
	public ResponseEntity<?> buscarReceitaPorDataRecebimento(@RequestParam String dataInicial, @RequestParam String dataFinal){
		List<ReceitaDTO> receitas = service.findByDate(dataInicial, dataFinal);
		return new ResponseEntity<>(receitas, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Cadastra/Salva uma nova receita", description = "Cria uma nova receita", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "201", description = "Receita criada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar o cadastro.") })
	public ResponseEntity<?> cadastrarReceita(@RequestBody Receita receita) {
		ReceitaDTO receitaSalva = service.save(receita);
		return new ResponseEntity<>(receitaSalva, HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Altera uma receita", description = "Altera uma receita", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Receita alterada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar alteração.") })
	public ResponseEntity<?> editarReceita(@RequestBody Receita receita) {
		ReceitaDTO receitaAtualizada =  service.update(receita);
		return new ResponseEntity<>(receitaAtualizada,HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Exclui uma receita", description = "Exclui uma receita", tags = { "Receitas Controller" }, responses = {
			@ApiResponse(responseCode = "200", description = "Receita excluida o com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro ao realizar exclusão.") })
	public ResponseEntity<?> excluirReceita(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
