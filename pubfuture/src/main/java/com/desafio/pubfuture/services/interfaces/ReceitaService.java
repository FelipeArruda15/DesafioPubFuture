package com.desafio.pubfuture.services.interfaces;

import java.util.List;

import com.desafio.pubfuture.dto.ReceitaDTO;
import com.desafio.pubfuture.model.entities.Receita;

public interface ReceitaService {

	public ReceitaDTO findById(Long id);

	public List<ReceitaDTO> findAll();

	public ReceitaDTO save(Receita receita);
	
	public void delete(Long id);

	public ReceitaDTO update(Receita receita);
	
	public Double mostrarSaldoTotal();

	List<ReceitaDTO> findByTipoReceita(String tipoReceita);
	
	List<ReceitaDTO> findByDate(String dataInicial, String dataFinal);
}
