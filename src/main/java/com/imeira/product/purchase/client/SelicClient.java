package com.imeira.product.purchase.client;

import com.imeira.product.purchase.domain.Selic;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "selicclient", url = "https://api.bcb.gov.br")
public interface SelicClient {
	
	@GetMapping("/dados/serie/bcdata.sgs.11/dados/ultimos/30?formato=json")
	@Headers("Content-Type: application/json")
	List<Selic> getSelicLastThirtyDays();
}
