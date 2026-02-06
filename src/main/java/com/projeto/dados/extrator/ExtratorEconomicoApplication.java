package com.projeto.dados.extrator;

import com.projeto.dados.extrator.service.IngestaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExtratorEconomicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtratorEconomicoApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner run(IngestaoService ingestaoService) {
		return args -> {
            System.out.println("--- INICIANDO PIPELINE MULTI-DADOS ---");

            ingestaoService.extrair("Dólar", "1");
            ingestaoService.extrair("SELIC", "11");
            ingestaoService.extrair("IPCA", "433");

            System.out.println("--- TUDO PRONTO NO BANCO DE DADOS ---");
		};
	}
}


