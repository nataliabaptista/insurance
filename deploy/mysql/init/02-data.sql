INSERT INTO db_insurance.tb_products VALUES
(1, 'Seguro Residencial', current_date, 'userXpto', true),
(2, 'Seguro vida', current_date, 'userXpto', true),
(3, 'Seguro de Acidentes Pessoais', current_date, 'userXpto', true),
(4, 'Seguro de Automovel', current_date, 'userXpto', true),
(5, 'Cartao Protegido', current_date, 'userXpto', true);


INSERT INTO db_insurance.tb_coverages VALUES
(1, 'Incendio, Raio e Explosao', 10000.00),
(2, 'Roubo e Furto de Bens', 50000.00),
(3, 'Danos Eletricos', 10000.00),
(4, 'Morte qualquer causa', 100000.00),
(5, 'Invalidez por acidente', 50000.00),
(6, 'Doencas graves', 20000.00),
(7, 'Morte acidental', 100000.00),
(8, 'Internacao hospitalar', 10000.00),
(9, 'Batida parcial ou total', 50000.00),
(10, 'Roubo e furto', 100000.00),
(11, 'Cobertura a terceiros', 250000.00),
(12, 'Saque e Transacoes sob Coacao', 10000.00),
(13, 'Roubo ou Furto apos Saque', 10000.00),
(14, 'Bolsa Protegida', 10000.00);


INSERT INTO db_insurance.tb_assistances VALUES
(1, 'Chaveiro, Eletricista, Encanador'),
(2, 'Assistencia funeral, Assistencia PET'),
(3, 'Chaveiro, troca de pneu e auxilio mecanico'),
(4, 'Reparos para vidros, farois e retrovisores');

INSERT INTO db_insurance.tb_amounts VALUES
(1, 100.00, 200.00, 150.00),
(2, 500.00, 250.00, 350.00),
(3, 1000.00, 500.00, 750.00),
(4, 10000.00, 3000.00, 5000.00),
(5, 50000.00, 10000.00, 25000.00);

INSERT INTO db_insurance.tb_offers VALUES
(1, 'Seguro Residencial Basico', current_date, 'userXpto', true, 1, 2, NULL, NULL, NULL, 1, NULL, 1),
(2, 'Seguro Residencial Completo', current_date, 'userXpto', true, 1, 1, 2, 3, NULL,  1, NULL, 2),
(3, 'Seguro vida Basico', current_date, 'userXpto', true, 2, 4, NULL, NULL, NULL, 2, NULL, 2),
(4, 'Seguro vida Completo', current_date, 'userXpto', true, 2, 4, 5, 6, 7, 2, NULL, 3),
(5, 'Seguro de Acidentes Pessoais Basico', current_date, 'userXpto', true, 3, 5, NULL, NULL, NULL, 2, NULL, 3),
(6, 'Seguro de Acidentes Pessoais Completo', current_date, 'userXpto', true, 3, 5, 6, 7, 8, 2, NULL, 4),
(7, 'Seguro de Automovel Basico', current_date, 'userXpto', true, 4, 9, NULL, NULL, NULL, 3, NULL, 3),
(8, 'Seguro de Automovel Completo', current_date, 'userXpto', true, 4, 9, 10, 11, NULL, 3, 4, 5),
(9, 'Cartao Protegido Basico', current_date, 'userXpto', true, 5, 13, NULL, NULL, NULL, NULL, NULL, 1),
(10, 'Cartao Protegido Completo', current_date, 'userXpto', true, 5, 12, 13, 14, NULL, NULL, NULL, 2);


INSERT INTO db_insurance.tb_customers VALUES
('00055577712', 'Nome clinte Um', 'F', 'Male', '1996-12-10', 'email@email.com', '551498881111'),
('99944477722', 'Nome clinte Dois', 'F', 'Female', '1996-10-10', 'email2@email.com', '551498886666'),
('36699978944', 'Nome clinte Tres', 'F', 'Male', '1996-02-10', 'email3@email.com', '5514911114444');

