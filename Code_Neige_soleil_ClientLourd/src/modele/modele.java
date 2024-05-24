 package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import controleur.Client;
import controleur.Gestionnaire;
import controleur.Logement;
import controleur.Proprietaire;
import controleur.Reservation;

public class modele {
	private static Bdd uneBdd = new Bdd ("localhost" , "neige_soleil", "root" , "");
	
	public static Gestionnaire selectWhereGestionnaire (String email , String mdp) {
		Gestionnaire unGestionnaire = null ;
		String requete="select * from Gestionnaire where email=? and mdp = ?";
		try {
			uneBdd.seConnecter();
			PreparedStatement unStat= uneBdd.getMaconnexion().prepareStatement(requete); //curseur d'exécution
			 unStat.setString(1, email);
	            unStat.setString(2, mdp);
			//recuperation d'un utilisateur
			ResultSet  unRes = unStat.executeQuery();
		
			if(unRes.next()) {
				unGestionnaire = new Gestionnaire (
						unRes.getInt("id_utilisateur"), unRes.getString("nom"),
						unRes.getString("prenom"), unRes.getString("email"),unRes.getString("telephone"),
						unRes.getString("mdp"),unRes.getString("roles")
						);
						
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'excecution : "+requete);
		}
		
		
		return unGestionnaire ; 
	}

	public static void updateGestionnaire(Gestionnaire unGestionnaire) {
		String requete ="update Gestionnaire set "
				+ "nom='"+unGestionnaire.getNom()+
				"',prenom='"+unGestionnaire.getPrenom()+
					"',email='"+unGestionnaire.getEmail()+
					"',telephone='"+unGestionnaire.getTelephone()+
					"',mdp='"+unGestionnaire.getMdp()+
					"',roles='"+unGestionnaire.getRoles()+
					"' where id_utilisateur =" +unGestionnaire.getId_utilisateur()+";";
			try {
				uneBdd.seConnecter();
				Statement unStat= uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);	
				unStat.close();
				uneBdd.seDeconnecter();
			}catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
	}
	
