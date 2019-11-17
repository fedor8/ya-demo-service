package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
