DROP TABLE auditer CASCADE CONSTRAINTS;
DROP TABLE affecter CASCADE CONSTRAINTS;
DROP TABLE tabl CASCADE CONSTRAINTS;
DROP TABLE contient CASCADE CONSTRAINTS;
DROP TABLE serveur CASCADE CONSTRAINTS;
DROP TABLE plat CASCADE CONSTRAINTS;
DROP TABLE commande CASCADE CONSTRAINTS;

CREATE TABLE commande (
  numcom  number(4),
  numtab  number(4),
  datcom  date,
  nbpers  number(2),
  datpaie date,
  modpaie varchar2(15),
  montcom number(8, 2),
  PRIMARY KEY (numcom)
);

CREATE TABLE plat (
  numplat  number(4),
  libelle  varchar2(40),
  type     varchar2(15),
  prixunit number(6, 2),
  PRIMARY KEY (numplat)
);

CREATE TABLE serveur (
  numserv number(2),
  nomserv varchar2(25),
  grade   varchar2(20),
  PRIMARY KEY (numserv)
);

CREATE TABLE tabl (
  numtab  number(4),
  nbplace number(2),
  PRIMARY KEY (numtab)
);

CREATE TABLE contient (
  numcom   number(4),
  numplat  number(4),
  quantite number(2),
  PRIMARY KEY (numcom, numplat)
);

CREATE TABLE affecter (
  numtab  number(4),
  dataff  date,
  numserv number(2),
  PRIMARY KEY (numtab, dataff)
);

ALTER TABLE commande ADD FOREIGN KEY (numtab) REFERENCES tabl (numtab);
ALTER TABLE affecter ADD FOREIGN KEY (numserv) REFERENCES serveur (numserv);
ALTER TABLE contient ADD FOREIGN KEY (numcom) REFERENCES commande (numcom);
ALTER TABLE contient ADD FOREIGN KEY (numplat) REFERENCES plat (numplat);

CREATE TABLE auditer (
  numcom  number(4),
  numtab  number(4),
  datcom  date,
  nbpers  number(2),
  datpaie date,
  montcom number(8, 2),
  PRIMARY KEY (numcom)
);

INSERT INTO serveur VALUES (1, 'Tutus Peter', 'maitre d''hotel');
INSERT INTO serveur VALUES (2, 'Lilo Vito', 'serveur g1');
INSERT INTO serveur VALUES (3, 'Don Carl', 'serveur g2');
INSERT INTO serveur VALUES (4, 'Leo Jon', 'serveur g1');
INSERT INTO serveur VALUES (5, 'Dean Geak', 'chef serveur');

INSERT INTO plat VALUES (1, 'assiette de crudités', 'Entrée', 25);
INSERT INTO plat VALUES (2, 'tarte de saison', 'Dessert', 25);
INSERT INTO plat VALUES (3, 'sorbet mirabelle', 'Dessert', 35);
INSERT INTO plat VALUES (4, 'filet de boeuf', 'Viande', 62);
INSERT INTO plat VALUES (5, 'salade verte', 'Entrée', 15);
INSERT INTO plat VALUES (6, 'chevre chaud', 'Entrée', 21);
INSERT INTO plat VALUES (7, 'pate lorrain', 'Entrée', 25);
INSERT INTO plat VALUES (8, 'saumon fumé', 'Entrée', 30);
INSERT INTO plat VALUES (9, 'entrecote printaniere', 'Viande', 58);
INSERT INTO plat VALUES (10, 'gratin dauphinois', 'Plat', 42);
INSERT INTO plat VALUES (11, 'brochet à l''oseille', 'Poisson', 68);
INSERT INTO plat VALUES (12, 'gigot d''agneau', 'Viande', 56);
INSERT INTO plat VALUES (13, 'crème caramel', 'Dessert', 15);
INSERT INTO plat VALUES (14, 'munster au cumin', 'Fromage', 18);
INSERT INTO plat VALUES (15, 'filet de sole au beurre', 'Poisson', 70);
INSERT INTO plat VALUES (16, 'fois gras de lorraine', 'Entrée', 61);

