package br.com.altbank.test_java.domain.web.rest;

import br.com.altbank.test_java.domain.card.model.dto.CreatedCard;
import br.com.altbank.test_java.domain.card.model.dto.ResponseStatusCard;
import br.com.altbank.test_java.domain.card.model.dto.ReturnCardsAccount;
import br.com.altbank.test_java.domain.card.model.dto.RequestStatusCard;
import br.com.altbank.test_java.domain.card.service.CardService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
public class CardResource {

    private final CardService cardService;

    public CardResource(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/account/{cpf}")
    public ResponseEntity<ReturnCardsAccount> findById(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok().body(cardService.findCardsAccount(cpf));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<CreatedCard> insert(@Valid @PathVariable("cpf") String cpf) {
        return ResponseEntity.ok().body(cardService.create(cpf));
    }

    @PostMapping("reissue/{cpf}")
    public ResponseEntity<CreatedCard> reissueCard(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok().body(cardService.reissueCard(cpf));
    }

    @PutMapping("/status/{accountNumber}/{cardNumber}")
    public ResponseEntity<ResponseStatusCard> setStatusCard(@PathVariable Integer accountNumber,
                                                            @PathVariable String cardNumber,
                                                            @Valid @RequestBody  RequestStatusCard statusCard) {

        ResponseStatusCard responseStatusCard = cardService.setStatusCard(accountNumber, cardNumber,statusCard);
        return ResponseEntity.ok(responseStatusCard);
    }

}
