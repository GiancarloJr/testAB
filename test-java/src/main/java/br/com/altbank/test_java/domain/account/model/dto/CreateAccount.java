package br.com.altbank.test_java.domain.account.model.dto;


import br.com.altbank.test_java.domain.web.errors.validationmessage.ExceptionMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CreateAccount(
        @NotNull(message = ExceptionMessages.CPF_NOT_NULL)
        @CPF(message = ExceptionMessages.CPF_INVALID) String cpf,
        @NotNull(message = ExceptionMessages.NAME_NOT_NULL)
        String name,
        @NotNull(message = ExceptionMessages.EMAIL_NOT_NULL)
        @Email(message = ExceptionMessages.EMAIL_INVALID) String email,
        @NotNull(message = ExceptionMessages.PHONE_NOT_NULL)
        @Size(min = 11,max=11, message = ExceptionMessages.PHONE_LENGTH)
        String phone,
        @NotNull(message = ExceptionMessages.ADDRESS_NOT_NULL)
        String street,
        @NotNull(message = ExceptionMessages.ADDRESS_NOT_NULL)
        String city,
        @NotNull(message = ExceptionMessages.ADDRESS_NOT_NULL)
        String state,
        @NotNull(message = ExceptionMessages.ADDRESS_NOT_NULL)
        String cep,
        @NotNull(message = ExceptionMessages.ADDRESS_NOT_NULL)
        String country){
}

