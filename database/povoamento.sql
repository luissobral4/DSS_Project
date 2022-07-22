USE DSS_Project;

INSERT INTO Prateleira VALUES 
	(1, 5, 1),
    (2, 5, 0),
    (3, 5, 0),
    (4, 5, 2),
    (5, 5, 1),
    (6, 5, 0),
    (7, 5, 1),
    (8, 5, 0),
    (9, 5, 0),
    (10, 5, 0);

INSERT INTO Localizacao VALUES 
	(1, "Armazenamento", 1),
    (2, "Armazenamento", 4),
    (3, "Armazenamento", 4),
    (4, "Armazenamento", 1),
    (5, "Rececao", null),
    (6, "Armazenamento", 4),
    (7, "Armazenamento", 5),
    (8, "Armazenamento", 6);

INSERT INTO Palete VALUES 
	('a1', 1),
    ('a2', 2),
    ('a3', 3),
    ('a4', 4),
    ('a5', 5),
    ('a6', 6),
    ('a7', 7);

INSERT INTO InfoTransporte VALUES (1, 'a6', 2);

INSERT INTO Robot VALUES 
	(1, 0, 0, 1, 6),
    (2, 1, 0, null, 7),
    (3, 1, 0, null, 8);

INSERT INTO Gestor VALUES ('1', 'Paulo Sousa', 'root', 0);