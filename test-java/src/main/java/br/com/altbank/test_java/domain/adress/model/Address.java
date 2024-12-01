package br.com.altbank.test_java.domain.adress.model;

import br.com.altbank.test_java.domain.customer.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ADDRESS")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "ADDID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name ="STREET", nullable = false)
    private String street;

    @Column(name ="CITY",nullable = false)
    private String city;

    @Column(name ="STATE",nullable = false)
    private String state;

    @Column(name ="CEP",nullable = false)
    private String cep;

    @Column(name ="COUNTRY",nullable = false)
    private String country;
}