-- Création de la table utilisateur
 CREATE TABLE utilisateur (
 id INT PRIMARY KEY AUTO_INCREMENT,
 nom VARCHAR(255) NOT NULL,
 prenom VARCHAR(255) NOT NULL,
 date_naissance DATE NOT NULL,
 adresse VARCHAR(255) NOT NULL,
 email VARCHAR(255) NOT NULL UNIQUE,
 login VARCHAR(50) NOT NULL UNIQUE,
 mot_de_passe VARCHAR(255) NOT NULL
 );-- Création de la table profil
 CREATE TABLE profil (
 id INT PRIMARY KEY AUTO_INCREMENT,
 utilisateur_id INT,
 nom VARCHAR(255) NOT NULL,
 prenom VARCHAR(255) NOT NULL,
 date_naissance DATE NOT NULL,
 adresse VARCHAR(255) NOT NULL,
 FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
 );-- Création de la table agence
 CREATE TABLE agence (
 id INT PRIMARY KEY AUTO_INCREMENT,
nom VARCHAR(255) NOT NULL,
 adresse VARCHAR(255) NOT NULL,
 ville VARCHAR(255) NOT NULL,
 pays VARCHAR(255) NOT NULL
 );-- Création de la table véhicule
 CREATE TABLE vehicule (
 id INT PRIMARY KEY AUTO_INCREMENT,
 categorie VARCHAR(255) NOT NULL,
 marque VARCHAR(255) NOT NULL,
 modele VARCHAR(255) NOT NULL,
 agence_id INT,
 statut VARCHAR(50) NOT NULL,
 FOREIGN KEY (agence_id) REFERENCES agence(id)
 );-- Création de la table réservation
 CREATE TABLE reservation (
 id INT PRIMARY KEY AUTO_INCREMENT,
 utilisateur_id INT,
 agence_depart_id INT,
 agence_retour_id INT,
 date_debut DATE NOT NULL,
 date_fin DATE NOT NULL,
 categorie_vehicule VARCHAR(255),
 statut VARCHAR(50) NOT NULL,
 tarif DECIMAL(10, 2) NOT NULL,
 paiement_id INT,
 FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id),
 FOREIGN KEY (agence_depart_id) REFERENCES agence(id),
 FOREIGN KEY (agence_retour_id) REFERENCES agence(id),
 FOREIGN KEY (paiement_id) REFERENCES paiement(id)
 );-- Création de la table paiement
 CREATE TABLE paiement (
 id INT PRIMARY KEY AUTO_INCREMENT,
 utilisateur_id INT,
 montant DECIMAL(10, 2) NOT NULL,
 date_paiement DATE NOT NULL,
 methode VARCHAR(50) NOT NULL,
 FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
 );-- Création de la table service_client
 CREATE TABLE service_client (
 id INT PRIMARY KEY AUTO_INCREMENT,
 utilisateur_id INT,
 type_contact VARCHAR(50) NOT NULL,
 message TEXT NOT NULL,
 date_contact DATE NOT NULL,
 statut VARCHAR(50) NOT NULL,
 FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
 );
-- Création de la table piece_jointe
 CREATE TABLE piece_jointe (
 id INT PRIMARY KEY AUTO_INCREMENT,
 service_client_id INT,
 type VARCHAR(50) NOT NULL,
 chemin_acces VARCHAR(255) NOT NULL,
 FOREIGN KEY (service_client_id) REFERENCES service_client(id)
 );-- Création de la table évaluation
 CREATE TABLE evaluation (
 id INT PRIMARY KEY AUTO_INCREMENT,
 utilisateur_id INT,
 reservation_id INT,
 note INT NOT NULL CHECK (note BETWEEN 1 AND 5),
 commentaire TEXT,
 date DATE NOT NULL,
 FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id),
 FOREIGN KEY (reservation_id) REFERENCES reservation(id)
 )