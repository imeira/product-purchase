package com.imeira.product.purchase.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Simulation {

	private PaymentConditions paymentRules;
	private Product product;

}
