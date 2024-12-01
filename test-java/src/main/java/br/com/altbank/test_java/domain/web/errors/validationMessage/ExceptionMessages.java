package br.com.altbank.test_java.domain.web.errors.validationMessage;

public interface ExceptionMessages {
    String CPF_INVALID = "CPF invalid";
     String CPF_NOT_NULL = "CPF cannot be null.";
     String NAME_NOT_NULL = "Name cannot be null.";
     String EMAIL_INVALID = "Email invalid";
     String EMAIL_NOT_NULL = "Email cannot be null.";
     String PHONE_LENGTH = "Phone lenght invalid.";
     String PHONE_NOT_NULL = "Phone cannot be null.";
     String CUSTOMER_NOT_FOUND = "Customer not found.";
     String CUSTOMER_ALREADY_EXIST = "Customer already exists.";
     String CARD_NOT_FOUND = "Card not found.";
     String ACCOUNT_NOT_FOUND = "Account not found.";
     String STATUS_INVALID = "Status invalid. Use 'true' or 'false'";
     String STATUS_NOT_NULL = "Status cannot be null.";
     String ADDRESS_NOT_NULL = "Field cannot be null.";
     String ADRESS_NOT_FOUND = "Address not found.";
}
