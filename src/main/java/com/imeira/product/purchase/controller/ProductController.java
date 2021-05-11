package com.imeira.product.purchase.controller;

import com.imeira.product.purchase.domain.Installment;
import com.imeira.product.purchase.domain.Simulation;
import com.imeira.product.purchase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@PostMapping("/purchase")
    public ResponseEntity<List<Installment>> simulatePurchase(@RequestBody Simulation installments) {
    	 List<Installment> list = service.simulatePurchase(installments);
        return ResponseEntity.ok().body(list);
    }
    
}
