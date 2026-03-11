create schema quanlysanpham;
set search_path to quanlysanpham;
CREATE TABLE admin (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);CREATE TABLE product (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           brand VARCHAR(50) NOT NULL,
                           price DECIMAL(12, 2) NOT NULL,
                           stock INT NOT NULL
  );CREATE TABLE customer (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,
                                phone VARCHAR(20),
                                email VARCHAR(100) UNIQUE,
                                address VARCHAR(255)
    );CREATE TABLE invoice (
                                     id SERIAL PRIMARY KEY,
                                     customer_id INT,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     total_amount DECIMAL(12, 2) NOT NULL,
                                     CONSTRAINT fk_customer
                                         FOREIGN KEY(customer_id)
                                             REFERENCES customer(id)
                                             ON DELETE SET NULL
      );
      CREATE TABLE invoice_details (
    id SERIAL PRIMARY KEY,
    invoice_id INT,
    product_id INT,
    quantity INT NOT NULL,
    unit_price DECIMAL(12, 2) NOT NULL,
    CONSTRAINT fk_invoice
        FOREIGN KEY(invoice_id)
        REFERENCES invoice(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_product
        FOREIGN KEY(product_id)
        REFERENCES product(id)
);



-- Hàm cập nhật tổng tiền
CREATE OR REPLACE FUNCTION update_invoice_total()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE quanlysanpham.invoice
    SET total_amount = (
        SELECT COALESCE(SUM(quantity * unit_price), 0)
        FROM quanlysanpham.invoice_details
        WHERE invoice_id = NEW.invoice_id
    )
    WHERE id = NEW.invoice_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger cho INSERT và UPDATE trên invoice_details
CREATE OR REPLACE TRIGGER trg_update_invoice_total
    AFTER INSERT OR UPDATE OR DELETE
    ON quanlysanpham.invoice_details
    FOR EACH ROW
EXECUTE FUNCTION update_invoice_total();

