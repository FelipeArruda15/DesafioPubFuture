package com.desafio.pubfuture.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.desafio.pubfuture.model.enums.TipoDespesa;

@Entity
@Table(name = "tb_despesa")
public class Despesa extends EntidadeAbstrata{

	private Double valor;
	private Date dataPagamento;
	private Date dataPagamentoEsperado;
	@Enumerated(value = EnumType.STRING)
	private TipoDespesa tipoDespesa;
	
	
    @ManyToOne
    @JoinColumn(name = "conta_id")
	private Conta conta;

	public Despesa() {
	}
	
	public Despesa(Long id, Double valor, Date dataPagamento, Date dataPagamentoEsperado, TipoDespesa tipoDespesa, Conta conta) {
		this.id = id;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.dataPagamentoEsperado = dataPagamentoEsperado;
		this.tipoDespesa = tipoDespesa;
		this.conta = conta;
	}
	
	public Despesa(Double valor, Date dataPagamento, TipoDespesa tipoDespesa, Conta conta) {
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.tipoDespesa = tipoDespesa;
		this.conta = conta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataPagamentoEsperado() {
		return dataPagamentoEsperado;
	}

	public void setDataPagamentoEsperado(Date dataPagamentoEsperado) {
		this.dataPagamentoEsperado = dataPagamentoEsperado;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	@Override
	public String toString() {
		return "Despesa [valor=" + valor + ", dataPagamento=" + dataPagamento + ", dataPagamentoEsperado="
				+ dataPagamentoEsperado + ", tipoDespesa=" + tipoDespesa + ", conta=" + conta + "]";
	}
	
}
