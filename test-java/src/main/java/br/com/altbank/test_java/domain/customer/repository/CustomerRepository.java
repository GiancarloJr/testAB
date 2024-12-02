package br.com.altbank.test_java.domain.customer.repository;

import br.com.altbank.test_java.domain.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCpf(String cpf);



}
