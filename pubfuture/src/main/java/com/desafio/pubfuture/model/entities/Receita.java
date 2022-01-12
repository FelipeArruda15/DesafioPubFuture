package com.desafio.pubfuture.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.desafio.pubfuture.model.enums.TipoReceita;

@Entity
@Table(name = "tb_receita")
public class Receita extends EntidadeAbstrata{

	private Double valor;
	private Date dataRecebimento;
	private Date dataRecebimentoEsperado;
	private String descricao;
	@Enumerated(value = EnumType.STRING)
	private TipoReceita tipoReceita;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "conta_id")
	private Conta conta;

	public Receita() {
	}

	public Receita(Long id, Double valor, Date dataRecebimento, Date dataRecebimentoEsperado, String descricao,
			TipoReceita tipoReceita, Conta conta) {
		this.id = id;
		this.valor = valor;
		this.dataRecebimento = dataRecebimento;
		this.dataRecebimentoEsperado = dataRecebimentoEsperado;
		this.descricao = descricao;
		this.tipoReceita = tipoReceita;
		this.conta = conta;
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

	@Override
	public String toString() {
		return "Receita [id=" + id + ", valor=" + valor + ", dataRecebimento=" + dataRecebimento
				+ ", dataRecebimentoEsperado=" + dataRecebimentoEsperado + ", descricao=" + descricao + ", tipoReceita="
				+ tipoReceita + ", conta=" + conta + "]";
	}
}
