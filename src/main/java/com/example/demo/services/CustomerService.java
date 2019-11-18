package com.example.demo.services;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Service
public class CustomerService {

  private CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer updateSalary(Long customerId, BigDecimal salary) {
    final Customer customer = customerRepository.findById(customerId).orElseThrow(
        () -> new IllegalArgumentException(
            String.format("Customer with id {%s} not found in the DB", customerId)));
    customer.getSalary().setAmount(salary);
    customerRepository.save(customer);
    return customer;
  }

  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

  public List<Customer> listAll() {
    return customerRepository.findAll();
  }

  public void deleteAll() {
    customerRepository.deleteAll();
  }
}
