package com.desafio.pubfuture.dto;

import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.enums.TipoConta;

public class ContaDTO {
	
	private Long id;
	private Double saldo;
	private TipoConta tipoConta;
	private String instituicaoFinanceira;
	
	public ContaDTO() {
	}

	public ContaDTO(Long id, Double saldo, TipoConta tipoConta, String instituicaoFinanceira) {
		this.id = id;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.instituicaoFinanceira = instituicaoFinanceira;
	}
	
	public ContaDTO(Conta conta) {
		id = conta.getId();
		saldo = conta.getSaldo();
		tipoConta = conta.getTipoConta();
		instituicaoFinanceira = conta.getInstituicaoFinanceira();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}

	public void setInstituicaoFinanceira(String instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

}
