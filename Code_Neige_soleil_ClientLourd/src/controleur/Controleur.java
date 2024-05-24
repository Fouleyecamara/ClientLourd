package controleur;

import java.util.ArrayList;

import modele.modele;

public class Controleur {
	public static Gestionnaire selectwhereGestionnaire (String email , String mdp) {
		return modele.selectWhereGestionnaire(email, mdp);
	}

	public static void updateGestionnaire(Gestionnaire unGestionnaire) {
		
	modele.updateGestionnaire (unGestionnaire);
	
	} 
	/*** gestion des classes ****/
	
	public static void insertClient(Client unClient) {
		modele.insertClient(unClient);
	}
	
	public static ArrayList<Client> selectAllClients(String filtre ){
		return modele.selectAllClients(filtre);
	}

	public static Client selectWhereClient( String civilite,String nom, String prenom,  String email, String telephone, String mdp,
			String roles) {
		return modele.selectWhereClient( civilite,nom , prenom , email , telephone , mdp , roles );
		
	}
	public static void deleteClient (int id_utilisateur) {
		modele.deleteClient(id_utilisateur);
	}
	
	public static void updateClient (Client unClient) {
		modele.updateClient(unClient);
	}
	
	
/*** gestion des proprietaire  ****/
	
	public static void insertProprietaire(Proprietaire unProprietaire) {
		modele.insertProprietaire(unProprietaire);
	}
	
	public static ArrayList<Proprietaire> selectAllProprietaires(String filtre ){
		return modele.selectAllProprietaires(filtre);
	}

	public static Proprietaire selectWhereProprietaire( String nom, String prenom, String email, String telephone, String mdp,
			String roles) {
		return modele.selectWhereProprietaire( nom , prenom , email , telephone , mdp , roles );
		
	}
	public static void deleteProprietaire (int id_utilisateur) {
		modele.deleteProprietaire(id_utilisateur);
	}
	
	public static void updateProprietaire (Proprietaire unProprietaire) {
		modele.updateProprietaire(unProprietaire);
	}
	
	
/*** gestion des logement****/
	
	public static void insertLogement(Logement unLogement) {
		modele.insertLogement(unLogement);
	}

	public static ArrayList<Logement> selectAllLogements(String filtre) {
	    return modele.selectAllLogements(filtre);
	}

	public static Logement selectWhereLogement(String adresse, String ville, String type, String description, float prix, int capacite, String date_dispo, String datefin_dispo, String saison, int id_utilisateur) {
	    return modele.selectWhereLogement(adresse, ville, type, description, prix, capacite, date_dispo, datefin_dispo, saison, id_utilisateur);
	}

	public static void deleteLogement(int id_logement) {
	    modele.deleteLogement(id_logement);
	}

	public static void updateLogement(Logement unLogement) {
	    modele.updateLogement(unLogement);
	}


	
	/****** Gestions des reservation ******/
	
	
	public static void insertReservation(Reservation uneReservation) {
	    modele.insertReservation(uneReservation);
	}

	public static ArrayList<Reservation> selectAllReservations(String filtre) {
	    return modele.selectAllReservations(filtre);
	}

	public static Reservation selectWhereReservation( String date_debut, String date_fin, String statut, float prix_total,int id_logement, int id_utilisateur) {
	    return modele.selectWhereReservation(date_debut, date_fin, statut, prix_total, id_logement, id_utilisateur);
	}


	public static void deleteReservation(int id_reservation) {
	    modele.deleteReservation(id_reservation);
	}

	public static void updateReservation(Reservation uneReservation) {
	    modele.updateReservation(uneReservation);
	}

	
}

