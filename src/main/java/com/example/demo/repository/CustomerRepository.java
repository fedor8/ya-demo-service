package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Repository
@RestResource(exported = false)
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
