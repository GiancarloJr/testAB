package br.com.altbank.test_java.domain.card.model;

import br.com.altbank.test_java.domain.account.model.Account;
import br.com.altbank.test_java.domain.card.model.enums.CardStatusEnum;
import br.com.altbank.test_java.domain.card.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "CARD")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @Column(name = "CARDID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CARDNUMBER",nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "CARDCLIENTNAME",nullable = false)
    private String cardClientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CARDTYPE")
    private CardType type;

    @Column(name = "CVV",nullable = false)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    @JsonIgnore
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS",nullable = false)
    private CardStatusEnum status;

    @Column(name = "ISSUEDATE",nullable = false)
    private LocalDate issueDate;

    @Column(name = "DELIVERYDATE")
    private LocalDate deliveryDate;

    @Column(name = "EXPIRATIONDATE",nullable = false)
    private LocalDate expirationDate;
}