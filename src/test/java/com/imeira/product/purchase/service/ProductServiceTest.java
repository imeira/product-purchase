package com.imeira.product.purchase.service;

import com.imeira.product.purchase.client.SelicClient;
import com.imeira.product.purchase.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    SelicClient client;

    @InjectMocks
    ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void simulatePurchase() {
        when(client.getSelicLastThirtyDays()).thenReturn(new ArrayList<Selic>());
        List<Installment> result = productService.simulatePurchase(getSimulation());
        Assertions.assertNotNull(result);
    }
    
    @Test
    void getSelicRateLast30Days() {
        when(client.getSelicLastThirtyDays()).thenReturn(new ArrayList<Selic>());

        BigDecimal result = productService.getSelicRateLast30Days();
        Assertions.assertNotNull(result);
    }

    @Test
    void calculateInterest() {
        when(client.getSelicLastThirtyDays()).thenReturn(new ArrayList<Selic>());

        Double result = productService.calculateInterest(new Double(100), 10, new Double(1000));
        Assertions.assertNotNull(result);
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
