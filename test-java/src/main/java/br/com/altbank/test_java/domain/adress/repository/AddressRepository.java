package br.com.altbank.test_java.domain.adress.repository;

import br.com.altbank.test_java.domain.adress.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
