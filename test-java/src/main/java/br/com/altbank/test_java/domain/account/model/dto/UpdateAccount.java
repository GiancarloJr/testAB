package br.com.altbank.test_java.domain.account.model.dto;

public record UpdateAccount(String name,
                            String email, 
                            String phone,
                            Integer idAddress,
                            String street,
                            String city,
                            String state,
                            String cep,
                            String country) {
}
