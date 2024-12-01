package br.com.altbank.test_java.domain.web.errors.validationMessage;

public interface ExceptionMessages {
    String CPF_INVALID = "O CPF invalido";
     String CPF_NOT_NULL = "O CPF não pode ser nulo.";
     String NAME_NOT_NULL = "O nome não pode ser nulo ou vazio.";
     String EMAIL_INVALID = "Formato de e-mail inválido.";
     String EMAIL_NOT_NULL = "E-mail não pode ser nulo ou vazio.";
     String PHONE_LENGTH = "O telefone deve ter exatamente 11 caracteres.";
     String PHONE_NOT_NULL = "O telefone não pode ser nulo.";
     String CUSTOMER_NOT_FOUND = "Customer not found.";
     String CUSTOMER_ALREADY_EXIST = "Customer already exists.";
     String CARD_NOT_FOUND = "Card not found.";
     String ACCOUNT_NOT_FOUND = "Account not found.";
     String STATUS_INVALID = "Status invalid. Use 'true' or 'false'";
     String STATUS_NOT_NULL = "Status cannot be null.";
     String ADDRESS_NOT_NULL = "Field cannot be null.";
     String ADRESS_NOT_FOUND = "Address not found.";
}
