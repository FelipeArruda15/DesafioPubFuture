package com.desafio.pubfuture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.pubfuture.model.entities.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
	
	@Query(value = "select * from TB_DESPESA where UPPER(TIPO_DESPESA) = UPPER(?1)", nativeQuery = true)
	List<Despesa> findByTipoDespesaIgnoreCase(String tipoDespesa);

	@Query(value = "select * from TB_DESPESA where DATA_PAGAMENTO between ?1 and ?2", nativeQuery = true)
	List<Despesa> findByDataPagamento(String dataInicial, String dataFinal);
}
