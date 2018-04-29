CREATE TABLE IF NOT EXISTS NOTE (
  ID INT unsigned NOT NULL AUTO_INCREMENT,
  CONTENT VARCHAR(144) NOT NULL,
  AUTEUR VARCHAR (50) NOT NULL,
  DATE_AJOUT DATETIME NOT NULL,
  PRIMARY KEY (ID_NOTE)
);

INSERT INTO NOTE values (1, 'note de la bdd', 'Beyonce', NOW() );
INSERT INTO NOTE values (2, 'note n°2 de la bdd', 'Rihanna', NOW() );