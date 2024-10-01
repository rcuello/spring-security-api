
-- Insertar usuario administrador
    -- Super Admin (ID 1)
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('admin'        , 'Super','Admin','admin@yopmail.com', '$2a$10$FV0v5FjoY2j668DD0zvRUOQBz6e45ewHuOrp26JXJqxIHydMgRhZm', 'ADMINISTRATOR');
    -- asistentes de administrador
        -- Homero Simpson (ID 2)
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('asistente'    , 'Homero','Simpson','homero.simpson@yopmail.com', '$2a$10$vKtgkXrTDCVAH5qzqybBJuKypYjDPQyeBSO8UR3sH1x7hRhTfetwm', 'ASSISTANT_ADMINISTRATOR');
    -- clientes
        -- John Doe (ID 3)
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('jhon.doe', 'John','Doe','john.doe@yopmail.com', '$2a$10$CfNa7120LlhuFZW5aS2gN.hkET/CmP0fKObYdbemtnaXTRjZVJZ4W', 'CUSTOMER');
        -- Don Quijote (ID 4)
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('cervantes.quijote', 'Don','Quijote','don.quijote@yopmail.com', '$2a$10$CfNa7120LlhuFZW5aS2gN.hkET/CmP0fKObYdbemtnaXTRjZVJZ4W', 'CUSTOMER');
        -- Lorca Gonzales (ID 5)
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('lorca.gonzales', 'Lorca','Gonzales','lorca.gonzales@yopmail.com', '$2a$10$CfNa7120LlhuFZW5aS2gN.hkET/CmP0fKObYdbemtnaXTRjZVJZ4W', 'CUSTOMER');
        -- Pablo Neruda (ID 6)
INSERT INTO users (username, first_name,last_name,email, password, role) VALUES ('pablo.neruda', 'Pablo','Neruda','pablo.neruda@yopmail.com', '$2a$10$CfNa7120LlhuFZW5aS2gN.hkET/CmP0fKObYdbemtnaXTRjZVJZ4W', 'CUSTOMER');

-- Insertar categorías
INSERT INTO categories (name, status) VALUES ('Electrónica' , 'ENABLED');
INSERT INTO categories (name, status) VALUES ('Ropa'        , 'ENABLED');
INSERT INTO categories (name, status) VALUES ('Deportes'    , 'ENABLED');
INSERT INTO categories (name, status) VALUES ('Hogar'       , 'ENABLED');

-- Insertar productos en la categoría de Electrónica
INSERT INTO products (name, price, status, category_id) VALUES ('Smartphone', 2000000.00, 'ENABLED', 1); 
INSERT INTO products (name, price, status, category_id) VALUES ('Auriculares Bluetooth', 300000.00, 'DISABLED', 1); 
INSERT INTO products (name, price, status, category_id) VALUES ('Tablet', 1200000.00, 'ENABLED', 1); 
INSERT INTO products (name, price, status, category_id) VALUES ('Smartwatch', 800000.00, 'ENABLED', 1); 
INSERT INTO products (name, price, status, category_id) VALUES ('Cámara Digital', 1500000.00, 'ENABLED', 1); 

-- Insertar productos en la categoría de Ropa
INSERT INTO products (name, price, status, category_id) VALUES ('Camiseta', 80000.00, 'ENABLED', 2); 
INSERT INTO products (name, price, status, category_id) VALUES ('Pantalones', 120000.00, 'ENABLED', 2); 
INSERT INTO products (name, price, status, category_id) VALUES ('Zapatos', 180000.00, 'ENABLED', 2); 
INSERT INTO products (name, price, status, category_id) VALUES ('Chaqueta', 250000.00, 'ENABLED', 2); 
INSERT INTO products (name, price, status, category_id) VALUES ('Sombrero', 50000.00, 'ENABLED', 2); 

-- Insertar productos en la categoría de Deportes
INSERT INTO products (name, price, status, category_id) VALUES ('Balón de Fútbol', 100000.00, 'ENABLED', 3); 
INSERT INTO products (name, price, status, category_id) VALUES ('Raqueta de Tenis', 500000.00, 'DISABLED', 3); 
INSERT INTO products (name, price, status, category_id) VALUES ('Bicicleta', 1500000.00, 'ENABLED', 3); 
INSERT INTO products (name, price, status, category_id) VALUES ('Pesas', 300000.00, 'ENABLED', 3); 

