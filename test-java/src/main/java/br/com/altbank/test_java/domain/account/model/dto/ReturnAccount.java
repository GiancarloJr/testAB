package br.com.altbank.test_java.domain.account.model.dto;

import br.com.altbank.test_java.domain.account.model.enums.AccountStatusEnum;
import br.com.altbank.test_java.domain.adress.model.Address;
import br.com.altbank.test_java.domain.card.model.Card;

import java.util.List;

public record ReturnAccount(String cpf, String name, String email, String phone, Integer accountNumber,
                            AccountStatusEnum accountStatus, List<Address> addresses, List<Card> cards) {
}
