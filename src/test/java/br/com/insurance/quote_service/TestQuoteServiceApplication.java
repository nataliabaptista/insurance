package br.com.insurance.quote_service;

import org.springframework.boot.SpringApplication;

public class TestQuoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(QuoteServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
