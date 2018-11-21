package br.com.apirest.estudando.repository;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apirest.estudando.model.Transacao;
import br.com.apirest.estudando.util.TipoTransacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

	@Query("SELECT t FROM transacoes t WHERE conta_id =:idConta and data_transacao >=:start and data_transacao <=:end and tipo =:tipo")
	Set<Transacao> findLimiteSaqueDiario(@Param("idConta") Long idConta, @Param("start") Calendar start, @Param("end") Calendar end, @Param("tipo") TipoTransacao tipo);
	
	public List<Transacao> findByContaId(Long idConta);
	
	public List<Transacao> findAllByConta_IdAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(Long id, Calendar dataInicio, Calendar dataFim);
}
