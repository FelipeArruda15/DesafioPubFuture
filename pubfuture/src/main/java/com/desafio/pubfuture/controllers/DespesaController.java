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

import com.desafio.pubfuture.dto.DespesaDTO;
import com.desafio.pubfuture.model.entities.Despesa;
import com.desafio.pubfuture.services.implementation.DespesaServiceIMPL;

@RestController
@RequestMapping(value = "/despesas")
public class DespesaController {

	@Autowired
	private DespesaServiceIMPL service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarDespesaPorId(@PathVariable Long id) {
		DespesaDTO despesa = service.findById(id);
		return new ResponseEntity<>(despesa, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> listarDespesas() {
		List<DespesaDTO> despesas = service.findAll();
		return new ResponseEntity<>(despesas, HttpStatus.OK);
	}

	@GetMapping(value = "/saldoDespesas")
	public ResponseEntity<?> saldoTotal() {
		Double saldoTotal = service.mostrarSaldoTotal();
		return new ResponseEntity<>(saldoTotal, HttpStatus.OK);
	}

	@GetMapping(value = "/filtroPorTipo")
	public ResponseEntity<?> buscarDespesaPorTipo(@RequestParam("tipoDespesa") String tipoDespesa) {
		List<DespesaDTO> despesas = service.findByTipoDespesa(tipoDespesa);
		return new ResponseEntity<>(despesas, HttpStatus.OK);
	}

	@GetMapping(value = "/filtroPorData")
	public ResponseEntity<?> buscarDespesaPorDataPagamento(@RequestParam("dataInicial") String dataInicial,
			@RequestParam("dataFinal") String dataFinal) {
		List<DespesaDTO> despesas = service.findByDate(dataInicial, dataFinal);
		return new ResponseEntity<>(despesas, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> cadastrarDespesa(@RequestBody Despesa despesa) {
		DespesaDTO despesaSalva = service.save(despesa);
		return new ResponseEntity<>(despesaSalva, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> editarDespesa(@RequestBody Despesa despesa) {
		DespesaDTO despesaAtualizada = service.update(despesa);
		return new ResponseEntity<>(despesaAtualizada, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> excluirDespesa(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