INSERT INTO tabl VALUES (10, 4);
INSERT INTO tabl VALUES (11, 6);
INSERT INTO tabl VALUES (12, 8);
INSERT INTO tabl VALUES (13, 4);
INSERT INTO tabl VALUES (14, 6);
INSERT INTO tabl VALUES (15, 4);
INSERT INTO tabl VALUES (16, 4);
INSERT INTO tabl VALUES (17, 6);
INSERT INTO tabl VALUES (18, 2);
INSERT INTO tabl VALUES (19, 4);

INSERT INTO commande VALUES (100, 10, '10/09/2016', 2, to_date('10/09/2016 20:50', 'dd/mm/yyyy hh24:mi'), 'Carte', NULL);
INSERT INTO commande VALUES (101, 11, '10/09/2016', 4, to_date('10/09/2016 21:20', 'dd/mm/yyyy hh24:mi'), 'Chèque', NULL);
INSERT INTO commande VALUES (102, 17, '10/09/2016', 2, to_date('10/09/2016 20:55', 'dd/mm/yyyy hh24:mi'), 'Carte', NULL);
INSERT INTO commande VALUES (103, 12, '10/09/2016', 2, to_date('10/09/2016 21:10', 'dd/mm/yyyy hh24:mi'), 'Espèces', NULL);
INSERT INTO commande VALUES (104, 18, '10/09/2016', 1, to_date('10/09/2016 21:00', 'dd/mm/yyyy hh24:mi'), 'Chèque', NULL);
INSERT INTO commande VALUES (105, 10, '10/09/2016', 2, to_date('10/09/2016 20:45', 'dd/mm/yyyy hh24:mi'), 'Carte', NULL);
INSERT INTO commande VALUES (106, 14, '11/10/2016', 2, to_date('11/10/2016 22:45', 'dd/mm/yyyy hh24:mi'), 'Carte', NULL);

INSERT INTO affecter VALUES (10, '10/09/2016', 1);
INSERT INTO affecter VALUES (11, '10/09/2016', 1);
INSERT INTO affecter VALUES (12, '10/09/2016', 1);
INSERT INTO affecter VALUES (17, '10/09/2016', 2);
INSERT INTO affecter VALUES (18, '10/09/2016', 2);
INSERT INTO affecter VALUES (15, '10/09/2016', 3);
INSERT INTO affecter VALUES (16, '10/09/2016', 3);
INSERT INTO affecter VALUES (10, '11/09/2016', 1);

INSERT INTO contient VALUES (100, 4, 2);
INSERT INTO contient VALUES (100, 5, 2);
INSERT INTO contient VALUES (100, 13, 1);
INSERT INTO contient VALUES (100, 3, 1);
INSERT INTO contient VALUES (101, 7, 2);
INSERT INTO contient VALUES (101, 16, 2);
INSERT INTO contient VALUES (101, 12, 2);
INSERT INTO contient VALUES (101, 15, 2);
INSERT INTO contient VALUES (101, 2, 2);
INSERT INTO contient VALUES (101, 3, 2);
INSERT INTO contient VALUES (102, 1, 2);
INSERT INTO contient VALUES (102, 10, 2);
INSERT INTO contient VALUES (102, 14, 2);
INSERT INTO contient VALUES (102, 2, 1);
INSERT INTO contient VALUES (102, 3, 1);
INSERT INTO contient VALUES (103, 9, 2);
INSERT INTO contient VALUES (103, 14, 2);
INSERT INTO contient VALUES (103, 2, 1);
INSERT INTO contient VALUES (103, 3, 1);
INSERT INTO contient VALUES (104, 7, 1);
INSERT INTO contient VALUES (104, 11, 1);
INSERT INTO contient VALUES (104, 14, 1);
INSERT INTO contient VALUES (104, 3, 1);
INSERT INTO contient VALUES (105, 3, 2);
INSERT INTO contient VALUES (106, 3, 2);

COMMIT;
