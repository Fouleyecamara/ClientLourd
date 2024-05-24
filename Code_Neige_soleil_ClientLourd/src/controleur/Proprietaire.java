package controleur;

public class Proprietaire {
	
private int id_utilisateur ; 
	
	private String nom , prenom, email ,telephone, mdp , roles;

	public Proprietaire(int id_utilisateur, String nom, String prenom, String email, String telephone, String mdp,
			String roles) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.mdp = mdp;
		this.roles = roles;
	}
	
	public Proprietaire(String nom, String prenom, String email, String telephone, String mdp,
			String roles) {
		super();
		this.id_utilisateur = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.mdp = mdp;
		this.roles = roles;
	}
	
	public Proprietaire() {
		super();
		this.id_utilisateur = 0;
		
		this.nom = "";
		this.prenom = "";
		this.telephone = "";
		this.email = "";
		this.mdp = "";
		this.roles = "";
	}

	public int getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

}
