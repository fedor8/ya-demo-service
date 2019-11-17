package com.example.demo.controller.dto;

import java.util.List;
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
public class CustomerDto {
  private Long id;
  @NotNull
  private String name;
  @NotNull
  private SalaryDto salary;
}
