package br.com.altbank.test_java.domain.card.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardStatusEnum {
    ENABLED(true),
    DISABLED(false);

    private final boolean status;

    public static CardStatusEnum getStatus(boolean codigo) {
        for (CardStatusEnum status : CardStatusEnum.values()) {
            if (status.isStatus() == codigo) {
                return status;
            }
        }
        return null;
    }
}
