INSERT INTO customer
VALUES
    (1, 'Gonçalo Rua', 'gr@gmail.com', '911111111', 'ABCD, 1000-111 Lisboa'),
    (2, 'João Gouveia', 'jg@gmail.com', '922222222', 'EFGH, 2000-222 Porto'),
    (3, 'Francisco Fonseca', 'ff@gmail.com', '933333333', 'IJKL, 3000-333 Lisboa');

INSERT INTO orders
VALUES
    (1, 2, '2022-01-26'),
    (2, 1, '2022-01-28'),
    (3, 2, '2022-05-22'),
    (4, 3, '2023-05-23'),
    (5, 3, '2023-05-23');

INSERT INTO pay
VALUES
    (1, 2),
    (4, 3);

INSERT INTO employee
VALUES
    ('19374543511', '918273640', '1973-12-12', 'José Alfredo'),
    ('32948179321', '678549240', '1999-06-11', 'António Rebelo'),
    ('65849059803', '491238025', '1997-04-23', 'Margarida Sá'),
    ('89438992384', '587893123', '1988-09-05', 'Ana Júlia');
    
INSERT INTO process
VALUES
    ('19374543511', 1),
    ('19374543511', 2),
    ('19374543511', 3),
    ('32948179321', 4),
    ('32948179321', 5);

INSERT INTO department
VALUES
    ('Apoio ao cliente'),
    ('Processamento de encomendas'),
    ('Marketing');

INSERT INTO workplace
VALUES
    ('R. Mendes Ribeiro Silva Carminho, 9999-322 Lisboa', 38.736946, -9.142685),
    ('R. Alberto Portinhol, 2321-412 Braga', 41.545448, -8.426507),
    ('Av. Argélio Teixeira, 3271-231 Santarém', 39.284880, -8.704075);

INSERT INTO office
VALUES
    ('R. Mendes Ribeiro Silva Carminho, 9999-322 Lisboa');

INSERT INTO warehouse
VALUES
    ('R. Alberto Portinhol, 2321-412 Braga'),
    ('Av. Argélio Teixeira, 3271-231 Santarém');
    
INSERT INTO works
VALUES
    ('19374543511', 'Processamento de encomendas', 'R. Alberto Portinhol, 2321-412 Braga'),
    ('32948179321', 'Processamento de encomendas', 'Av. Argélio Teixeira, 3271-231 Santarém'),
    ('65849059803', 'Apoio ao cliente', 'R. Mendes Ribeiro Silva Carminho, 9999-322 Lisboa'),
    ('32948179321', 'Apoio ao cliente', 'R. Mendes Ribeiro Silva Carminho, 9999-322 Lisboa'),
    ('89438992384', 'Marketing', 'R. Mendes Ribeiro Silva Carminho, 9999-322 Lisboa');
    
INSERT INTO product
VALUES
    ('26H1UR7', 'Deodorant', 'Prevent and mask body odor', 6.56, 1098467501207),
    ('37J0LX1', 'Shampoo', 'Clean hair', 7.99, NULL),
    ('10P9QV2', 'Body wash', 'Clean the body', 15.99, NULL),
    ('99F5MN6', 'Bath sponge', 'Sponges for the shower', 1.49, NULL),
    ('32C1VQ4', 'Bath tub', 'Take a bath in it', 749.00, 5483210347392);
    
INSERT INTO contains
VALUES
    (1, '26H1UR7', 1),
    (1, '37J0LX1', 1),
    (1, '10P9QV2', 1),
    (2, '37J0LX1', 3),
    (3, '26H1UR7', 4),
    (4, '99F5MN6', 100),
    (4, '32C1VQ4', 1),
    (5, '32C1VQ4', 1);
    
INSERT INTO supplier
VALUES
    ('123409876', 'Bed Bath & Beyond', 'Queens Blvd, 1374-231 Coimbra', '99F5MN6', '2022-05-19'),
    ('543210094', 'Espaço Casa', 'R. Angelina Vidal 12-A R/C, 1170-019 Lisboa', '26H1UR7', '2021-12-03'),
    ('943290854', 'Leroy Merlin', 'Av. Berlim 10, 1800-038 Lisboa', '37J0LX1', '2021-12-03'),
    ('487542039', 'Lidl', 'R. Américo Rodrigues Barbosa 29, 4710-008 Braga', '10P9QV2', '2022-01-25'),
    ('312905843', 'IKEA', 'Zona Industrial de Alfragide Estrada Nacional 117, 2614-520 Amadora', '32C1VQ4', '2022-01-24');
    
INSERT INTO delivery
VALUES
    ('R. Alberto Portinhol, 2321-412 Braga', '543210094'),
    ('Av. Argélio Teixeira, 3271-231 Santarém', '123409876'),
    ('R. Alberto Portinhol, 2321-412 Braga', '943290854'),
    ('R. Alberto Portinhol, 2321-412 Braga', '487542039'),
    ('Av. Argélio Teixeira, 3271-231 Santarém', '312905843');