package com.imeira.product.purchase.controller;

import com.imeira.product.purchase.domain.Installment;
import com.imeira.product.purchase.domain.PaymentConditions;
import com.imeira.product.purchase.domain.Product;
import com.imeira.product.purchase.domain.Simulation;
import com.imeira.product.purchase.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void simulatePurchase() {
        when(productService.simulatePurchase(getSimulation())).thenReturn(Arrays.<Installment>asList(new Installment()));

        ResponseEntity<List<Installment>> result = productController.simulatePurchase(getSimulation());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    private Simulation getSimulation() {
        Product product = new Product();
        product.setValue(new BigDecimal(1000));
        product.setName("Produto 1");
        PaymentConditions paymentConditions = new PaymentConditions();
        paymentConditions.setNumberInstallments(5);
        paymentConditions.setInitValue(new BigDecimal(10));
        Simulation simulation = new Simulation();
        simulation.setProduct(product);
        simulation.setPaymentConditions(paymentConditions);
        return simulation;
    }
}
