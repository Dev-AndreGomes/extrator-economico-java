package com.projeto.dados.extrator.service;

import com.projeto.dados.extrator.dto.DadosEconomicosDTO;
import com.projeto.dados.extrator.model.IndicadorEconomico;
import com.projeto.dados.extrator.repository.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class IngestaoService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IndicadorRepository repository;

    public void extrair(String nomeIndicador, String codigoSerie) {
        String dataInicio = LocalDate.now().minusDays(30).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String url = String.format(
                "https://api.bcb.gov.br/dados/serie/bcdata.sgs.%s/dados?formato=json&dataInicial=%s",
                codigoSerie, dataInicio
        );

        DadosEconomicosDTO[] resposta = restTemplate.getForObject(url, DadosEconomicosDTO[].class);

        if (resposta != null) {
            Arrays.stream(resposta).forEach(dto ->{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataRef = LocalDate.parse(dto.getData(), formatter);

                var existe = repository.findByNomeAndDataReferencia(nomeIndicador, dataRef);

                if (existe.isEmpty()) {
                    IndicadorEconomico indicador = new IndicadorEconomico();
                    indicador.setNome(nomeIndicador);
                    indicador.setValor(new BigDecimal(dto.getValor()));
                    indicador.setDataReferencia(dataRef);

                    repository.save(indicador);
                } else {
                    System.out.println("Pulando " + nomeIndicador + " para a data " + dataRef + " (já cadastrado)");
                }
            });
            System.out.println("Dados de " + nomeIndicador + " processados!");
        }
    }
}