-- Insertar productos en la categoría de Hogar
INSERT INTO products (name, price, status, category_id) VALUES ('Aspiradora', 600000.00, 'ENABLED', 4); 
INSERT INTO products (name, price, status, category_id) VALUES ('Licuadora', 200000.00, 'ENABLED', 4); 
INSERT INTO products (name, price, status, category_id) VALUES ('Mesa de Centro', 400000.00, 'ENABLED', 4);
INSERT INTO products (name, price, status, category_id) VALUES ('Silla de Oficina', 350000.00, 'ENABLED', 4);


-- Insertar cupones iniciales
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('BLACKFRIDAY'  , 20.00, '2024-11-24', '2024-11-25', 50);   -- Descuento por viernes negro
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('VIPCUSTOMER'  , 25.00, '2024-01-01', '2024-12-31', NULL); -- Descuento a clientes preferenciales
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('ELECTRONICS20', 20.00, '2024-04-01', '2024-04-30', 100);  -- Descuento en electrónica
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('SPORTS15'     , 15.00, '2024-07-01', '2024-07-31', 150);  -- Descuento en artículos deportivos
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('HOME25'       , 25.00, '2024-09-01', '2024-09-30', 100);  -- Descuento en artículos para el hogar
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('BIRTHDAY10'   , 10.00, '2024-05-01', '2024-05-31', 50);   -- Descuento de cumpleaños
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('FLASHSALE'    , 30.00, '2024-10-15', '2024-10-15', 50);   -- Descuento de venta flash
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('LOYALTY10'    , 10.00, '2024-02-01', '2024-02-28', 200);  -- Descuento por lealtad
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('WEEKEND20'    , 20.00, '2024-11-01', '2024-11-30', 100);  -- Descuento de fin de semana
INSERT INTO coupons (code, discount_percentage, valid_from, valid_until, usage_limit) VALUES ('FIRSTORDER'   , 15.00, '2024-01-01', '2024-12-31', 300);  -- Descuento en la primera orden


