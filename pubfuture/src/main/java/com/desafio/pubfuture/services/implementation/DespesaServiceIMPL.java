package com.desafio.pubfuture.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.pubfuture.dto.DespesaDTO;
import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.entities.Despesa;
import com.desafio.pubfuture.model.entities.Receita;
import com.desafio.pubfuture.repositories.ContaRepository;
import com.desafio.pubfuture.repositories.DespesaRepository;
import com.desafio.pubfuture.services.interfaces.DespesaService;
import com.desafio.pubfuture.utils.ConverterData;

@Service
public class DespesaServiceIMPL implements DespesaService {

	@Autowired
	private DespesaRepository repository;

	@Autowired
	private ContaRepository repositoryConta;

	@Override
	public DespesaDTO findById(Long id) {
		Despesa despesa = repository.findById(id).get();
		return new DespesaDTO(despesa);
	}

	@Override
	public List<DespesaDTO> findAll() {
		List<Despesa> despesas = repository.findAll();
		return despesas.stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());
	}

	@Override
	public DespesaDTO save(Despesa despesa) {
		Despesa despesaSalva = repository.save(despesa);

		Conta conta = contaVinculada(despesaSalva.getConta().getId());
		conta.addDespesa(despesaSalva);
		repositoryConta.save(conta);

		return new DespesaDTO(despesaSalva);
	}

	@Override
	public void delete(Long id) {
		verificarDespesaExistente(id);
		Despesa despesaExcluida = repository.findById(id).get();
		repository.deleteById(id);
		Conta conta = contaVinculada(despesaExcluida.getId());
		conta.removeDespesa(despesaExcluida);
	}

	@Override
	public DespesaDTO update(Despesa despesa) {
		verificarDespesaExistente(despesa.getId());
		Despesa despesaAtualizada = repository.save(despesa);
		despesaAtualizada.getConta().setSaldo(null);
		repositoryConta.save(despesaAtualizada.getConta());
		return new DespesaDTO(despesaAtualizada);
	}

	@Override
	public Double mostrarSaldoTotal() {
		Double saldoTotal = 0.0;
		List<Despesa> despesas = repository.findAll();
		for (Despesa despesa : despesas) {
			saldoTotal += despesa.getValor();
		}
		return saldoTotal;
	}

	@Override
	public List<DespesaDTO> findByTipoDespesa(String tipoDespesa) {
		List<Despesa> despesas = repository.findByTipoDespesaIgnoreCase(tipoDespesa);
		return despesas.stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());
	}
	
	@Override
	public List<DespesaDTO> findByDate(String dataInicial, String dataFinal) {

		String dtInicio = ConverterData.ConverterParaSQL(dataInicial);
		String dtFinal = ConverterData.ConverterParaSQL(dataFinal);
		List<Despesa> despesas = repository.findByDataPagamento(dtInicio, dtFinal);
		return despesas.stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());

	}

	private Conta contaVinculada(Long id) {
		Conta conta = repositoryConta.getById(id);
		return conta;
	}

	private void verificarDespesaExistente(Long id) {
		Optional<Despesa> despesa = repository.findById(id);
		if (!despesa.isPresent()) {
			throw new NullPointerException("Despesa não encontrada com o ID: " + id);
		}
	}

}
