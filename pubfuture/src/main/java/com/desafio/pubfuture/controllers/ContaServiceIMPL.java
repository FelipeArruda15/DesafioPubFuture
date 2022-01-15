package com.desafio.pubfuture.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.pubfuture.dto.ContaDTO;
import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.entities.Despesa;
import com.desafio.pubfuture.model.entities.Receita;
import com.desafio.pubfuture.model.entities.Transferencia;
import com.desafio.pubfuture.model.enums.TipoDespesa;
import com.desafio.pubfuture.model.enums.TipoReceita;
import com.desafio.pubfuture.repositories.ContaRepository;
import com.desafio.pubfuture.repositories.DespesaRepository;
import com.desafio.pubfuture.repositories.ReceitaRepository;
import com.desafio.pubfuture.services.interfaces.ContaService;

@Service
public class ContaServiceIMPL implements ContaService {

	@Autowired
	private ContaRepository repository;

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private ReceitaRepository receitaRepository;

	public ContaDTO findById(Long id) {
		verificarContaExistente(id);
		Conta conta = repository.findById(id).get();
		return new ContaDTO(conta);
	}

	@Override
	public List<ContaDTO> findAll() {
		List<Conta> contas = repository.findAll();
		return contas.stream().map(c -> new ContaDTO(c)).collect(Collectors.toList());
	}

	@Override
	public ContaDTO save(Conta conta) {
		Conta contaSalva = repository.save(conta);
		return new ContaDTO(contaSalva);
	}

	@Override
	public void delete(Long id) {
		verificarContaExistente(id);
		repository.deleteById(id);
	}

	@Override
	public ContaDTO update(Conta conta) {
		verificarContaExistente(conta.getId());
		Conta contaAtualizada = repository.save(conta);
		return new ContaDTO(contaAtualizada);
	}

	@Override
	public void transferencia(Transferencia transferencia) {
		verificarContaExistente(transferencia.getContaRemetente().getId());
		verificarContaExistente(transferencia.getContaDestino().getId());

		Conta contaRemetente = repository.findById(transferencia.getContaRemetente().getId()).get();
		Conta contaDestino = repository.findById(transferencia.getContaDestino().getId()).get();

		if (saldoSuficiente(contaRemetente, transferencia.getValor())) {
			criarDespesa(contaRemetente, transferencia.getValor());
			criarReceita(contaDestino, transferencia.getValor());
		}

	}

	@Override
	public Double mostrarSaldoTotal(Long id) {
		Conta conta = repository.findById(id).get();
		return conta.getSaldo();
	}

	private boolean saldoSuficiente(Conta conta, Double valor) {
		if(conta.getSaldo() > valor)
			return true;
		return false;
	}
	
	public List<ContaDTO> findByInstituicaoFinanceira(String instituicao) {
		List<Conta> contas = repository.findByInstituicaoFinanceiraIgnoreCase(instituicao);
		return contas.stream().map(conta -> new ContaDTO(conta)).collect(Collectors.toList());
	}

	private void criarReceita(Conta conta, double valor) {
		Receita receita = new Receita(valor, new Date(), "Dinheiro recebido por outra conta", TipoReceita.OUTROS, conta);
		receitaRepository.save(receita);
		conta.addReceita(receita);
		repository.save(conta);
	}

	private void criarDespesa(Conta conta, Double valor) {
		Despesa despesa = new Despesa(valor, new Date(), TipoDespesa.OUTROS, conta);
		despesaRepository.save(despesa);
		conta.addDespesa(despesa);
		repository.save(conta);
	}

	private void verificarContaExistente(Long id) {
		Optional<Conta> conta = repository.findById(id);
		if (!conta.isPresent()) {
			throw new NullPointerException("Conta não encontrada com ID:" + id);
		}
	}

}
