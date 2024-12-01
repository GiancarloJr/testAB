package br.com.altbank.test_java.domain.account.model.dto;

import br.com.altbank.test_java.domain.account.model.enums.AccountStatusEnum;

public record ResponseStatusAccount(Integer accountNumber , AccountStatusEnum accountStatus) {
}
