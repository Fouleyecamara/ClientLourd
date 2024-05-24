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
	import controleur.Tableau;
	import controleur.Gestionnaire;
	
	public class PanelClient extends PanelPrincipal implements ActionListener 
	// ActionListener est une interface pour écouter et réagir aux actions de l'utilisateur, comme les clics sur un bouton.
	{
		
		
		private JPanel panelForm = new JPanel(); //panel formualaire
		private JButton btAnnuler = new JButton("Annuler");
		private JButton btEnregistrer = new JButton("Enregistrer");
		private JComboBox<String> civiliteComboBox = new JComboBox<>(new String[]{"Madame", "Monsieur"});
	
		private JTextField txtNom = new JTextField();
		private JTextField txtPrenom = new JTextField();
		
		private JTextField txtEmail = new JTextField();
		private JTextField txtTelephone = new JTextField();
		private JTextField txtMdp = new JTextField();
		private JTextField txtRoles = new JTextField();
		
		
		private JPanel panelListe = new JPanel(); //permet d'afficher ce qu'on a mis dans le formualaire
		private JTable tableClient;
		private JScrollPane uneScroll;
		
		private static Tableau unTableau ; 
		private JPanel panelFiltre = new JPanel();
		private JTextField txtFiltre = new JTextField();
		private JButton btFiltrer = new JButton("Filtrer");
		
		// Constructeur de PanelClient qui configure l'interface utilisateur pour la gestion des clients.
		private JLabel lbNbClient = new JLabel ("Nombre de clients : ");
		
		public PanelClient() {
			super("Gestion des clients");// Appel du constructeur de PanelPrincipal avec titre spécifié.
	
		    // Définir les propriétés et ajouter le label pour afficher le nombre total de clients.
			this.lbNbClient.setBounds(400 , 360 , 300 , 20);
			this.add(this.lbNbClient);
			
			//construction du panel form : modification du profil
			this.panelForm.setBounds(20,80,300,250);
			this.panelForm.setBackground(Color.white);
			this.panelForm.setLayout(new GridLayout (8,2));
			
		    // Ajouter les composants du formulaire au panelForm. Chaque add correspond à une rangée dans la grille.
			this.panelForm.add(new JLabel("civilite : "));// Ajoute une étiquette pour la civilité
			this.panelForm.add(civiliteComboBox);// Ajoute combobox qui contient "Madame" et "Monsieur"
	
			
			this.panelForm.add(new JLabel("Nom : "));
			this.panelForm.add(this.txtNom);
			
			this.panelForm.add(new JLabel("Prenom : "));
			this.panelForm.add(this.txtPrenom);
			
			this.panelForm.add(new JLabel("Email : "));
			this.panelForm.add(this.txtEmail);
			
			this.panelForm.add(new JLabel("Téléphone : "));
			this.panelForm.add(this.txtTelephone);
			
			
			
			this.panelForm.add(new JLabel("Mot de passe : "));
			this.panelForm.add(this.txtMdp);
			
			this.panelForm.add(new JLabel("Role : "));
			this.panelForm.add(this.txtRoles);
			
			this.panelForm.add(this.btAnnuler);
			this.panelForm.add(this.btEnregistrer);
			
			this.add(this.panelForm);
			
		    // Configuration du panelList qui affiche les clients existants dans un tableau.
	 		//les entetes du tableau
			this.panelListe.setBounds(370,130,500,200);
			this.panelListe.setBackground(new Color(255, 160, 122 ));
			this.panelListe.setLayout(null);
			
		    // Configuration du tableau de clients avec les entêtes appropriées
			String entetes[] = {"ID Client" , "Civilite","Nom" , "Prenom" , "Email" , "Telephone" , "Mdp" , "Roles"};
			this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
			this.tableClient = new JTable (this.unTableau);
			
			this.uneScroll = new JScrollPane(this.tableClient);
			this.uneScroll.setBounds(0,0,460,200);
			this.panelListe.add(this.uneScroll);
			this.add(this.panelListe);
			
			
			
			//construction du Panel filtre
			this.panelFiltre.setBackground(new Color(255, 160, 122 ));
			this.panelFiltre.setBounds(370 , 90 , 460  , 30);
			this.panelFiltre.setLayout(new GridLayout(1 ,3));
			
			this.panelFiltre.add(new JLabel ("Filtrer les client: "));
			this.panelFiltre.add(this.txtFiltre);
			this.panelFiltre.add(this.btFiltrer);
			this.add(this.panelFiltre);
			
			//rendre les bouton ecoutable 
			this.btAnnuler.addActionListener(this);
			this.btEnregistrer.addActionListener(this);
			this.btFiltrer.addActionListener(this);
			
		    // Empêcher le réarrangement des colonnes du tableau.
	 		this.tableClient.getTableHeader().setReorderingAllowed(false);
			
			//on affiche le nombre de classe 
			this.lbNbClient.setText("Nombre de client: "+this.unTableau.getRowCount());
			
		    // Ajouter l'écouteur d'événements pour les clics de souris sur le tableau.
			this.tableClient.addMouseListener(new MouseListener() {
				
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
					int id_utilisateur = 0;
					if (e.getClickCount()>=2) { //quand on clique deux fois cela va afficher le bouton pour le supprimer
						numLigne = tableClient.getSelectedRow();
						id_utilisateur = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le client ?", "Suppression du client" , JOptionPane.YES_NO_OPTION);
						if (reponse == 0) {
							//suppression dans la BDD
							Controleur.deleteClient(id_utilisateur);
							//actualisation de l'affichage 
							unTableau.supprimerLigne(numLigne);
							
							civiliteComboBox.setSelectedIndex(0); // Sélectionner le premier élément de la combobox.
	
							txtNom.setText("");
							txtPrenom.setText("");
							
							txtEmail.setText("");
							txtTelephone.setText("");
							txtMdp.setText("");
							txtRoles.setText("");
							
							btEnregistrer.setText("Enregistrer");
							
							PanelReservation.remplirCBXClient();
							
							//on affiche le nombre de client 
							lbNbClient.setText("Nombre de client : "+unTableau.getRowCount());
							
						}
					}else if (e.getClickCount()==1) { //quand on clique une fois cela va remplir les trois case pour les modifier 
					    // Remplir le formulaire avec les données d'un client lors d'un simple clic sur une ligne du tableau.
	
						numLigne = tableClient.getSelectedRow();
						civiliteComboBox.setSelectedItem(unTableau.getValueAt(numLigne, 1).toString());
						txtNom.setText(unTableau.getValueAt(numLigne, 2).toString());
						txtPrenom.setText(unTableau.getValueAt(numLigne, 3).toString());
						
						txtEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
						txtTelephone.setText(unTableau.getValueAt(numLigne, 5).toString());
						txtMdp.setText(unTableau.getValueAt(numLigne, 6).toString());
						txtRoles.setText(unTableau.getValueAt(numLigne, 7).toString());
						
						btEnregistrer.setText("Modifier");
					}
				}
			});
	
		}
		public static void actualiserTableClient() {
			Object matrice [][]= obtenirDonnees("");
			unTableau.setDonnees(matrice);
		}
	
	
		// row data  n'accecpte pas Les arraylist donc on les met dans une matrice 
		public static Object [] [] obtenirDonnees (String filtre){
			ArrayList <Client> lesClients = Controleur.selectAllClients(filtre);
			Object [] [] matrice = new Object [lesClients.size()] [8];
			int i = 0;  // Compteur pour parcourir la liste des clients.
			for (Client unClient : lesClients) {
				matrice[i][0] = unClient.getId_utilisateur();
				matrice[i][1] = unClient.getCivilite();
				matrice[i][2] = unClient.getNom();
				matrice[i][3] = unClient.getPrenom();
				matrice[i][4] = unClient.getEmail();
				matrice[i][5] = unClient.getTelephone();
				matrice[i][6] = unClient.getMdp();
				matrice[i][7] = unClient.getRoles();
				
				i ++ ; // Incrémente le compteur pour passer au client suivant.
			}
			return matrice; // Retourne la matrice remplie.
		}
		
		
	
		@Override
		public void actionPerformed(ActionEvent e) { // Réagit aux actions de l'utilisateur sur les éléments d'interface.
			// L'opérateur '==' est utilisé pour comparer les références et vérifier 
			//si l'objet déclenchant l'événement (e.getSource()) est le même que le bouton 'Annuler' (this.btAnnuler).
	
			if (e.getSource()== this.btAnnuler) { 
				//réinitialiser tous les champs de formulaire à leurs états
				this.civiliteComboBox.setSelectedIndex(0);
				this.txtNom.setText("");
				this.txtPrenom.setText("");
	
				this.txtEmail.setText("");
				this.txtTelephone.setText("");
				this.txtMdp.setText("");
				this.txtRoles.setText("");
				//
				btEnregistrer.setText("Enregistrer");
			}
			
			// Vérifie si le bouton cliqué est "Enregistrer" et si son texte est "Enregistrer"
			else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){ 
				//&&this.Enregistrer.getText().equals("Enregistrer") veut dire que le bouton il y aura ecrit enregistrer 
				//recuperer les champs
				String civilite = (String)this.civiliteComboBox.getSelectedItem();
				String nom = this.txtNom.getText();
				String prenom = this.txtPrenom.getText();
				String email = this.txtEmail.getText();
				String telephone = this.txtTelephone.getText();
				String mdp = this.txtMdp.getText();
				String roles = this.txtRoles.getText();
				
				
				
				//on instancie une classe 
				// Crée une nouvelle instance de la classe Client avec les informations saisies par l'utilisateur pour l'ajouter en bdd
				Client unClient = new Client( civilite, nom , prenom , email , telephone , mdp , roles);
				
				//on insère dans la BDD
				Controleur.insertClient (unClient);
				
				//recuperer la nvll classe avec son ID 
				unClient = Controleur.selectWhereClient(civilite, nom ,prenom, email , telephone , mdp , roles);
				
	
				//on vide les champs
				this.civiliteComboBox.setSelectedIndex(0);
				this.txtNom.setText("");
				this.txtPrenom.setText("");
				this.txtEmail.setText("");
				this.txtTelephone.setText("");
				this.txtMdp.setText("");
				this.txtRoles.setText("");
				
				
				//on met à jour l'affichage
				Object ligne [] = {unClient.getId_utilisateur(),civilite ,nom,prenom  , email , telephone , mdp , roles };
				this.unTableau.ajouterLigne(ligne);
				
				//on affiche le nombre de client
				
				this.lbNbClient.setText("Nombre de client: "+this.unTableau.getRowCount());
				
				JOptionPane.showMessageDialog(this,"Insertion reussite" );
				
				PanelReservation.remplirCBXClient();
				
			}
			else if (e.getSource() == this.btFiltrer){
					String filtre = this.txtFiltre.getText();
					Object matrice [] [] = this.obtenirDonnees(filtre);
					//mise a jour de l'affichage 
							this.unTableau.setDonnees(matrice);
	
		}
			else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier"))
		{ 	
				String civilite = (String) this.civiliteComboBox.getSelectedItem();
				String nom = this.txtNom.getText();
				String prenom = this.txtPrenom.getText();
				
				String email = this.txtEmail.getText();
				String telephone = this.txtTelephone.getText();
				String mdp = this.txtMdp.getText();
				String roles = this.txtRoles.getText();
			
			//on recupere l'id du client  
			int numLigne = tableClient.getSelectedRow();
			//Integer.parseInt() convertit un texte (String) en un nombre entier (int).
			int id_utilisateur = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			
			//on instancie une classe 
			Client unClient = new Client(civilite, nom , prenom,  email , telephone , mdp , roles);
			//on insère dans la BDD
			Controleur.updateClient(unClient);
		
			
			//actualiser le tableau d'affichage 
			Object ligne [] = {unClient.getId_utilisateur(),civilite, nom , prenom , email , telephone , mdp , roles };
			unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectuée");
			
			//on vide les champs
			this.civiliteComboBox.setSelectedIndex(0);
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtTelephone.setText("");
			this.txtMdp.setText("");
			this.txtRoles.setText("");
			this.btEnregistrer.setText("Enregistrer"); //apres le bouton redeviens enregistrer 
			PanelReservation.remplirCBXClient();
		}
	
	
	} 
		}
	
