package com.desafio.pubfuture.services.interfaces;

import java.util.List;

import com.desafio.pubfuture.dto.ContaDTO;
import com.desafio.pubfuture.model.entities.Conta;
import com.desafio.pubfuture.model.entities.Transferencia;

public interface ContaService {

	public ContaDTO findById(Long id); 
	
	public List<ContaDTO> findAll(); 
	
	public ContaDTO save(Conta conta); 
	
	public void delete(Long id); 
	
	public ContaDTO update(Conta conta); 
	
	public void transferencia(Transferencia transferencia); 

	public Double mostrarSaldoTotal(Long id); 

}
