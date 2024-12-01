package br.com.altbank.test_java.domain.account.model.dto;

import br.com.altbank.test_java.domain.account.model.enums.AccountStatusEnum;
import br.com.altbank.test_java.domain.adress.model.Address;

public record CreatedAccount(Integer accountNumber, String cpf, String name, String email, String phone, Address address, AccountStatusEnum statusAccount) {
}
