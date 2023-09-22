INSERT INTO Customer
VALUES
    (DEFAULT, 'Gonçalo Rua', 'gr@gmail.com', 911111111, 'ABCD 1000'),
    (DEFAULT, 'João Gouveia', 'jg@gmail.com', 922222222, 'EFGH 2000'),
    (DEFAULT, 'Francisco Fonseca', 'ff@gmail.com', 933333333, 'IJKL 3000');

INSERT INTO Product
VALUES
    ('26H1UR7', 'Deodorant', 'Prevent and mask body odor', 656),
    ('37J0LX1', 'Shampoo', 'Clean hair', 799),
    ('10P9QV2', 'Body wash', 'Clean the body', 1599),
    ('99F5MN6', 'Bath sponge', 'Sponges for the shower', 149),
    ('32C1VQ4', 'Bath tub', 'Take a bath in it', 74900);
    
INSERT INTO "EAN Product"
VALUES
    ('26H1UR7', 1098467501207),
    ('32C1VQ4', 5483210347392);
    
INSERT INTO "Order"
VALUES
    (DEFAULT, '2023-01-26', 2),
    (DEFAULT, '2023-01-28', 1),
    (DEFAULT, '2023-05-22', 2),
    (DEFAULT, '2023-05-23', 3);
    
INSERT INTO Sale
VALUES
    (1, 2),
    (2, 1),
    (3, 2),
    (4, 3);
    
INSERT INTO contains
VALUES
    (1, '26H1UR7', 1),
    (1, '37J0LX1', 1),
    (1, '10P9QV2', 1),
    (2, '37J0LX1', 3),
    (3, '26H1UR7', 4),
    (4, '99F5MN6', 100),
    (4, '32C1VQ4', 1);
    
INSERT INTO Supplier
VALUES
    (123409876, 'Bed Bath & Beyond', '96-05 Queens Blvd, Queens, NY 11374, EUA'),
    (543210094, 'Espaço Casa', 'R. Angelina Vidal 12-A R/C, 1170-019 Lisboa'),
    (943290854, 'Leroy Merlin', 'Av. Berlim 10, 1800-038 Lisboa'),
    (487542039, 'Lidl', 'R. Américo Rodrigues Barbosa 29, 4710-008 Braga'),
    (312905843, 'IKEA', 'Zona Industrial de Alfragide Estrada Nacional 117, 2614-520 Amadora');
    
INSERT INTO "supply-contract"
VALUES
    (543210094, '26H1UR7', '2022-12-03'),
    (943290854, '37J0LX1', '2022-12-03'),
    (312905843, '32C1VQ4', '2023-01-24'),
    (487542039, '10P9QV2', '2023-01-25'),
    (123409876, '99F5MN6', '2023-05-19');
    
INSERT INTO Employee
VALUES
    (19374543511, 918273640, '1973-12-12', 'José Alfredo'),
    (32948179321, 678549240, '1999-06-11', 'António Rebelo'),
    (65849059803, 491238025, '1997-04-23', 'Margarida Sá'),
    (89438992384, 587893123, '1988-09-05', 'Ana Júlia');
    
INSERT INTO process
VALUES
    (1, 19374543511),
    (2, 19374543511),
    (3, 19374543511),
    (4, 32948179321);

INSERT INTO Department
VALUES
    ('Apoio ao cliente'),
    ('Processamento de encomendas'),
    ('Marketing');
    
INSERT INTO Workplace
VALUES
    ('R. Mendes Ribeiro Silva Carminho, 9999-32', 38.736946, -9.142685),
    ('R. Alberto Portinhol, 2321-412', 41.545448, -8.426507),
    ('Av. Argélio Teixeira 3271-231', 39.284880, -8.704075);
    
INSERT INTO Office
VALUES
    ('R. Mendes Ribeiro Silva Carminho, 9999-32');

INSERT INTO Warehouse
VALUES
    ('R. Alberto Portinhol, 2321-412'),
    ('Av. Argélio Teixeira 3271-231');
    
INSERT INTO works
VALUES
    (19374543511, 'Processamento de encomendas', 'R. Alberto Portinhol, 2321-412'),
    (32948179321, 'Processamento de encomendas', 'Av. Argélio Teixeira 3271-231'),
    (65849059803, 'Apoio ao cliente', 'R. Mendes Ribeiro Silva Carminho, 9999-32'),
    (32948179321, 'Apoio ao cliente', 'R. Mendes Ribeiro Silva Carminho, 9999-32'),
    (89438992384, 'Marketing', 'R. Mendes Ribeiro Silva Carminho, 9999-32');
    
INSERT INTO delivery
VALUES
    ('R. Alberto Portinhol, 2321-412', 543210094),
    ('Av. Argélio Teixeira 3271-231', 123409876),
    ('R. Alberto Portinhol, 2321-412', 943290854),
    ('R. Alberto Portinhol, 2321-412', 487542039),
    ('Av. Argélio Teixeira 3271-231', 312905843);