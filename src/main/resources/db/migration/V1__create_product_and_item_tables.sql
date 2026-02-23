CREATE TABLE product (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         product_name VARCHAR(255) NOT NULL,
                         created_by VARCHAR(100) NOT NULL,
                         created_on DATETIME(6) NOT NULL,
                         modified_by VARCHAR(100),
                         modified_on DATETIME(6),
                         PRIMARY KEY (id)
);

CREATE TABLE item (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      quantity INT NOT NULL,
                      product_id BIGINT NOT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (product_id) REFERENCES product(id)
);
