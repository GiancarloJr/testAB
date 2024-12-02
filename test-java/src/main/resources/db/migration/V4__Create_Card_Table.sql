CREATE TABLE IF NOT EXISTS CARD (
                                    CARDID INT NOT NULL AUTO_INCREMENT,
                                    CARDNUMBER VARCHAR(255) NOT NULL UNIQUE,
                                    CARDCLIENTNAME VARCHAR(255) NOT NULL,
                                    CARDTYPE ENUM('PHYSICAL', 'VIRTUAL'),
                                    CVV VARCHAR(4) NOT NULL,
                                    ACCOUNT_ID INT NOT NULL,
                                    STATUS ENUM('ENABLED', 'DISABLED'),
                                    ISSUEDATE DATE NOT NULL,
                                    DELIVERYDATE DATE DEFAULT NULL,
                                    EXPIRATIONDATE DATE NOT NULL,
                                    PRIMARY KEY (CARDID),
                                    CONSTRAINT FK_CARD_ACCOUNT FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT (ACCOUNT_NUMBER)
);