
CREATE TABLE IF NOT EXISTS ACCOUNT (
                                       ACCOUNT_NUMBER INT NOT NULL AUTO_INCREMENT,
                                       CUSTOMER_ID INT NOT NULL,
                                       STATUS ENUM('ENABLED', 'DISABLED'),
                                       PRIMARY KEY (ACCOUNT_NUMBER),
                                       CONSTRAINT FK_ACCOUNT_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTID)
);