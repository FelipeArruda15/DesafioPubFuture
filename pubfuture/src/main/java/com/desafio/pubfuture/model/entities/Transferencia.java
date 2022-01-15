package com.desafio.pubfuture.model.entities;

public class Transferencia {
	
	private Conta contaRemetente;
	private Conta contaDestino;
	private Double valor;
	
	public Transferencia() {
	}
	
	public Transferencia(Conta contaRemetente, Conta contaDestino, Double valor) {
		this.contaRemetente = contaRemetente;
		this.contaDestino = contaDestino;
		this.valor = valor;
	}

	public Conta getContaRemetente() {
		return contaRemetente;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public Double getValor() {
		return valor;
	}
	
}
