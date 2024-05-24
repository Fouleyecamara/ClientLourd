package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Logement;
import controleur.Proprietaire;
import controleur.Tableau;

public class PanelLogement extends PanelPrincipal implements ActionListener {

	private JPanel panelForm = new JPanel(); //panel formulaire
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtAdresse = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtType= new JTextField();
	private JTextField txtDescription = new JTextField();
	private JTextField txtPrix = new JTextField();
	private JTextField txtCapacite = new JTextField();
	private JTextField txtDateDispo = new JTextField(); 
	private JTextField txtDateFinDispo = new JTextField(); 
	private JComboBox<String> saisonComboBox = new JComboBox<>(new String[]{"soleil", "neige"}); 

	
	private static JComboBox<String> txtIdProprietaire = new JComboBox<String>(); //permet de faire la selection du 

	private JPanel panelListe = new JPanel(); //permet d'afficher ce qu'on a mis dans le formualaire
	private JTable tableLogement;
	private JScrollPane uneScroll;
	
	private Tableau unTableau ; 
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	
	private JLabel lbNbLogement = new JLabel ("Nombre de logements : ");
	
	
	public PanelLogement() {
		super("Gestion Logements");
		this.lbNbLogement.setBounds(400 , 360 , 300 , 20);
		this.add(this.lbNbLogement);
		
		
		this.panelForm.setBounds(20,80,300,250);
		this.panelForm.setBackground(Color.white);
		this.panelForm.setLayout(new GridLayout (11,2));
		
		this.panelForm.add(new JLabel("Adresse: "));
		this.panelForm.add(this.txtAdresse);
		
		this.panelForm.add(new JLabel("Ville : "));
		this.panelForm.add(this.txtVille);
		
		this.panelForm.add(new JLabel("Type : "));
		this.panelForm.add(this.txtType);
		
		this.panelForm.add(new JLabel("Description : "));
		this.panelForm.add(this.txtDescription);
		
		this.panelForm.add(new JLabel("Prix : "));
		this.panelForm.add(this.txtPrix);
		
		this.panelForm.add(new JLabel("Capacite : "));
		this.panelForm.add(this.txtCapacite);
		
		this.panelForm.add(new JLabel("Date début : "));
		this.panelForm.add(this.txtDateDispo);
		
		this.panelForm.add(new JLabel("Date fin: "));
		this.panelForm.add(this.txtDateFinDispo);
		
		this.panelForm.add(new JLabel("Saison : "));
		this.panelForm.add(this.saisonComboBox);

		this.panelForm.add(new JLabel("Proprietaire : "));
		this.panelForm.add(this.txtIdProprietaire);

		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.add(this.panelForm);
		
		//remplir le CBX proprietaire
		this.remplirCBXProprietaire();
		
		
		//construire le panelList
				this.panelListe.setBounds(370,130,900,200);
				this.panelListe.setBackground(new Color(255, 160, 122 ));
				this.panelListe.setLayout(null);
				String entetes[] = {"ID Logement" , "Adresse" , "Ville" , "Type" , 
						"Description" , "Prix" , "Capacite", 
						"Date de début", "Date de fin", "Saison", 
						"Proprietaire"};
				
				this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
				this.tableLogement = new JTable (this.unTableau);
				
				this.uneScroll = new JScrollPane(this.tableLogement);
				this.uneScroll.setBounds(0,0,460,200);
				this.panelListe.add(this.uneScroll);
				this.add(this.panelListe);
				
				
				
				//construction du Panel filtre
				this.panelFiltre.setBackground(new Color(255, 160, 122 ));
				this.panelFiltre.setBounds(370 , 90 , 460  , 30);
				this.panelFiltre.setLayout(new GridLayout(1 ,3));
				
				this.panelFiltre.add(new JLabel ("Filtrer les Logements: "));
				this.panelFiltre.add(this.txtFiltre);
				this.panelFiltre.add(this.btFiltrer);
				this.add(this.panelFiltre);
				
		
		
		//rendre les bouton ecoutable 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
		
		//rendre la table non editable 
				this.tableLogement.getTableHeader().setReorderingAllowed(false);
				
				//on affiche le nombre de classe 
				this.lbNbLogement.setText("Nombre de Logement: "+this.unTableau.getRowCount());
				
	
	
	
	
	//mise en place de MouseListener sur la table 
			this.tableLogement.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					int numLigne = 0;
					int id_logement = 0;
					if (e.getClickCount()>=2) { //quand on clique deux fois cela va afficher le bouton pour le supprimer
						numLigne = tableLogement.getSelectedRow();
						id_logement = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce logement ?", "Suppression du logement" , JOptionPane.YES_NO_OPTION);
						if (reponse == 0) {
						    // Suppression dans la BDD
						    Controleur.deleteLogement(id_logement);

						    // Actualisation de l'affichage
						    unTableau.supprimerLigne(numLigne);
						    txtAdresse.setText("");
						    txtVille.setText("");
						    txtType.setText("");
						    txtDescription.setText("");
						    txtPrix.setText("");
						    txtCapacite.setText("");
						    txtDateDispo.setText("");
						    txtDateFinDispo.setText("");
						    saisonComboBox.setSelectedIndex(0); // Réinitialise la sélection de la saison
						    txtIdProprietaire.setSelectedIndex(0); // Réinitialise la sélection du propriétaire
						    
						    PanelReservation.remplirCBXLogement();
						    
						    // Mise à jour du nombre de logements affichés
						    lbNbLogement.setText("Nombre de Logement: " + unTableau.getRowCount());
						}

							
						
					}
					else if (e.getClickCount() == 1) { // Quand on clique une fois cela va remplir les champs pour les modifier
					    // Remplir les champs du formulaire
						
					    numLigne = tableLogement.getSelectedRow();
					    txtAdresse.setText(unTableau.getValueAt(numLigne, 1).toString());
					    txtVille.setText(unTableau.getValueAt(numLigne, 2).toString());
					    txtType.setText(unTableau.getValueAt(numLigne, 3).toString());
					    txtDescription.setText(unTableau.getValueAt(numLigne, 4).toString());
					    txtPrix.setText(unTableau.getValueAt(numLigne, 5).toString());
					    txtCapacite.setText(unTableau.getValueAt(numLigne, 6).toString());
					    txtDateDispo.setText(unTableau.getValueAt(numLigne, 7).toString());
					    txtDateFinDispo.setText(unTableau.getValueAt(numLigne, 8).toString());
					    saisonComboBox.setSelectedItem(unTableau.getValueAt(numLigne, 9).toString());
					    
					    // Extration de l'id du proprietaire : id-nom-prenom
					    String chaineProprietaire = unTableau.getValueAt(numLigne, 10).toString();
					    String tabProprietaire[] = chaineProprietaire.split("-");
					    int id_proprietaire = Integer.parseInt(tabProprietaire[0]);
					    // Sélection du propriétaire dans la JComboBox
					    txtIdProprietaire.setSelectedItem(id_proprietaire + "-" + unTableau.getValueAt(numLigne, 10).toString());

					    btEnregistrer.setText("Modifier");
					}

				}
			});

		}


		// row data  n'accecpte pas Les arraylist donc on les met dans une matrice 
		public Object [] [] obtenirDonnees (String filtre){
			ArrayList <Logement> lesLogements = Controleur.selectAllLogements(filtre);
			Object [] [] matrice = new Object [lesLogements.size()] [11];
			int i = 0;
			for (Logement unLogement : lesLogements) {
				matrice[i][0] = unLogement.getId_logement();
				matrice[i][1] = unLogement.getAdresse();
				matrice[i][2] = unLogement.getVille();
				matrice[i][3] = unLogement.getType();
				matrice[i][4] = unLogement.getDescription();
				matrice[i][5] = unLogement.getPrix();
				matrice[i][6] = unLogement.getCapacite();
				matrice[i][7] = unLogement.getDate_dispo();
				matrice[i][8] = unLogement.getDatefin_dispo();
				matrice[i][9] = unLogement.getSaison();
				matrice[i][10] = unLogement.getId_utilisateur();
				
				i ++ ; 
			}
			return matrice;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (e.getSource() == this.btAnnuler) {
		        this.txtAdresse.setText("");
		        this.txtVille.setText("");
		        this.txtType.setText("");
		        this.txtDescription.setText("");
		        this.txtPrix.setText("");
		        this.txtCapacite.setText("");
		        this.txtDateDispo.setText("");
		        this.txtDateFinDispo.setText("");
		        this.saisonComboBox.setSelectedIndex(0); // Réinitialise la sélection de la saison
		        this.txtIdProprietaire.setSelectedIndex(0);
		        btEnregistrer.setText("Enregistrer");
		        
		    } else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
		        String adresse = this.txtAdresse.getText();
		        String ville = this.txtVille.getText();
		        String type = this.txtType.getText();
		        String description = this.txtDescription.getText();
		        float prix = Float.parseFloat(this.txtPrix.getText());
		        int capacite = Integer.parseInt(this.txtCapacite.getText());
		        String date_dispo = this.txtDateDispo.getText();
		        String datefin_dispo = this.txtDateFinDispo.getText();
		        String saison = (String) saisonComboBox.getSelectedItem();

		        // Extraction de l'id du proprietaire : id-nom-prenom
		        String chaine = this.txtIdProprietaire.getSelectedItem().toString();
		        String tab[] = chaine.split("-");
		        int id_utilisateur = Integer.parseInt(tab[0]);

		        // Instancier la classe Logement
		        Logement unLogement = new Logement(adresse, ville, type, description, prix, capacite, date_dispo, datefin_dispo, saison, id_utilisateur);
		     // Insertion dans la BDD
		        Controleur.insertLogement(unLogement);

		        // Mise à jour des données affichées dans le tableau
		        Object[][] nouvellesDonnees = obtenirDonnees("");
		        unTableau.setDonnees(nouvellesDonnees);

		        // Nettoyer les champs du formulaire
		        this.txtAdresse.setText("");
		        this.txtVille.setText("");
		        this.txtType.setText("");
		        this.txtDescription.setText("");
		        this.txtPrix.setText("");
		        this.txtCapacite.setText("");
		        this.txtDateDispo.setText("");
		        this.txtDateFinDispo.setText("");
		        this.saisonComboBox.setSelectedIndex(0); // Réinitialise la sélection de la saison
		        this.txtIdProprietaire.setSelectedIndex(0);
				
				//on affiche le nombre de logements 
				
				this.lbNbLogement.setText("Nombre de Logements : "+this.unTableau.getRowCount());
		           
		     JOptionPane.showMessageDialog(this, "Insertion réussie");
		     
		     PanelReservation.remplirCBXLogement();

		    
		    }
		

			else if (e.getSource() == this.btFiltrer){
					String filtre = this.txtFiltre.getText();
					Object matrice [] [] = this.obtenirDonnees(filtre);
					//mise a jour de l'affichage 
							this.unTableau.setDonnees(matrice);

		}else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
		    String adresse = this.txtAdresse.getText();
		    String ville = this.txtVille.getText();
		    String type = this.txtType.getText();
		    String description = this.txtDescription.getText();
		    float prix = Float.parseFloat(this.txtPrix.getText());
		    int capacite = Integer.parseInt(this.txtCapacite.getText());
		    String date_dispo = this.txtDateDispo.getText();
		    String datefin_dispo = this.txtDateFinDispo.getText();
		    String saison = (String) saisonComboBox.getSelectedItem();
		    int id_utilisateur = Integer.parseInt(txtIdProprietaire.getSelectedItem().toString().split("-")[0]);

		    // Récupérer l'ID du logement sélectionné dans la table
		    int numLigne = tableLogement.getSelectedRow();
		    int id_logement = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());

		    // Instancier une classe Logement avec l'ID du logement
		    Logement unLogement = new Logement(id_logement, adresse, ville, type, description, prix, capacite, date_dispo, datefin_dispo, saison, id_utilisateur);

		    // Mettr à jour dans la BDD
		    Controleur.updateLogement(unLogement);

		    // Actualiser le tableau d'affichage 
		    Object ligne[] = {unLogement.getId_logement(), adresse, ville, type, description, prix, capacite, date_dispo, datefin_dispo, saison, id_utilisateur};
		    unTableau.modifierLigne(numLigne, ligne);
		    JOptionPane.showMessageDialog(this, "Modification effectuée");

		    // Réinitialiser les champs
		    this.txtAdresse.setText("");
		    this.txtVille.setText("");
		    this.txtType.setText("");
		    this.txtDescription.setText("");
		    this.txtPrix.setText("");
		    this.txtCapacite.setText("");
		    this.txtDateDispo.setText("");
		    this.txtDateFinDispo.setText("");
		    this.saisonComboBox.setSelectedIndex(0);
		    this.txtIdProprietaire.setSelectedIndex(0);
		    this.btEnregistrer.setText("Enregistrer"); // Remettre le texte par défaut au bouton
		    PanelReservation.remplirCBXLogement();
		}
		}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	public static void  remplirCBXProprietaire() {
		ArrayList<Proprietaire> lesProprietaire = Controleur.selectAllProprietaires("");
	
		//vider le Combox des classes
		txtIdProprietaire.removeAllItems();
	
		// on parcours les classes et on insère les proprietaire : id-nom-prenom
		for (Proprietaire unProprietaire : lesProprietaire) {
			txtIdProprietaire.addItem(unProprietaire.getId_utilisateur()+"-"+unProprietaire.getNom()+"-"+unProprietaire.getPrenom());
		}
	
	}	
}



