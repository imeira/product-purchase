package com.imeira.product.purchase.domain;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

	private BigInteger code;

	@NotNull
	@NotBlank
	private String name;

	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal value;
	
}