-- Insertar comentarios de productos
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'Excelente smartphone, muy rapido y con buena camara.', '2024-10-01', 1, 3);                        -- John Doe (ID 3) comenta sobre el Smartphone
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'Buenos auriculares, pero la bateria podria durar mas.', '2024-10-02', 2, 4);                       -- Don Quijote (ID 4) comenta sobre los Auriculares Bluetooth
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La tablet es buena, pero el tamano de la pantalla podria ser mejor.', '2024-10-03', 3, 5);         -- Lorca Gonzales (ID 5) comenta sobre la Tablet
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'Smartwatch increible, muy comodo y con muchas funciones.', '2024-10-04', 4, 6);                    -- Pablo Neruda (ID 6) comenta sobre el Smartwatch
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'La camara digital es buena, pero el zoom podria ser mejor.', '2024-10-05', 5, 3);                  -- John Doe (ID 3) comenta sobre la Camara Digital
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'Excelente camiseta, muy comoda y de buena calidad.', '2024-10-06', 6, 4);                          -- Don Quijote (ID 4) comenta sobre la Camiseta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'Los pantalones son buenos, pero el ajuste podria ser mejor.', '2024-10-07', 7, 5);                 -- Lorca Gonzales (ID 5) comenta sobre los Pantalones
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'Los zapatos son muy comodos y de buena calidad.', '2024-10-08', 8, 6);                             -- Pablo Neruda (ID 6) comenta sobre los Zapatos
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'La chaqueta es buena, pero el material podria ser mas resistente.', '2024-10-09', 9, 3);           -- John Doe (ID 3) comenta sobre la Chaqueta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'El sombrero es muy elegante y de buena calidad.', '2024-10-10', 10, 4);                            -- Don Quijote (ID 4) comenta sobre el Sombrero
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'El balon de futbol es bueno, pero el material podria ser mas resistente.', '2024-10-11', 11, 5);   -- Lorca Gonzales (ID 5) comenta sobre el Balon de Futbol
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La raqueta de tenis es buena, pero el peso podria ser mejor.', '2024-10-12', 12, 6);               -- Pablo Neruda (ID 6) comenta sobre la Raqueta de Tenis
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'La bicicleta es muy comoda y de buena calidad.', '2024-10-13', 13, 3);                             -- John Doe (ID 3) comenta sobre la Bicicleta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'Las pesas son buenas, pero el peso podria ser mejor.', '2024-10-14', 14, 4);                       -- Don Quijote (ID 4) comenta sobre las Pesas
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'La aspiradora es muy potente y de buena calidad.', '2024-10-15', 15, 5);                           -- Lorca Gonzales (ID 5) comenta sobre la Aspiradora
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'La licuadora es buena, pero el motor podria ser mas potente.', '2024-10-16', 16, 6);               -- Pablo Neruda (ID 6) comenta sobre la Licuadora
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'La mesa de centro es muy elegante y de buena calidad.', '2024-10-17', 17, 3);                      -- John Doe (ID 3) comenta sobre la Mesa de Centro
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'La silla de oficina es comoda, pero el material podria ser mejor.', '2024-10-18', 18, 4);          -- Don Quijote (ID 4) comenta sobre la Silla de Oficina
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'El smartphone tiene problemas de bateria.', '2024-10-19', 1, 5);                                   -- Lorca Gonzales (ID 5) comenta sobre el Smartphone
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'Los auriculares Bluetooth no funcionan bien.', '2024-10-20', 2, 6);                                -- Pablo Neruda (ID 6) comenta sobre los Auriculares Bluetooth
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La tablet es lenta y tiene poca memoria.', '2024-10-21', 3, 3);                                    -- John Doe (ID 3) comenta sobre la Tablet
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'El smartwatch se desactiva constantemente.', '2024-10-22', 4, 4);                                  -- Don Quijote (ID 4) comenta sobre el Smartwatch
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'La camara digital tiene poca resolucion.', '2024-10-23', 5, 5);                                    -- Lorca Gonzales (ID 5) comenta sobre la Camara Digital
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La camiseta se arruga facilmente.', '2024-10-24', 6, 6);                                           -- Pablo Neruda (ID 6) comenta sobre la Camiseta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'Los pantalones no se ajustan bien.', '2024-10-25', 7, 3);                                          -- John Doe (ID 3) comenta sobre los Pantalones
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'Los zapatos son incomodos y de mala calidad.', '2024-10-26', 8, 4);                                -- Don Quijote (ID 4) comenta sobre los Zapatos
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La chaqueta es demasiado grande.', '2024-10-27', 9, 5);                                            -- Lorca Gonzales (ID 5) comenta sobre la Chaqueta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'El sombrero es demasiado pequeno.', '2024-10-28', 10, 6);                                          -- Pablo Neruda (ID 6) comenta sobre el Sombrero
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'El balon de futbol se desinfla rapidamente.', '2024-10-29', 11, 3);                                -- John Doe (ID 3) comenta sobre el Balon de Futbol
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La raqueta de tenis es demasiado pesada.', '2024-10-30', 12, 4);                                   -- Don Quijote (ID 4) comenta sobre la Raqueta de Tenis
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'La bicicleta tiene problemas con los cambios.', '2024-10-31', 13, 5);                              -- Lorca Gonzales (ID 5) comenta sobre la Bicicleta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'Las pesas son de mala calidad.', '2024-11-01', 14, 6);                                             -- Pablo Neruda (ID 6) comenta sobre las Pesas
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La aspiradora es demasiado ruidosa.', '2024-11-02', 15, 3);                                        -- John Doe (ID 3) comenta sobre la Aspiradora
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'La licuadora no tiene suficiente potencia.', '2024-11-03', 16, 4);                                 -- Don Quijote (ID 4) comenta sobre la Licuadora
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'La mesa de centro es demasiado pequena.', '2024-11-04', 17, 5);                                    -- Lorca Gonzales (ID 5) comenta sobre la Mesa de Centro
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La silla de oficina es incomoda.', '2024-11-05', 18, 6);                                           -- Pablo Neruda (ID 6) comenta sobre la Silla de Oficina

