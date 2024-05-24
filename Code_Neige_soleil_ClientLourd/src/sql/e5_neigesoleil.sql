CREATE TABLE Utilisateur(
   ID_Utilisateur INT AUTO_INCREMENT,
   Nom VARCHAR(50) NOT NULL,
   Prenom VARCHAR(50) NOT NULL,
   Email VARCHAR(50) NOT NULL,
   Telephone INT NOT NULL,
   Mdp VARCHAR(255) NOT NULL,
   Roles  enum('client','proprio','admin') NOT NULL,
   PRIMARY KEY(ID_Utilisateur)
);

CREATE TABLE Proprietaire(
  ID_Utilisateur INT AUTO_INCREMENT not null,
   Nom VARCHAR(50) NOT NULL,
   Prenom VARCHAR(50) NOT NULL,
   Email VARCHAR(50) NOT NULL,
   Telephone INT NOT NULL,
   Mdp VARCHAR(255) NOT NULL,
   Roles  enum('proprio') NOT NULL,
   PRIMARY KEY(ID_Utilisateur)
);

CREATE TABLE Client(
  ID_Utilisateur INT AUTO_INCREMENT not null,
   Nom VARCHAR(50) NOT NULL,
   Prenom VARCHAR(50) NOT NULL,
   Email VARCHAR(50) NOT NULL,
   Telephone INT NOT NULL,
   Mdp VARCHAR(255) NOT NULL,
   Roles  enum('client') NOT NULL,
   PRIMARY KEY(ID_Utilisateur)
);

CREATE TABLE Gestionnaire(
    ID_Utilisateur INT AUTO_INCREMENT not null,
   Nom VARCHAR(50) NOT NULL,
   Prenom VARCHAR(50) NOT NULL,
   Email VARCHAR(50) NOT NULL,
   Telephone INT NOT NULL,
   Mdp VARCHAR(255) NOT NULL,
   Roles  enum('admin') NOT NULL,
   PRIMARY KEY(ID_Utilisateur)
);

CREATE TABLE Logement(
   ID_Logement INT AUTO_INCREMENT,
   Adresse VARCHAR(50) NOT NULL,
   Ville VARCHAR(50) NOT NULL,
   Type VARCHAR(50) NOT NULL,
   Description VARCHAR(255) NOT NULL,
   Prix DECIMAL(15,2) NOT NULL,
   Capacite INT NOT NULL,
   date_dispo DATE,
   datefin_dispo DATE,
   Saison enum ('Soleil' , 'Neige') NOT NULL,
   ID_Utilisateur INT NOT NULL,
   PRIMARY KEY(ID_Logement),
   FOREIGN KEY(ID_Utilisateur) REFERENCES Proprietaire(ID_Utilisateur)
);

CREATE TABLE Reservation(
   id_reservation INT AUTO_INCREMENT,
   date_debut Varchar (255),
   date_din  varchar(255),
   statut VARCHAR(50) NOT NULL,
   prix_total DECIMAL (15,2),
   id_logement INT NOT NULL,
   id_utilisateur INT NOT NULL,
   PRIMARY KEY(id_reservation),
   FOREIGN KEY(ID_Logement) REFERENCES Logement(id_logement),
   FOREIGN KEY(ID_Utilisateur) REFERENCES Client(ID_Utilisateur)
);

CREATE TABLE Commentaire(
   ID_Commentaire INT AUTO_INCREMENT,
   Note INT NOT NULL,
   Contenu VARCHAR(255) NOT NULL,
   Date_Com DATE NOT NULL,
   ID_Logement INT NOT NULL,
   PRIMARY KEY(ID_Commentaire),
   FOREIGN KEY(ID_Logement) REFERENCES Logement(ID_Logement)
);

CREATE TABLE photo(
   ID_photo INT AUTO_INCREMENT,
   nom_photo VARCHAR(255) NOT NULL,
   ID_Logement INT NOT NULL,
   PRIMARY KEY(ID_photo),
   FOREIGN KEY(ID_Logement) REFERENCES Logement(ID_Logement)
);
Â