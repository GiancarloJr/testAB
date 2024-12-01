package br.com.altbank.test_java.domain.card.model.dto;

import br.com.altbank.test_java.domain.web.errors.validationMessage.ExceptionMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestStatusCard(
                                @NotNull(message = ExceptionMessages.STATUS_NOT_NULL)
                                Boolean statusCard) {
}
