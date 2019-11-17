package com.example.demo.controller.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {
  @NotNull
  private BigDecimal amount;
}
