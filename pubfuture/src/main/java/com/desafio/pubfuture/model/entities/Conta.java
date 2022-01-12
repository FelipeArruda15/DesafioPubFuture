package com.desafio.pubfuture.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.desafio.pubfuture.model.enums.TipoConta;

@Entity
@Table(name = "tb_conta")
public class Conta extends EntidadeAbstrata{

	private Double saldo;
	@Enumerated(value = EnumType.STRING)
	private TipoConta tipoConta;
	private String instituicaoFinanceira;

	public Conta() {
	}

	public Conta(Long id, Double saldo, TipoConta tipoConta, String instituicaoFinanceira) {
		this.id = id;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.instituicaoFinanceira = instituicaoFinanceira;
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

	@Override
	public String toString() {
		return "Conta [id=" + id + ", saldo=" + saldo + ", tipoConta=" + tipoConta + ", instituicaoFinanceira="
				+ instituicaoFinanceira + "]";
	}

}
