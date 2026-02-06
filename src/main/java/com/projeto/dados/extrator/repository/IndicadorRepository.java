package com.projeto.dados.extrator.repository;

import com.projeto.dados.extrator.model.IndicadorEconomico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IndicadorRepository extends JpaRepository<IndicadorEconomico, Long> {

    Optional<IndicadorEconomico> findByNomeAndDataReferencia(String nome, LocalDate dataReferencia);
}
