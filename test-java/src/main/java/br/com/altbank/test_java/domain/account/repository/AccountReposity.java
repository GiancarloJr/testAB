package br.com.altbank.test_java.domain.account.repository;

import br.com.altbank.test_java.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountReposity extends JpaRepository<Account, Integer> {
        Optional<Account> findByAccountNumber(Integer accountNumber);

}
