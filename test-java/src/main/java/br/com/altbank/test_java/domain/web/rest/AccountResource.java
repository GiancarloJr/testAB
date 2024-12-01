package br.com.altbank.test_java.domain.web.rest;

import br.com.altbank.test_java.domain.account.model.dto.*;
import br.com.altbank.test_java.domain.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
@AllArgsConstructor
public class AccountResource {

    private final AccountService accountService;

    @GetMapping("/{cpf}")
    public ResponseEntity<ReturnAccount> findById(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok().body(accountService.findByCpf(cpf));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UpdatedAccount> update(@PathVariable("cpf") String cpf, @RequestBody UpdateAccount updateAccount) {
        UpdatedAccount updatedAccount = accountService.updateAccount(cpf,updateAccount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping()
    public ResponseEntity<CreatedAccount> insert(@Valid @RequestBody CreateAccount account) {
        return ResponseEntity.ok().body(accountService.create(account));
    }

    @PutMapping("/status/{account}")
    public ResponseEntity<ResponseStatusAccount> statusAccount(@PathVariable Integer account,@Valid @RequestBody RequestStatusAccount requestStatusAccount) {
        ResponseStatusAccount disableAccount = accountService.setStatusAccountByCpf(account, requestStatusAccount);
        return ResponseEntity.ok(disableAccount);
    }

}
