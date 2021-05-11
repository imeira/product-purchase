package com.imeira.product.purchase.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Simulation {

	private PaymentConditions paymentConditions;
	private Product product;

}
