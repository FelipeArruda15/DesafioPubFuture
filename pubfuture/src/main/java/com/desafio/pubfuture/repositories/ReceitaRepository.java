package com.desafio.pubfuture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.pubfuture.model.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	@Query(value = "select * from TB_RECEITA where UPPER(TIPO_RECEITA) = UPPER(?1)", nativeQuery = true)
	List<Receita> findByTipoReceitaIgnoreCase(String tipoReceita);
	
	@Query(value = "select * from TB_RECEITA where DATA_RECEBIMENTO between ?1 and ?2", nativeQuery = true)
	List<Receita> findByDataRecebimento(String dataInicial, String dataFinal);
}
