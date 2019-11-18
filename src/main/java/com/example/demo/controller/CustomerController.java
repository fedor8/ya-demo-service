package com.example.demo.controller;

import com.example.demo.controller.dto.CustomerDto;
import com.example.demo.converter.CustomerDtoToCustomerConverter;
import com.example.demo.converter.CustomerToDtoCustomerConverter;
import com.example.demo.entity.Customer;
import com.example.demo.services.CustomerService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Controller
@RequestMapping("/api/customer")
public class CustomerController {

  private CustomerService customerService;
  private CustomerDtoToCustomerConverter dtoToCustomerConverter;
  private CustomerToDtoCustomerConverter customerToDtoConverter;

  public CustomerController(CustomerService customerService,
      CustomerDtoToCustomerConverter dtoToCustomerConverter,
      CustomerToDtoCustomerConverter customerToDtoConverter) {
    this.customerService = customerService;
    this.dtoToCustomerConverter = dtoToCustomerConverter;
    this.customerToDtoConverter = customerToDtoConverter;
  }

  @PostMapping("/create")
  @Transactional
  public HttpEntity<Long> create(@RequestBody @Valid CustomerDto customerDto) {
    final Customer customer = customerService.save(dtoToCustomerConverter.convert(customerDto));
    return new ResponseEntity<>(customer.getId(), HttpStatus.CREATED);
  }

  @PostMapping("/{id}/update-salary")
  @ResponseBody
  @Transactional
  public HttpEntity<CustomerDto> update(@RequestParam @NotNull BigDecimal salary,
      @PathVariable("id") Long customerId) {
    final Customer customer = customerService.updateSalary(customerId, salary);
    return new ResponseEntity<>(customerToDtoConverter.convert(customer), HttpStatus.OK);
  }

  @GetMapping("/")
  @ResponseBody
  public HttpEntity<List<CustomerDto>> findAllCustomers() {
    final ArrayList<CustomerDto> customers = new ArrayList<>();
    customerService.listAll()
        .forEach(customer -> customers.add(customerToDtoConverter.convert(customer)));
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @DeleteMapping("/")
  @ResponseBody
  @Transactional
  public void deleteAllCustomers() {
    customerService.deleteAll();
  }

}
