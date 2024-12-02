package br.com.altbank.test_java.domain.account.model.dto;

import br.com.altbank.test_java.domain.web.errors.validationmessage.ExceptionMessages;
import jakarta.validation.constraints.NotNull;

public record RequestStatusAccount(@NotNull(message = ExceptionMessages.STATUS_INVALID)
                                   Boolean accountStatus) {
}
