package controleur;

public class Reservation {
	 private int id_reservation;
	 private String date_debut , date_fin , statut ;
	 private float prix_total;
	 private int id_logement , id_utilisateur ;
	
	
	
	
	public Reservation(int id_reservation, String date_debut, String date_fin, String statut, float prix_total,
			int id_logement, int id_utilisateur) {
		super();
		this.id_reservation = id_reservation;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.statut = statut;
		this.prix_total = prix_total;
		this.id_logement = id_logement;
		this.id_utilisateur = id_utilisateur;
	} 
	
	public Reservation( String date_debut, String date_fin, String statut, float prix_total,
			int id_logement, int id_utilisateur) {
		super();
		this.id_reservation = 0;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.statut = statut;
		this.prix_total = prix_total;
		this.id_logement = id_logement;
		this.id_utilisateur = id_utilisateur;
	}

	public int getId_reservation() {
		return id_reservation;
	}

	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public float getPrix_total() {
		return prix_total;
	}

	public void setPrix_total(float prix_total) {
		this.prix_total = prix_total;
	}

	public int getId_logement() {
		return id_logement;
	}

	public void setId_logement(int id_logement) {
		this.id_logement = id_logement;
	}

	public int getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	} 

	
	
}
