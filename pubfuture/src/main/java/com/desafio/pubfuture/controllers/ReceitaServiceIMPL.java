package com.desafio.pubfuture.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.pubfuture.dto.ReceitaDTO;
import com.desafio.pubfuture.exceptions.EntityNotFoundException;
import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.entities.Receita;
import com.desafio.pubfuture.repositories.ContaRepository;
import com.desafio.pubfuture.repositories.ReceitaRepository;
import com.desafio.pubfuture.services.interfaces.ReceitaService;
import com.desafio.pubfuture.utils.ConverterData;

@Service
public class ReceitaServiceIMPL implements ReceitaService {

	@Autowired
	private ReceitaRepository repository;

	@Autowired
	private ContaRepository repositoryConta;

	@Override
	public ReceitaDTO findById(Long id) {
		verificarReceitaExistente(id);
		Receita receita = repository.findById(id).get();
		return new ReceitaDTO(receita);
	}

	@Override
	public List<ReceitaDTO> findAll() {
		List<Receita> receitas = repository.findAll();
		return receitas.stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
	}

	@Override
	public ReceitaDTO save(Receita receita) {
		Receita receitaSalva = repository.save(receita);

		Conta conta = contaVinculada(receitaSalva.getConta().getId());
		conta.addReceita(receitaSalva);
		repositoryConta.save(conta);

		return new ReceitaDTO(receitaSalva);
	}

	@Override
	public void delete(Long id) {
		verificarReceitaExistente(id);
		repository.deleteById(id);
	}

	@Override
	public ReceitaDTO update(Receita receita) {
		verificarReceitaExistente(receita.getId());
		Receita receitaAtualizada = repository.save(receita);
		return new ReceitaDTO(receitaAtualizada);
	}

	@Override
	public Double mostrarSaldoTotal() {
		Double saldoTotal = 0.0;
		List<Receita> receitas = repository.findAll();
		for (Receita receita : receitas) {
			saldoTotal += receita.getValor();
		}
		return saldoTotal;
	}

	@Override
	public List<ReceitaDTO> findByTipoReceita(String tipoReceita) {
		List<Receita> receitas = repository.findByTipoReceitaIgnoreCase(tipoReceita);
		return receitas.stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
	}

	@Override
	public List<ReceitaDTO> findByDate(String dataInicial, String dataFinal){
			String dtInicio = ConverterData.ConverterParaSQL(dataInicial);
			String dtFinal = ConverterData.ConverterParaSQL(dataFinal);
			List<Receita> receitas = repository.findByDataRecebimento(dtInicio, dtFinal);
			return receitas.stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());	
	}

	public Conta contaVinculada(Long id) {
		Conta conta = repositoryConta.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Conta não encontrada " + id));
		return conta;
	}

	public void verificarReceitaExistente(Long id) {
		 	repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id não encontrado " + id));
	}

}
