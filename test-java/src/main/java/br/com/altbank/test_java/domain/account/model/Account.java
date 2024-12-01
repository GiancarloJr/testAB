package br.com.altbank.test_java.domain.account.model;

import br.com.altbank.test_java.domain.account.model.enums.AccountStatusEnum;
import br.com.altbank.test_java.domain.card.model.Card;
import br.com.altbank.test_java.domain.customer.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ACCOUNT")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_NUMBER")
    private Integer accountNumber;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> cards;

    @Enumerated(EnumType.STRING)
    @Column(name= "STATUS",nullable = false)
    private AccountStatusEnum status;
}
