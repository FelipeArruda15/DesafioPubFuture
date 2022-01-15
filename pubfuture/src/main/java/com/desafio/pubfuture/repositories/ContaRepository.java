package com.desafio.pubfuture.repositories;

import com.desafio.pubfuture.model.entities.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findByInstituicaoFinanceiraIgnoreCase(String instituicaoFinanceira);
}
