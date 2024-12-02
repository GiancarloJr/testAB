package br.com.altbank.test_java.domain.card.model.dto;

import br.com.altbank.test_java.domain.web.errors.validationmessage.ExceptionMessages;
import jakarta.validation.constraints.NotNull;

public record RequestStatusCard(
                                @NotNull(message = ExceptionMessages.STATUS_NOT_NULL)
                                Boolean statusCard) {
}
