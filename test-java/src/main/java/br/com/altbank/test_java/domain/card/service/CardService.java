package br.com.altbank.test_java.domain.card.service;

import br.com.altbank.test_java.domain.account.model.Account;
import br.com.altbank.test_java.domain.account.repository.AccountReposity;
import br.com.altbank.test_java.domain.card.model.Card;
import br.com.altbank.test_java.domain.card.model.dto.CreatedCard;
import br.com.altbank.test_java.domain.card.model.dto.RequestStatusCard;
import br.com.altbank.test_java.domain.card.model.dto.ResponseStatusCard;
import br.com.altbank.test_java.domain.card.model.dto.ReturnCardsAccount;
import br.com.altbank.test_java.domain.card.model.enums.CardStatusEnum;
import br.com.altbank.test_java.domain.card.model.enums.CardType;
import br.com.altbank.test_java.domain.card.repository.CardRepository;
import br.com.altbank.test_java.domain.card.utils.CardUtils;
import br.com.altbank.test_java.domain.customer.model.Customer;
import br.com.altbank.test_java.domain.customer.repository.CustomerRepository;
import br.com.altbank.test_java.domain.web.errors.exceptions.NotFoundException;
import br.com.altbank.test_java.domain.web.errors.validationMessage.ExceptionMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final AccountReposity accountReposity;

    public CreatedCard create(String cpf) {

        Customer customerEntity = customerRepository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException(ExceptionMessages.CUSTOMER_NOT_FOUND)
        );

        CardType cardType = setCardType(customerEntity);
        CardStatusEnum cardStatusEnum = setCardStatus(cardType);

        Card cardEntity = Card.builder()
                .cardNumber(CardUtils.generateCardNumber())
                .cardClientName(customerEntity.getName())
                .status(cardStatusEnum)
                .account(customerEntity.getAccount())
                .type(cardType)
                .issueDate(LocalDate.now())
                .CVV(CardUtils.generateCvvNumber())
                .expirationDate(LocalDate.now().plus(4, ChronoUnit.YEARS))
                .build();

        cardEntity = cardRepository.save(cardEntity);

        CreatedCard cardCreated = new CreatedCard(customerEntity.getAccount().getAccountNumber()
                , cardEntity.getCardNumber()
                , cardEntity.getCVV()
                , cardEntity.getType()
                , cardEntity.getCardClientName()
                , cardEntity.getStatus());

        return cardCreated;
    }

    public ReturnCardsAccount findCardsAccount(String cpf) {

        Customer customerEntity = customerRepository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException(ExceptionMessages.CUSTOMER_NOT_FOUND)
        );

        List<Card> cardsAccount = customerEntity.getAccount().getCards();

        ReturnCardsAccount returnCardsAccount =
                new ReturnCardsAccount(customerEntity.getAccount().getAccountNumber(),
                        cardsAccount);

        return returnCardsAccount;
    }

    public CreatedCard reissueCard(String cpf) {

        Customer customerEntity = customerRepository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException(ExceptionMessages.CUSTOMER_NOT_FOUND)
        );

        Card cardEntity = customerEntity.getAccount()
                    .getCards()
                        .stream()
                            .filter(x -> x.getType().equals(CardType.PHYSICAL)).findAny().
                orElseThrow(() -> new NotFoundException(ExceptionMessages.CARD_NOT_FOUND));

        cardEntity.setCardNumber(CardUtils.generateCardNumber());
        cardEntity.setStatus(CardStatusEnum.DISABLED);
        cardEntity.setIssueDate(LocalDate.now());
        cardEntity.setCVV(CardUtils.generateCvvNumber());

        cardEntity = cardRepository.save(cardEntity);

        CreatedCard cardCreated = new CreatedCard(customerEntity.getAccount().getAccountNumber()
                , cardEntity.getCardNumber()
                , cardEntity.getCVV()
                , cardEntity.getType()
                , cardEntity.getCardClientName()
                , cardEntity.getStatus());

        return cardCreated;
    }

    public ResponseStatusCard setStatusCard(Integer accountNumber, String cardNumber, RequestStatusCard statusCard) {

        Account accountEntity = accountReposity.findByAccountNumber(accountNumber).orElseThrow(
                () -> new NotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND));

        Card cardEntity = accountEntity.getCards().stream()
                .filter(card -> card.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.CARD_NOT_FOUND));

        cardEntity.setStatus(CardStatusEnum.getStatus(statusCard.statusCard()));

        cardRepository.save(cardEntity);

        ResponseStatusCard responseStatusCard = new ResponseStatusCard(cardNumber, cardEntity.getStatus());

        return responseStatusCard;
    }

    private CardType setCardType(Customer customer) {
        return customer.getAccount().getCards().stream()
                .anyMatch(card -> card.getType() == CardType.PHYSICAL)
                ? CardType.VIRTUAL
                : CardType.PHYSICAL;
    }

    private CardStatusEnum setCardStatus(CardType cardType) {
        return cardType == CardType.PHYSICAL ? CardStatusEnum.DISABLED : CardStatusEnum.ENABLED;
    }


}
