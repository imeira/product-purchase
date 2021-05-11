package com.imeira.product.purchase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Selic {
	@JsonProperty(value = "data")
	@JsonFormat(locale = "pt", shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "BRA")
	private Date date;
	
	@JsonProperty(value = "valor")
	private BigDecimal value;
}
