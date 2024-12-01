package br.com.altbank.test_java.domain.account.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatusEnum {
    ENABLED(true),
    DISABLED(false);

    private final boolean status;

    public static AccountStatusEnum getStatus(boolean codigo) {
        for (AccountStatusEnum status : AccountStatusEnum.values()) {
            if (status.isStatus() == codigo) {
                return status;
            }
        }
        return null;
    }
}
