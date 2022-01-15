package com.desafio.pubfuture.dto;

import java.util.Date;

import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.entities.Despesa;
import com.desafio.pubfuture.model.enums.TipoDespesa;

public class DespesaDTO {

	private Long id;
	private Double valor;
	private Date dataPagamento;
	private Date dataPagamentoEsperado;
	private TipoDespesa tipoDespesa;
	private Conta conta;

	public DespesaDTO() {
	}

	public DespesaDTO(Long id, Double valor, Date dataPagamento, Date dataPagamentoEsperado, TipoDespesa tipoDespesa,
			Conta conta) {
		this.id = id;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.dataPagamentoEsperado = dataPagamentoEsperado;
		this.tipoDespesa = tipoDespesa;
		this.conta = conta;
	}

	public DespesaDTO(Despesa despesa) {
		id = despesa.getId();
		valor = despesa.getValor();
		dataPagamento = despesa.getDataPagamento();
		dataPagamentoEsperado = despesa.getDataPagamentoEsperado();
		tipoDespesa = despesa.getTipoDespesa();
		conta = despesa.getConta();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
