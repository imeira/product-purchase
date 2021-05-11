package com.imeira.product.purchase.service;

//import com.imeira.product.purchase.client.SelicClient;
import com.imeira.product.purchase.client.SelicClient;
import com.imeira.product.purchase.domain.Installment;
import com.imeira.product.purchase.domain.Selic;
import com.imeira.product.purchase.domain.Simulation;
//import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

	@Autowired
	private SelicClient client;

	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

	public List<Installment> simulatePurchase(Simulation installments) {
		LOG.info("XXXXXXX getInstallments XXXXXXX");
		if(installments.getProduct().getValue().doubleValue() < installments.getPaymentConditions().getInitValue().doubleValue()) {
			LOG.error("Valor de entrada é maior que o valor do produto");
			throw new IllegalArgumentException("Valor de entrada é maior que o valor do produto");
		}
		Double diff = installments.getProduct().getValue().doubleValue() - installments.getPaymentConditions().getInitValue().doubleValue();

		List<Installment> list = new ArrayList<>();
		if (installments.getPaymentConditions().getNumberInstallments() < 7) {
			LOG.info("XXXXXXX getInstallments IF XXXXXXX");
			for (int i = 0; i < installments.getPaymentConditions().getNumberInstallments(); i++) {
				list.add(Installment.builder().installment(i+1)
						.value(BigDecimal.valueOf(diff).divide(BigDecimal.valueOf(installments.getPaymentConditions().getNumberInstallments()), 2, RoundingMode.HALF_DOWN))
						.interestRate(BigDecimal.ZERO).build());
			}
		} else {
			LOG.info("XXXXXXX getInstallments ELSE XXXXXXX");
			Double total = calculateInterest(getSelicRateLast30Days().doubleValue(), installments.getPaymentConditions().getNumberInstallments(), diff);
			for (int i = 0; i < installments.getPaymentConditions().getNumberInstallments(); i++) {
				list.add(Installment.builder().installment(i+1)
						.value(BigDecimal.valueOf(total).divide(BigDecimal.valueOf(installments.getPaymentConditions().getNumberInstallments()), 2, RoundingMode.HALF_DOWN))
						.interestRate(getSelicRateLast30Days()).build());
			}
		}
		LOG.info("XXXXXXX getInstallments XXXXXXX list.size= " + list.size());
		return list;
	}

	public BigDecimal getSelicRateLast30Days() {
		List<Selic> list = client.getSelicLastThirtyDays();
		LOG.info("XXXXXXX getSelicRateLast30Days XXXXXXX list.size= " + list.size());
		return list.stream().map(s -> s.getValue())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static Double calculateInterest(Double rate, Integer month, Double value) {
		return value * (Math.pow((1 + (rate / 100)), month));
	}
}
