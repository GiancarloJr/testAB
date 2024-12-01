CREATE TABLE IF NOT EXISTS ADDRESS (
                                       ADDID INT NOT NULL AUTO_INCREMENT,
                                       CUSTOMER_ID INT NOT NULL,
                                       STREET VARCHAR(255) NOT NULL,
                                       CITY VARCHAR(255) NOT NULL,
                                       STATE VARCHAR(255) NOT NULL,
                                       CEP VARCHAR(20) NOT NULL,
                                       COUNTRY VARCHAR(255) NOT NULL,
                                       PRIMARY KEY (ADDID),
                                       CONSTRAINT FK_ADDRESS_CUSTOMER FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTID)
);