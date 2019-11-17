package com.example.demo.converter;

import com.example.demo.controller.dto.CustomerDto;
import com.example.demo.controller.dto.SalaryDto;
import com.example.demo.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Component
public class CustomerToDtoCustomerConverter implements Converter<Customer, CustomerDto> {

  @Override
  public CustomerDto convert(Customer customer) {
    return CustomerDto.builder()
        .id(customer.getId())
        .name(customer.getName())
        .salary(getSalaryDto(customer))
        .build();
  }

  private SalaryDto getSalaryDto(Customer customer) {
    if(customer.getSalary() == null){
      return null;
    }
    return SalaryDto.builder().amount(customer.getSalary().getAmount()).build();
  }
}
