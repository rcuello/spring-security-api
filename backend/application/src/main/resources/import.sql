

-- Spring Boot detectará automáticamente el archivo import.sql y lo ejecutará al iniciar la aplicación

INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('cliente', 'John','Doe','john.doe@mail.com', '$2a$10$CfNa7120LlhuFZW5aS2gN.hkET/CmP0fKObYdbemtnaXTRjZVJZ4W', 'CUSTOMER');
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('asistente', 'Homero','Simpson','homero.simpson@mail.com', '$2a$10$vKtgkXrTDCVAH5qzqybBJuKypYjDPQyeBSO8UR3sH1x7hRhTfetwm', 'ASSISTANT_ADMINISTRATOR');
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('admin', 'Super','Admin','admin@mail.com', '$2a$10$FV0v5FjoY2j668DD0zvRUOQBz6e45ewHuOrp26JXJqxIHydMgRhZm', 'ADMINISTRATOR');

INSERT INTO categories (name, status) VALUES ('Electrónica', 'ENABLED');
INSERT INTO categories (name, status) VALUES ('Ropa', 'ENABLED');
INSERT INTO categories (name, status) VALUES ('Deportes', 'ENABLED');
INSERT INTO categories (name, status) VALUES ('Hogar', 'ENABLED');

INSERT INTO products (name, price, status, category_id) VALUES ('Smartphone', 500.00, 'ENABLED', 1);
INSERT INTO products (name, price, status, category_id) VALUES ('Auriculares Bluetooth', 50.00, 'DISABLED', 1);
INSERT INTO products (name, price, status, category_id) VALUES ('Tablet', 300.00, 'ENABLED', 1);

INSERT INTO products (name, price, status, category_id) VALUES ('Camiseta', 25.00, 'ENABLED', 2);
INSERT INTO products (name, price, status, category_id) VALUES ('Pantalones', 35.00, 'ENABLED', 2);
INSERT INTO products (name, price, status, category_id) VALUES ('Zapatos', 45.00, 'ENABLED', 2);

INSERT INTO products (name, price, status, category_id) VALUES ('Balón de Fútbol', 20.00, 'ENABLED', 3);
INSERT INTO products (name, price, status, category_id) VALUES ('Raqueta de Tenis', 80.00, 'DISABLED', 3);

INSERT INTO products (name, price, status, category_id) VALUES ('Aspiradora', 120.00, 'ENABLED', 4);
INSERT INTO products (name, price, status, category_id) VALUES ('Licuadora', 50.00, 'ENABLED', 4);