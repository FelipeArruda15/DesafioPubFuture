package com.desafio.pubfuture.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.desafio.pubfuture.model.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_conta")
public class Conta extends EntidadeAbstrata {

	private Double saldo;
	@Enumerated(value = EnumType.STRING)
	private TipoConta tipoConta;
	private String instituicaoFinanceira;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "conta")
	private List<Receita> receitas = new ArrayList<>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "conta")
	private List<Despesa> despesas = new ArrayList<>();

	public Conta() {
	}

	public Conta(Double saldo, TipoConta tipoConta, String instituicaoFinanceira) {
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

	public Conta(TipoConta tipoConta, String instituicaoFinanceira) {
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

	public List<Receita> getReceitas() {
		return receitas;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void addReceita(Receita receita) {
		receitas.add(receita);
		setSaldo(saldo + receita.getValor());
	}

	public void addDespesa(Despesa despesa) {
		despesas.add(despesa);
		setSaldo(saldo - despesa.getValor());
	}

	public void removeReceita(Receita receita) {
		setSaldo(saldo - receita.getValor());
		receitas.remove(receita);

	}

	public void removeDespesa(Despesa despesa) {
		setSaldo(saldo + despesa.getValor());
		despesas.remove(despesa);

	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", saldo=" + saldo + ", tipoConta=" + tipoConta + ", instituicaoFinanceira="
				+ instituicaoFinanceira + "]";
	}

}
