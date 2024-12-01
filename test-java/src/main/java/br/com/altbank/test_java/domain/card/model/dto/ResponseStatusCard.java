package br.com.altbank.test_java.domain.card.model.dto;

import br.com.altbank.test_java.domain.card.model.enums.CardStatusEnum;

public record ResponseStatusCard(String numberCard , CardStatusEnum statusCard) {
}
