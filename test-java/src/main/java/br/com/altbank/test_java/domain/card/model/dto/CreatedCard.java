package br.com.altbank.test_java.domain.card.model.dto;

import br.com.altbank.test_java.domain.card.model.enums.CardStatusEnum;
import br.com.altbank.test_java.domain.card.model.enums.CardType;

public record CreatedCard(Integer accountNumber, String cardNumber, String CVV, CardType cardType, String cardClientName,
                          CardStatusEnum statusCard) {
}
