package com.example.demo.converter;

import com.example.demo.controller.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Salary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

  @Override
  public Customer convert(CustomerDto customerDto) {
    if(customerDto.getSalary()==null){
      throw new IllegalArgumentException("Can't save customer without salary!");
    }
    final Customer customer = Customer.builder()
        .id(customerDto.getId())
        .name(customerDto.getName())
        .salary(Salary.builder().amount(customerDto.getSalary().getAmount()).build())
        .build();
    customer.getSalary().setCustomer(customer);
    return customer;
  }
}