	/****** GESTION DES CLIENTS *****/
	 public static void insertClient(Client unClient){
		 
		 String requete = "INSERT INTO Client VALUES (null , '" + unClient.getCivilite() + "', '" + unClient.getNom() + "', '" + unClient.getPrenom() + "', '" 
		 + unClient.getEmail() + "', '" + unClient.getTelephone() + "', '"+ unClient.getMdp() 
		 + "', '" + unClient.getRoles() + "');";

		 try {
				uneBdd.seConnecter();
				Statement unStat= uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
	 }
	 
	 public static ArrayList<Client> selectAllClients(String filtre){
		 String requete ="";
		 
		 if (filtre .equals("")) {
			 requete ="select * from client;";
		 }else {
			 requete = "SELECT * FROM client WHERE  OR civilite LIKE '%" + filtre + "%' OR nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%' OR email LIKE '%" + filtre
					 + "%' OR telephone LIKE '%" + filtre + "%' OR roles LIKE '%" 
					 + filtre + "%';";

		 }
		 
		 
		 ArrayList<Client> lesClients = new ArrayList<Client>();
		 
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaconnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			 // on parcours les résultat pour construire ArrayList
			 while (desRes.next()) {
				 Client unClient = new Client(
						 desRes.getInt("id_utilisateur") ,desRes.getString("civilite"), desRes.getString("nom"),
						 desRes.getString("prenom"),desRes.getString("email"),
						 desRes.getString("telephone"),desRes.getString("mdp"),
						 desRes.getString("roles")
						 );
				 lesClients.add(unClient);
			 }
			 unStat.close();
			 uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
		 
		 return lesClients;
	 }

	public static Client selectWhereClient(String civilite, String nom, String prenom,  String email, String telephone, String mdp,
			String roles) {
		String requete = "SELECT * FROM client WHERE civilite='" +civilite+ "' And  nom='" + nom + "' AND prenom='" + prenom + "' AND email='" + email + 
				"' AND telephone=" + telephone + 
				" AND mdp='" + mdp + "' AND roles='client';" ;
		Client unClient = null ;
		try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaconnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			
			 if (desRes.next()) {
				 unClient = new Client(
						 desRes.getInt("id_utilisateur"), desRes.getString("civilite"),desRes.getString("nom"),
						 desRes.getString("prenom"),desRes.getString("email"),
						 desRes.getString("telephone"),desRes.getString("mdp"),
						 desRes.getString("roles")
						 );

			 }
			 unStat.close();
			 uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
		return unClient;
	}
	public static void deleteClient (int id_utilisateur) {
		String requete = "delete from client where id_utilisateur = ?;";
		try {
			uneBdd.seConnecter();
			PreparedStatement unStat= uneBdd.getMaconnexion().prepareStatement(requete);
			unStat.setInt(1, id_utilisateur);
			unStat.execute();
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'excecution : "+requete);
		}
		
	}
	public static void updateClient (Client unClient) {
		String requete = "UPDATE client SET	 civilite = '" + unClient.getCivilite() +"',  nom = '" + unClient.getNom() + 
				"', prenom = '" + unClient.getPrenom() + "', email = '" + unClient.getEmail() + 
				"', telephone = " + unClient.getTelephone() + 
				", mdp = '" + unClient.getMdp() + 
				"', roles = '" + unClient.getRoles() + 
			
				"' WHERE id_utilisateur = " + unClient.getId_utilisateur() + ";";

		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'excecution : "+requete);
		}
		
	}


	 
	 
	 
	 /****** GESTION DES PROPRIETAIRE  *****/
	
	 
	public static void insertProprietaire(Proprietaire unProprietaire){
		 
		 String requete = "INSERT INTO proprietaire VALUES (null, '" + unProprietaire.getNom() + "', '" + unProprietaire.getPrenom() + "', '" 
		 + unProprietaire.getEmail() + "', '" + unProprietaire.getTelephone() + "', '"+ unProprietaire.getMdp() + "', '" + unProprietaire.getRoles() + "');";

		 try {
				uneBdd.seConnecter();
				Statement unStat= uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
	 }
	 
	 public static ArrayList<Proprietaire> selectAllProprietaires(String filtre){
		 String requete ="";
		 
		 if (filtre .equals("")) {
			 requete ="select * from proprietaire;";
		 }else {
			 requete = "SELECT * FROM proprietaire WHERE nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%' OR email LIKE '%" + filtre + "%' OR telephone LIKE '%" + filtre + "%' OR roles LIKE '%" + filtre + "%';";

		 }
		 
		 
		 ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>();
		 
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaconnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			 // on parcours les résultat pour construire ArrayList
			 while (desRes.next()) {
				 Proprietaire unProprietaire = new Proprietaire(
						 desRes.getInt("id_utilisateur"),desRes.getString("nom"),
						 desRes.getString("prenom"),desRes.getString("email"),
						 desRes.getString("telephone"),desRes.getString("mdp"),
						 desRes.getString("roles")
						 );
				 lesProprietaires.add(unProprietaire);
			 }
			 unStat.close();
			 uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
		 
		 return lesProprietaires;
	 }

	public static Proprietaire selectWhereProprietaire( String nom, String prenom, String email, String telephone, String mdp,
			String roles) {
		String requete = "SELECT * FROM proprietaire WHERE nom='" + nom + "' AND prenom='" + prenom + "' AND email='" + email + 
				"' AND telephone=" + telephone + 
				" AND mdp='" + mdp + "' AND roles='proprio';";
		Proprietaire unProprietaire = null ;
		try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaconnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			
			 if (desRes.next()) {
				 unProprietaire = new Proprietaire(
						 desRes.getInt("id_utilisateur"),desRes.getString("nom"),
						 desRes.getString("prenom"),desRes.getString("email"),
						 desRes.getString("telephone"),desRes.getString("mdp"),
						 desRes.getString("roles")
						 );

			 }
			 unStat.close();
			 uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
		return unProprietaire;
	}
	public static void deleteProprietaire (int id_utilisateur) {
		String requete = "delete from proprietaire where id_utilisateur ="+id_utilisateur+";";
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'excecution : "+requete);
		}
		
	}
	public static void updateProprietaire (Proprietaire unProprietaire) {
		String requete = "UPDATE Proprietaire SET nom = '" + unProprietaire.getNom() + 
				"', prenom = '" + unProprietaire.getPrenom() + 
				"', email = '" + unProprietaire.getEmail() + 
				"', telephone = " + unProprietaire.getTelephone() + 
				", mdp = '" + unProprietaire.getMdp() + 
				"', roles = '" + unProprietaire.getRoles() + 
				"' WHERE id_utilisateur = " + unProprietaire.getId_utilisateur() + ";";

		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'excecution : "+requete);
		}
		
	}
	 
	 
	 
	 
	 
	 
	 
	 /****** GESTION Logement  *****/
	
	
	 public static void insertLogement(Logement unLogement){
		 
		 String requete = "INSERT INTO logement VALUES (null, '" + unLogement.getAdresse() + "', '" + unLogement.getVille() + "', '" 
		 + unLogement.getType() + "', '" + unLogement.getDescription() + "', '" + unLogement.getPrix()+ "', '" + unLogement.getCapacite()+ "', '" + unLogement.getDate_dispo()+
		 "', '" + unLogement.getDatefin_dispo()+"', '" + unLogement.getSaison()+"', '" + unLogement.getId_utilisateur()+"');";

		 try {
				uneBdd.seConnecter();
				Statement unStat= uneBdd.getMaconnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}catch(SQLException exp) {
				System.out.println("Erreur d'excecution : "+requete);
			}
	 }
	
	 
	 public static ArrayList<Logement> selectAllLogements(String filtre) {
		    String requete = "";
		    ArrayList<Logement> lesLogements = new ArrayList<Logement>();

		    try {
		        if (filtre.equals("")) {
		            requete = "SELECT * FROM logement;";
		        } else {
		            requete = "SELECT * FROM logement WHERE adresse LIKE '%" + filtre + "%' OR ville LIKE '%" + filtre + "%' OR type LIKE '%" + filtre + "%' OR description LIKE '%" + filtre + "%' OR saison LIKE '%" + filtre + "%' OR capacite LIKE '%" + filtre + "%' OR prix LIKE '%" + filtre + "%';";
		        }

		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        ResultSet desRes = unStat.executeQuery(requete);

		        // Parcours des résultats pour construire la liste des logements
		        while (desRes.next()) {
		            Logement unLogement = new Logement(
		                    desRes.getInt("id_logement"),
		                    desRes.getString("adresse"),
		                    desRes.getString("ville"),
		                    desRes.getString("type"),
		                    desRes.getString("description"),
		                    desRes.getFloat("prix"),
		                    desRes.getInt("capacite"),
		                    desRes.getString("date_dispo"),
		                    desRes.getString("datefin_dispo"),
		                    desRes.getString("saison"),
		                    desRes.getInt("id_utilisateur")
		            );
		            lesLogements.add(unLogement);
		        }

		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur d'exécution : " + requete);
		        exp.printStackTrace(); // Affichez la trace de la pile pour obtenir plus d'informations sur l'erreur
		    }

		    return lesLogements;
		}


	 
	 public static Logement selectWhereLogement(String adresse, String ville, String type, String description, float prix, int capacite, String date_dispo, String datefin_dispo, String saison, int id_utilisateur) {
		    String requete = "SELECT * FROM logement WHERE adresse='" + adresse + "' AND ville='" + ville + "' AND type='" + type + 
		            "' AND description='" + description + "' AND prix=" + prix + " AND capacite=" + capacite + " AND date_dispo='" + date_dispo +
		            "' AND datefin_dispo='" + datefin_dispo + "' AND saison='" + saison + "' AND id_utilisateur=" + id_utilisateur + ";";
		    
		    Logement unLogement = null ;
		    try {
		         uneBdd.seConnecter();
		         Statement unStat = uneBdd.getMaconnexion().createStatement();
		         ResultSet desRes = unStat.executeQuery(requete);
		        
		         if (desRes.next()) {
		             unLogement = new Logement(
		                     desRes.getInt("id_logement"),
		                     desRes.getString("adresse"),
		                     desRes.getString("ville"),
		                     desRes.getString("type"),
		                     desRes.getString("description"),
		                     desRes.getFloat("prix"),
		                     desRes.getInt("capacite"),
		                     desRes.getString("date_dispo"),
		                     desRes.getString("datefin_dispo"),
		                     desRes.getString("saison"),
		                     desRes.getInt("id_utilisateur")
		                     );
		         }
		         unStat.close();
		         uneBdd.seDeconnecter();
		     }
		     catch(SQLException exp) {
		            System.out.println("Erreur d'excecution : "+requete);
		     }
		    return unLogement;
		}

	 public static void deleteLogement(int id_logement) {
		    String requete = "DELETE FROM logement WHERE id_logement =" + id_logement + ";";
		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        unStat.execute(requete);
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur d'exécution : " + requete);
		    }
		}

		public static void updateLogement(Logement unLogement) {
		    String requete = "UPDATE logement SET adresse = '" + unLogement.getAdresse() +
		            "', ville = '" + unLogement.getVille() +
		            "', type = '" + unLogement.getType() +
		            "', description = '" + unLogement.getDescription() +
		            "', prix = " + unLogement.getPrix() +
		            ", capacite = " + unLogement.getCapacite() +
		            ", date_dispo = '" + unLogement.getDate_dispo() +
		            "', datefin_dispo = '" + unLogement.getDatefin_dispo() +
		            "', saison = '" + unLogement.getSaison() +
		            "' WHERE id_logement = " + unLogement.getId_logement() + ";";

		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        unStat.execute(requete);
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur d'exécution : " + requete);
		    }
		}

	 /******* Gestion des reservations ******/
		
		public static void insertReservation(Reservation uneReservation) {
		    String requete = "INSERT INTO Reservation VALUES (null , '" +
		        uneReservation.getDate_debut() + "', '" +
		        uneReservation.getDate_fin() + "', '" +
		        uneReservation.getStatut() + "', " +
		        uneReservation.getPrix_total() + ", " +
		        uneReservation.getId_logement() + ", " +
		        uneReservation.getId_utilisateur() + ");";

		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        unStat.execute(requete);
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch(SQLException exp) {
		        System.out.println("Erreur d'exécution : " + requete);
		    }
		}

		public static ArrayList<Reservation> selectAllReservations(String filtre) {
		    String requete = "";
		    ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();

		    try {
		        if (filtre.equals("")) {
		            requete = "SELECT * FROM reservation;";
		        } else {
		            requete = "SELECT * FROM reservation WHERE statut LIKE '%" + filtre + "%';";
		        }

		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        ResultSet desRes = unStat.executeQuery(requete);

		        // Parcours des résultats pour construire la liste des réservations
		        while (desRes.next()) {
		            Reservation uneReservation = new Reservation(
		                    desRes.getInt("id_reservation"),
		                    desRes.getString("date_debut"),
		                    desRes.getString("date_fin"),
		                    desRes.getString("statut"),
		                    desRes.getFloat("prix_total"),
		                    desRes.getInt("id_logement"),
		                    desRes.getInt("id_utilisateur")
		            );
		            lesReservations.add(uneReservation);
		        }

		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur d'exécution : " + requete);
		        exp.printStackTrace(); // Affichez la trace de la pile pour obtenir plus d'informations sur l'erreur
		    }

		    return lesReservations;
		}

		public static void deleteReservation(int id_reservation) {
		    String requete = "DELETE FROM reservation WHERE id_reservation =" + id_reservation + ";";
		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        unStat.execute(requete);
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur d'exécution : " + requete);
		    }
		}
		
		
		
		public static Reservation selectWhereReservation( String date_debut, String date_fin, String statut, float prix_total, int id_logement, int id_utilisateur) {
		    String requete = "SELECT * FROM reservation WHERE date_debut='" + date_debut + "' AND date_fin='" + date_fin + "' AND statut='" + statut + "' AND prix_total=" + prix_total + " AND id_logement=" + id_logement + " AND id_utilisateur=" + id_utilisateur + ";";
		    
		    Reservation uneReservation = null;
		    try {
		         uneBdd.seConnecter();
		         Statement unStat = uneBdd.getMaconnexion().createStatement();
		         ResultSet desRes = unStat.executeQuery(requete);
		        
		         if (desRes.next()) {
		             uneReservation = new Reservation(
		                     desRes.getInt("id_reservation"),
		                     desRes.getString("date_debut"),
		                     desRes.getString("date_fin"),
		                     desRes.getString("statut"),
		                     desRes.getFloat("prix_total"),
		                     desRes.getInt("id_logement"),
		                     desRes.getInt("id_utilisateur")
		                     );
		         }
		         unStat.close();
		         uneBdd.seDeconnecter();
		     }
		     catch(SQLException exp) {
		            System.out.println("Erreur d'excecution : "+requete);
		     }
		    return uneReservation;
		}

		

		public static void updateReservation(Reservation uneReservation) {
		    String requete = "UPDATE reservation SET date_debut = '" + uneReservation.getDate_debut() +
		            "', date_fin = '" + uneReservation.getDate_fin() +
		            "', statut = '" + uneReservation.getStatut() +
		            "', prix_total = " + uneReservation.getPrix_total() +
		            ", id_logement = " + uneReservation.getId_logement() +
		            ", id_utilisateur = " + uneReservation.getId_utilisateur() +
		            " WHERE id_reservation = " + uneReservation.getId_reservation() + ";";

		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        unStat.execute(requete);
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur d'exécution : " + requete);
		    }
		}

		

	 
}
