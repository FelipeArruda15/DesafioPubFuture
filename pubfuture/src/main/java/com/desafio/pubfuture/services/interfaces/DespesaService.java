package com.desafio.pubfuture.services.interfaces;

import java.util.List;

import com.desafio.pubfuture.dto.DespesaDTO;
import com.desafio.pubfuture.model.entities.Despesa;

public interface DespesaService {

	public DespesaDTO findById(Long id);

	public List<DespesaDTO> findAll();

	public DespesaDTO save(Despesa despesa);
	
	public void delete(Long id);

	public DespesaDTO update(Despesa despesa);
	
	public Double mostrarSaldoTotal();

	List<DespesaDTO> findByTipoDespesa(String tipoDespesa);
	
	List<DespesaDTO> findByDate(String dataInicial, String dataFinal);
	
}
