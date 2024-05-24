package controleur;


public class Logement {
	private int id_logement;
	private String adresse , ville , type , description ;
	private float prix ;
	private int capacite ;
	private String date_dispo, datefin_dispo, saison;
	
	private int id_utilisateur;

	public Logement(int id_logement, String adresse, String ville, String type, String description, float prix,
			int capacite, String date_dispo, String datefin_dispo, String saison, int id_utilisateur) {
		super();
		this.id_logement = id_logement;
		this.adresse = adresse;
		this.ville = ville;
		this.type = type;
		this.description = description;
		this.prix = prix;
		this.capacite = capacite;
		this.date_dispo = date_dispo;
		this.datefin_dispo = datefin_dispo;
		this.saison = saison;
		this.id_utilisateur = id_utilisateur;
	}
	
	public Logement( String adresse, String ville, String type, String description, float prix,
			int capacite, String date_dispo, String datefin_dispo, String saison, int id_utilisateur) {
		super();
		this.id_logement = 0;
		this.adresse = adresse;
		this.ville = ville;
		this.type = type;
		this.description = description;
		this.prix = prix;
		this.capacite = capacite;
		this.date_dispo = date_dispo;
		this.datefin_dispo = datefin_dispo;
		this.saison = saison;
		this.id_utilisateur = id_utilisateur;
	}

	public int getId_logement() {
		return id_logement;
	}

	public void setId_logement(int id_logement) {
		this.id_logement = id_logement;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public String getDate_dispo() {
		return date_dispo;
	}

	public void setDate_dispo(String date_dispo) {
		this.date_dispo = date_dispo;
	}

	public String getDatefin_dispo() {
		return datefin_dispo;
	}

	public void setDatefin_dispo(String datefin_dispo) {
		this.datefin_dispo = datefin_dispo;
	}

	public String getSaison() {
		return saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}

	public int getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	
	
	
}