package com.imeira.product.purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AccountTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountTransactionApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

}
