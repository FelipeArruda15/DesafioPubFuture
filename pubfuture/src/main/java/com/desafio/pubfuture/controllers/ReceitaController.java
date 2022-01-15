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

import com.desafio.pubfuture.dto.ReceitaDTO;
import com.desafio.pubfuture.model.entities.Receita;
import com.desafio.pubfuture.services.interfaces.ReceitaService;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarReceitaPorId(@PathVariable Long id) {
		ReceitaDTO receita = service.findById(id);
		return new ResponseEntity<>(receita, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> listarReceita() {
		List<ReceitaDTO> receitas = service.findAll();
		return new ResponseEntity<>(receitas, HttpStatus.OK);
	}

	@GetMapping(value = "/saldoReceitas")
	public ResponseEntity<?> saldoTotal() {
		Double saldoTotal = service.mostrarSaldoTotal();
		return new ResponseEntity<>(saldoTotal, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtroPorTipo")
	public ResponseEntity<?> buscarReceitaPorTipo(@RequestParam("tipoReceita") String tipoReceita){
		List<ReceitaDTO> receitas = service.findByTipoReceita(tipoReceita);
		return new ResponseEntity<>(receitas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtroPorData")
	public ResponseEntity<?> buscarReceitaPorDataRecebimento(@RequestParam String dataInicial, @RequestParam String dataFinal){
		List<ReceitaDTO> receitas = service.findByDate(dataInicial, dataFinal);
		return new ResponseEntity<>(receitas, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> cadastrarReceita(@RequestBody Receita receita) {
		ReceitaDTO receitaSalva = service.save(receita);
		return new ResponseEntity<>(receitaSalva, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> editarReceita(@RequestBody Receita receita) {
		ReceitaDTO receitaAtualizada =  service.update(receita);
		return new ResponseEntity<>(receitaAtualizada,HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> excluirReceita(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
