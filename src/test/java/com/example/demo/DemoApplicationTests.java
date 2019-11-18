package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.demo.controller.CustomerController;
import com.example.demo.controller.dto.CustomerDto;
import com.example.demo.controller.dto.SalaryDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Salary;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.services.CustomerService;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private CustomerController customerController;

  @InjectMocks
  private CustomerService customerService;

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @BeforeEach
  private void mockData(){
    final List<Customer> mockCustomers = new ArrayList<>();
    final Customer customer = new Customer();
    customer.setName("Test customer");
    customer.setId(1L);
    final Salary salary = new Salary();
    salary.setAmount(new BigDecimal(1000.98));
    customer.setSalary(salary);
    mockCustomers.add(customer);
    when(customerRepository.findAll()).thenReturn(mockCustomers);
  }

  @DisplayName("test customerService with mock data")
  @Test
  void testService() {
    final List<Customer> result = customerService.listAll();
    assertNotNull(result, "Expected not null result.");
    assertEquals(1, result.size(), "Size of the list must be 1");
    assertEquals(1L, result.get(0).getId(), "Id must be 1");
    assertEquals("Test customer", result.get(0).getName(), "Name must be Test customer");
    assertEquals(1000.98, result.get(0).getSalary().getAmount().doubleValue(), "Salary must be 1000.98");
  }

  @DisplayName("test customerController with empty DB at startup")
  @Test
  void testController() throws Exception{
    final CustomerDto customerDto = new CustomerDto();
    customerDto.setName("Test customer");
    customerDto.setSalary(new SalaryDto(new BigDecimal(1000.98)));
    Long res = restTemplate.exchange("http://localhost:" + port + "/demo-service/api/customer/create", HttpMethod.POST, new HttpEntity<CustomerDto>(customerDto), Long.class).getBody();
    assertEquals(1L, res, "Id must be 1");

    final URL url = new URL("http://localhost:" + port + "/demo-service/api/customer/");
    ResponseEntity<CustomerDto[]> response = restTemplate.getForEntity(
        url.toString(), CustomerDto[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    CustomerDto[] result = response.getBody();
    assertNotNull(result, "Expected not null result.");
    assertEquals(1, result.length, "Size of the list must be 1");
    assertEquals(1L, result[0].getId(), "Id must be 1");
    assertEquals("Test customer", result[0].getName(), "Name must be Test customer");
    assertEquals(1000.98, result[0].getSalary().getAmount().doubleValue(), "Salary must be 1000.98");
  }

}