-- Comentarios adicionales de usuarios sobre el mismo producto
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'El smartphone tiene una excelente camara, pero la bateria podria ser mejor.', '2024-11-06', 1, 3); -- John Doe (ID 3) comenta nuevamente sobre el Smartphone
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'Los auriculares Bluetooth son buenos, pero la conexion a veces se pierde.', '2024-11-07', 2, 4);   -- Don Quijote (ID 4) comenta nuevamente sobre los Auriculares Bluetooth
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'La tablet es lenta y tiene poca memoria, pero el diseno es bueno.', '2024-11-08', 3, 5);           -- Lorca Gonzales (ID 5) comenta nuevamente sobre la Tablet
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (5, 'El smartwatch es muy comodo y tiene muchas funciones, lo recomiendo.', '2024-11-09', 4, 6);        -- Pablo Neruda (ID 6) comenta nuevamente sobre el Smartwatch
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (4, 'La camara digital tiene buena resolucion, pero el zoom podria ser mejor.', '2024-11-10', 5, 3);    -- John Doe (ID 3) comenta nuevamente sobre la Camara Digital
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La camiseta es comoda, pero se arruga facilmente.', '2024-11-11', 6, 4);                           -- Don Quijote (ID 4) comenta nuevamente sobre la Camiseta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'Los pantalones no se ajustan bien, pero son de buena calidad.', '2024-11-12', 7, 5);               -- Lorca Gonzales (ID 5) comenta nuevamente sobre los Pantalones
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'Los zapatos son incomodos y de mala calidad, no los recomiendo.', '2024-11-13', 8, 6);             -- Pablo Neruda (ID 6) comenta nuevamente sobre los Zapatos
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La chaqueta es demasiado grande, pero el material es resistente.', '2024-11-14', 9, 3);            -- John Doe (ID 3) comenta nuevamente sobre la Chaqueta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'El sombrero es demasiado pequeno, pero tiene un buen diseno.', '2024-11-15', 10, 4);               -- Don Quijote (ID 4) comenta nuevamente sobre el Sombrero
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'El balon de futbol se desinfla rapidamente, no dura mucho.', '2024-11-16', 11, 5);                 -- Lorca Gonzales (ID 5) comenta nuevamente sobre el Balon de Futbol
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La raqueta de tenis es demasiado pesada, pero tiene buena calidad.', '2024-11-17', 12, 6);         -- Pablo Neruda (ID 6) comenta nuevamente sobre la Raqueta de Tenis
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'La bicicleta tiene problemas con los cambios, pero es comoda.', '2024-11-18', 13, 3);              -- John Doe (ID 3) comenta nuevamente sobre la Bicicleta
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'Las pesas son de mala calidad, no duran mucho.', '2024-11-19', 14, 4);                             -- Don Quijote (ID 4) comenta nuevamente sobre las Pesas
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La aspiradora es demasiado ruidosa, pero es muy potente.', '2024-11-20', 15, 5);                   -- Lorca Gonzales (ID 5) comenta nuevamente sobre la Aspiradora
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (2, 'La licuadora no tiene suficiente potencia, pero es facil de usar.', '2024-11-21', 16, 6);          -- Pablo Neruda (ID 6) comenta nuevamente sobre la Licuadora
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (1, 'La mesa de centro es demasiado pequena, no sirve para mucho.', '2024-11-22', 17, 3);               -- John Doe (ID 3) comenta nuevamente sobre la Mesa de Centro
INSERT INTO product_reviews (rating, comment, review_date, product_id, customer_id) VALUES (3, 'La silla de oficina es incomoda, pero tiene buena calidad.', '2024-11-23', 18, 4);                 -- Don Quijote (ID 4) comenta nuevamente sobre la Silla de Oficina

-- Insertar los parametros del sistema
INSERT INTO system_parameters (config_key, setting_value) VALUES ('nombre_tienda', 'Mi Tienda Online');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('moneda_predeterminada', 'COP');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('idioma_predeterminado', 'es');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('metodos_pago', 'PSE, Nequi, Transferencia Bancaria');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('proveedores_envio', 'ServiEntrega, InterRapidisimo, Coordinadora, TCC');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('tasa_impuesto_predeterminada' , '0.19');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('smtp_server', 'smtp.mitienda.com');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('ui_theme', 'light');
INSERT INTO system_parameters (config_key, setting_value) VALUES ('etiquetas_seo', 'ecommerce, online shopping, best deals');