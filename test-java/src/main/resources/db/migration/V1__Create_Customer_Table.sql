CREATE TABLE IF NOT EXISTS CUSTOMER (
                                        CUSTID INT NOT NULL AUTO_INCREMENT,
                                        CPF VARCHAR(20) NOT NULL UNIQUE,
                                        NAME VARCHAR(255) NOT NULL,
                                        EMAIL VARCHAR(255) NOT NULL UNIQUE,
                                        PHONE VARCHAR(20) NOT NULL,
                                        PRIMARY KEY (CUSTID)
);