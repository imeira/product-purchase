package com.imeira.product.purchase.domain;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentConditions {

	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal initValue;

	@Min(1)
	private Integer numberInstallments;

}
