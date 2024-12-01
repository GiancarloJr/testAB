package br.com.altbank.test_java.domain.card.model.dto;

import br.com.altbank.test_java.domain.card.model.Card;

import java.util.List;

public record ReturnCardsAccount(Integer numberAccount, List<Card> cards) {
}
