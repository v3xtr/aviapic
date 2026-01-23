CREATE TABLE product_pictures (
    product_id BIGINT NOT NULL,
    picture_url VARCHAR(255) NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);
