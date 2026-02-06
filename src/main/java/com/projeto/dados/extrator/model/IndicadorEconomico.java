package com.projeto.dados.extrator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "indicadores")
@Data
public class IndicadorEconomico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataReferencia;

    private BigDecimal valor;

    private LocalDateTime dataExtracao;

    @PrePersist
    protected void onCreate() {
        this.dataExtracao = LocalDateTime.now();
    }
}
