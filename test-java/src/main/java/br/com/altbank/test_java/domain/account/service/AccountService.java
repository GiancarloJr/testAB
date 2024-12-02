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
import br.com.altbank.test_java.domain.web.errors.validationmessage.ExceptionMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountReposity accountReposity;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CreatedAccount create(CreateAccount account) {

        customerRepository.findByCpf(account.cpf())
                .ifPresent(c -> {
                    throw new DataIntegrityException(ExceptionMessages.CUSTOMER_ALREADY_EXIST);
                });

        Customer customerEntity = Customer.builder()
                .cpf(account.cpf())
                .phone(account.phone())
                .email(account.email())
                .name(account.name())
                .build();

        Address addressEntity = Address.builder()
                .street(account.street())
                .city(account.city())
                .cep(account.cep())
                .country(account.country())
                .state(account.state())
                .customer(customerEntity)
                .build();

        Account accountEntity = Account.builder()
                .customer(customerEntity)
                .status(AccountStatusEnum.ENABLED)
                .build();

        customerEntity = customerRepository.save(customerEntity);
        addressEntity = addressRepository.save(addressEntity);
        accountEntity = accountReposity.save(accountEntity);

        return new CreatedAccount(accountEntity.getAccountNumber(),
                customerEntity.getCpf(),
                customerEntity.getName(),
                customerEntity.getEmail(),
                customerEntity.getPhone(),
                addressEntity,
                accountEntity.getStatus());
    }

    public ReturnAccount findByCpf(String cpf) {

        Customer customerEntity = customerRepository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException(ExceptionMessages.CUSTOMER_NOT_FOUND)
        );

        return new ReturnAccount(customerEntity.getCpf(),
                customerEntity.getName(),
                customerEntity.getEmail(),
                customerEntity.getPhone(),
                customerEntity.getAccount().getAccountNumber(),
                customerEntity.getAccount().getStatus(),
                customerEntity.getAddresses(),
                customerEntity.getAccount().getCards());
    }

    public ResponseStatusAccount setStatusAccountByCpf(Integer accountNumber, RequestStatusAccount requestStatus) {

        Account accountEntity = accountReposity.findByAccountNumber(accountNumber).orElseThrow(
                () -> new NotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND)
        );

        accountEntity.setStatus(AccountStatusEnum.getStatus(requestStatus.accountStatus()));

        accountEntity = accountReposity.save(accountEntity);

        return new ResponseStatusAccount(accountEntity.getAccountNumber(),accountEntity.getStatus());
    }

    public UpdatedAccount updateAccount(String cpf, UpdateAccount updateAccountRequest) {

        Customer customerEntity = customerRepository.findByCpf(cpf).orElseThrow(
                () -> new NotFoundException(ExceptionMessages.CUSTOMER_NOT_FOUND)
        );

        Address addressEntity =
                customerEntity.getAddresses().stream().filter(ad -> ad.getId().equals(updateAccountRequest.idAddress()))
                                .findFirst().orElseThrow(() -> new NotFoundException(ExceptionMessages.ADDRESS_NOT_FOUND));

        Optional.ofNullable(updateAccountRequest.name()).ifPresent(customerEntity::setName);
        Optional.ofNullable(updateAccountRequest.email()).ifPresent(customerEntity::setEmail);
        Optional.ofNullable(updateAccountRequest.phone()).ifPresent(customerEntity::setPhone);
        Optional.ofNullable(updateAccountRequest.cep()).ifPresent(addressEntity::setCep);
        Optional.ofNullable(updateAccountRequest.city()).ifPresent(addressEntity::setCity);
        Optional.ofNullable(updateAccountRequest.country()).ifPresent(addressEntity::setCountry);
        Optional.ofNullable(updateAccountRequest.state()).ifPresent(addressEntity::setState);
        Optional.ofNullable(updateAccountRequest.street()).ifPresent(addressEntity::setStreet);

        UpdatedAccount updatedAccount = new UpdatedAccount(
                customerEntity.getName(),
                customerEntity.getEmail(),
                customerEntity.getPhone(),
                addressEntity
        );

        customerRepository.save(customerEntity);
        addressRepository.save(addressEntity);

        return updatedAccount;
    }
}
