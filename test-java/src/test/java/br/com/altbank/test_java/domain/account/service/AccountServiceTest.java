package br.com.altbank.test_java.domain.account.service;

import br.com.altbank.test_java.domain.account.model.Account;
import br.com.altbank.test_java.domain.account.model.dto.*;
import br.com.altbank.test_java.domain.account.model.enums.AccountStatusEnum;
import br.com.altbank.test_java.domain.account.repository.AccountReposity;
import br.com.altbank.test_java.domain.adress.model.Address;
import br.com.altbank.test_java.domain.adress.repository.AddressRepository;
import br.com.altbank.test_java.domain.customer.model.Customer;
import br.com.altbank.test_java.domain.customer.repository.CustomerRepository;
import br.com.altbank.test_java.domain.web.errors.exceptions.DataIntegrityException;
import br.com.altbank.test_java.domain.web.errors.exceptions.NotFoundException;
import br.com.altbank.test_java.domain.web.errors.validationMessage.ExceptionMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountReposity accountReposity;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;

    private CreateAccount createAccount;
    private Customer customer;
    private Address address;
    private UpdateAccount updateAccount;
    private static final String CPF = "11111111111";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Account account = new Account();

        createAccount = new CreateAccount("11111111111",
                "Giancarlo",
                "giancarlo@hotmail.com",
                "99999999999",
                "Geronimo",
                "Rio Verde",
                "Goias",
                "77777777",
                "Brasil");

        customer = Customer.builder()
                .cpf("11111111111")
                .phone("99999999999")
                .email("giancarlo@hotmail.com")
                .account(account)
                .name("Giancarlo")
                .build();

        address = Address.builder()
                    .id(1)
                    .street(createAccount.street())
                    .city(createAccount.city())
                    .state(createAccount.state())
                    .cep(createAccount.cep())
                    .country(createAccount.country())
                .build();

        updateAccount = new UpdateAccount(
                "Giancarlo Sena",
                "giancarlo456@hotmail.com",
                "11111111111",
                1,
                "Reis",
                "São Paulo",
                "California",
                "8888888",
                "EUA"
        );
    }

    @Test
    void createAccountSuccess() {

        when(customerRepository.findByCpf(createAccount.cpf())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Account account = new Account();
        account.setAccountNumber(12345);

        when(accountReposity.save(any(Account.class))).thenReturn(account);

        CreatedAccount createdAccount = accountService.create(createAccount);

        assertNotNull(createdAccount);
        assertEquals(12345, createdAccount.accountNumber());
        assertEquals("11111111111", createdAccount.cpf());
        assertEquals("Giancarlo", createdAccount.name());
        assertEquals("giancarlo@hotmail.com", createdAccount.email());
        assertEquals("Geronimo", createdAccount.address().getStreet());
        assertEquals("Rio Verde", createdAccount.address().getCity());
        assertEquals("Goias", createdAccount.address().getState());
        assertEquals("77777777", createdAccount.address().getCep());
        assertEquals("Brasil", createdAccount.address().getCountry());

        verify(customerRepository).save(any(Customer.class));
        verify(accountReposity).save(any(Account.class));
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void createAccountCustomerAlreadyExists() {
        when(customerRepository.findByCpf(createAccount.cpf())).thenReturn(java.util.Optional.of(customer));

        DataIntegrityException thrown = assertThrows(DataIntegrityException.class, () -> accountService.create(createAccount));
        assertEquals(ExceptionMessages.CUSTOMER_ALREADY_EXIST, thrown.getMessage());
    }

    @Test
    void findByCpfSuccess() {
        when(customerRepository.findByCpf(CPF)).thenReturn(java.util.Optional.of(customer));

        ReturnAccount returnAccount  = accountService.findByCpf(CPF);

        assertNotNull(returnAccount);
        assertEquals("11111111111", returnAccount.cpf());
        assertEquals("Giancarlo", returnAccount.name());
        assertEquals("giancarlo@hotmail.com", returnAccount.email());
        assertEquals("99999999999", returnAccount.phone());
    }

    @Test
    void findByCpfCustomerNotFound() {
        when(customerRepository.findByCpf(CPF)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> accountService.findByCpf("11111111111"));
        assertEquals(ExceptionMessages.CUSTOMER_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void setStatusAccountSuccess() {
        Account account = new Account();
        account.setStatus(AccountStatusEnum.ENABLED);
        account.setAccountNumber(12345);
        when(accountReposity.findByAccountNumber(12345)).thenReturn(Optional.of(account));
        when(accountReposity.save(any(Account.class))).thenReturn(account);

        RequestStatusAccount statusAccount = new RequestStatusAccount(false);

        ResponseStatusAccount result = accountService.setStatusAccountByCpf(12345, statusAccount);

        assertNotNull(result);
        assertFalse(result.accountStatus().isStatus());
        verify(accountReposity).save(any(Account.class));
        assertEquals(account.getStatus(), result.accountStatus());
    }

    @Test
    void setStatusAccountByCpfAccountNotFound() {
        when(accountReposity.findByAccountNumber(12345)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> accountService.setStatusAccountByCpf(12345, new RequestStatusAccount(false)));
        assertEquals(ExceptionMessages.ACCOUNT_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void updateAccountSuccess() {

        UpdateAccount updateAccountRequest = updateAccount;

        Customer customerEntity = customer;

        Address addressEntity = address;

        customerEntity.setAddresses(Collections.singletonList(addressEntity));

        when(customerRepository.findByCpf(CPF)).thenReturn(Optional.of(customerEntity));
        when(addressRepository.save(any(Address.class))).thenReturn(addressEntity);
        when(customerRepository.save(any(Customer.class))).thenReturn(customerEntity);

        UpdatedAccount updatedAccount = accountService.updateAccount(CPF, updateAccountRequest);

        assertNotNull(updatedAccount);
        assertEquals("Giancarlo Sena", updatedAccount.name());
        assertEquals("giancarlo456@hotmail.com", updatedAccount.email());
        assertEquals("11111111111", updatedAccount.phone());
        assertEquals("8888888", updatedAccount.addresses().getCep());
        assertEquals("São Paulo", updatedAccount.addresses().getCity());
        assertEquals("California", updatedAccount.addresses().getState());
        assertEquals("Reis", updatedAccount.addresses().getStreet());
        assertEquals("EUA", updatedAccount.addresses().getCountry());

        verify(customerRepository).save(any(Customer.class));
        verify(addressRepository).save(any(Address.class));
    }
    @Test
    void updateAccountCustomerNotFound() {
        UpdateAccount updateAccountRequest = updateAccount;

        when(customerRepository.findByCpf(CPF)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> accountService.updateAccount(CPF, updateAccountRequest));
    }
}
