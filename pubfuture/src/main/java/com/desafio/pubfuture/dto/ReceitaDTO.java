package com.desafio.pubfuture.dto;

import java.util.Date;

import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.entities.Receita;
import com.desafio.pubfuture.model.enums.TipoReceita;

public class ReceitaDTO {

	private Long id;
	private Double valor;
	private Date dataRecebimento;
	private Date dataRecebimentoEsperado;
	private String descricao;
	private TipoReceita tipoReceita;
	private Conta conta;

	public ReceitaDTO() {
	}

	public ReceitaDTO(Long id, Double valor, Date dataRecebimento, Date dataRecebimentoEsperado, String descricao,
			TipoReceita tipoReceita, Conta conta) {
		this.id = id;
		this.valor = valor;
		this.dataRecebimento = dataRecebimento;
		this.dataRecebimentoEsperado = dataRecebimentoEsperado;
		this.descricao = descricao;
		this.tipoReceita = tipoReceita;
		this.conta = conta;
	}

	public ReceitaDTO(Receita receita) {
		id = receita.getId();
		valor = receita.getValor();
		dataRecebimento = receita.getDataRecebimento();
		dataRecebimentoEsperado = receita.getDataRecebimentoEsperado();
		descricao = receita.getDescricao();
		tipoReceita = receita.getTipoReceita();
		conta = receita.getConta();
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

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Date getDataRecebimentoEsperado() {
		return dataRecebimentoEsperado;
	}

	public void setDataRecebimentoEsperado(Date dataRecebimentoEsperado) {
		this.dataRecebimentoEsperado = dataRecebimentoEsperado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
