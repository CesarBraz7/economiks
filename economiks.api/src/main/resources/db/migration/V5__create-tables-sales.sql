CREATE TABLE sales (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    user_id BIGINT NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    paid_amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    remaining_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('COMPLETED', 'PENDING', 'CANCELLED')),
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_sales_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_sales_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE sale_items (
    id BIGSERIAL PRIMARY KEY,
    sale_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(10, 2) NOT NULL CHECK (unit_price >= 0),
    subtotal DECIMAL(10, 2) NOT NULL CHECK (subtotal >= 0),
    CONSTRAINT fk_sale_items_sale FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE,
    CONSTRAINT fk_sale_items_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    sale_id BIGINT NOT NULL,
    method VARCHAR(20) NOT NULL CHECK (method IN ('CASH', 'PIX', 'CARD', 'CREDIT')),
    amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
    payment_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_payments_sale FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE
);

CREATE INDEX idx_sales_customer ON sales(customer_id);
CREATE INDEX idx_sales_user ON sales(user_id);
CREATE INDEX idx_sales_date ON sales(sale_date);
CREATE INDEX idx_sales_status ON sales(status);
CREATE INDEX idx_sale_items_sale ON sale_items(sale_id);
CREATE INDEX idx_sale_items_product ON sale_items(product_id);
CREATE INDEX idx_payments_sale ON payments(sale_id);

