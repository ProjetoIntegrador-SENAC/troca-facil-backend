 CREATE TABLE products(
    id VARCHAR(255),
    condition_product VARCHAR(30),
    category VARCHAR(30),
    name VARCHAR(50),
    description VARCHAR(255),
    account_id VARCHAR(255),

    PRIMARY KEY(id),
    CONSTRAINT FOREIGN KEY (account_id) REFERENCES accounts(id)

 )