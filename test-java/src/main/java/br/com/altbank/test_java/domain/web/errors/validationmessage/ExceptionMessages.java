package br.com.altbank.test_java.domain.web.errors.validationmessage;

public class ExceptionMessages {

     private ExceptionMessages(){

     }

     public static final String CPF_INVALID = "CPF invalid.";
    public static final String CPF_NOT_NULL = "CPF cannot be null.";
    public static final String NAME_NOT_NULL = "Name cannot be null.";
    public static final String EMAIL_INVALID = "Email invalid.";
    public static final String EMAIL_NOT_NULL = "Email cannot be null.";
    public static final String PHONE_LENGTH = "Phone lenght invalid.";
    public static final String PHONE_NOT_NULL = "Phone cannot be null.";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found.";
    public static final String CUSTOMER_ALREADY_EXIST = "Customer already exists.";
    public static final String CARD_NOT_FOUND = "Card not found.";
    public static final String ACCOUNT_NOT_FOUND = "Account not found.";
    public static final String STATUS_INVALID = "Status invalid. Use 'true' or 'false'.";
    public static final String STATUS_NOT_NULL = "Status cannot be null.";
    public static final String ADDRESS_NOT_NULL = "Field cannot be null.";

    public static final String ADDRESS_NOT_EMPTY = "Field cannot be empty.";
    public static final String ADDRESS_NOT_FOUND = "Address not found.";
}
