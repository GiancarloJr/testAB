package br.com.altbank.test_java.domain.account.model.dto;

import br.com.altbank.test_java.domain.adress.model.Address;

public record UpdatedAccount(String name,
                             String email,
                             String phone,
                             Address addresses
                             ) {
}
