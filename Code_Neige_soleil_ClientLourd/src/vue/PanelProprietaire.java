package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Proprietaire;
import controleur.Controleur;
import controleur.Tableau;


public class PanelProprietaire extends PanelPrincipal implements ActionListener {
	
	
	private JPanel panelForm = new JPanel(); //panel formualaire
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	
	private JTextField txtEmail = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JTextField txtRoles = new JTextField();
	
	
	private JPanel panelListe = new JPanel(); //permet d'afficher ce qu'on a mis dans le formualaire
	private JTable tableProprietaire;
	private JScrollPane uneScroll;
	
	private Tableau unTableau ; 
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	
	private JLabel lbNbProprietaire  = new JLabel ("Nombre de proprietaires : ");
	
	public PanelProprietaire() {
		super("Gestion des Proprietaires");
		//construire le nb de Proprietaire
		this.lbNbProprietaire.setBounds(400 , 360 , 300 , 20);
		this.add(this.lbNbProprietaire);
		
		//construction du panel form : modification du profil
		this.panelForm.setBounds(20,80,300,250);
		this.panelForm.setBackground(Color.white);
		this.panelForm.setLayout(new GridLayout (7,2));
		
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
		
		//construire le panelList
		this.panelListe.setBounds(370,130,800,200);
		this.panelListe.setLayout(new BorderLayout());
		String entetes[] = {"ID Proprietaire" , "Nom" , "Prenom" , "Email" , "Telephone" , "Mdp" , "Roles"};
		
		this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
		this.tableProprietaire = new JTable (this.unTableau);
		
		this.uneScroll = new JScrollPane(this.tableProprietaire);
		this.uneScroll.setBounds(0,0,460,200);
		 this.panelListe.add(this.uneScroll, BorderLayout.CENTER);
		this.add(this.panelListe);
		
		
		
		//construction du Panel filtre
		this.panelFiltre.setBackground(new Color(255, 160, 122 ));
		this.panelFiltre.setBounds(370 , 90 , 460  , 30);
		this.panelFiltre.setLayout(new GridLayout(1 ,3));
		
		this.panelFiltre.add(new JLabel ("Filtrer les Proprietaire: "));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		
		//rendre les bouton ecoutable 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
		
		//rendre la table non editable 
		this.tableProprietaire.getTableHeader().setReorderingAllowed(false);
		
		//on affiche le nombre de classe 
		this.lbNbProprietaire.setText("Nombre de Proprietaire: "+this.unTableau.getRowCount());
		
		//mise en place de MouseListener sur la table 
		this.tableProprietaire.addMouseListener(new MouseListener() {
			
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
					numLigne = tableProprietaire.getSelectedRow();
					id_utilisateur = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le proprietaire ?", "Suppression de la classe" , JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
						//suppression dans la BDD
						Controleur.deleteProprietaire(id_utilisateur);
						//actualisation de l'affichage 
						unTableau.supprimerLigne(numLigne);
						txtNom.setText("");
						txtPrenom.setText("");
						
						txtEmail.setText("");
						txtTelephone.setText("");
						txtMdp.setText("");
						txtRoles.setText("");
						
						btEnregistrer.setText("Enregistrer");
						PanelLogement.remplirCBXProprietaire();
						
						//on affiche le nombre de classe 
						lbNbProprietaire.setText("Nombre de proprietaire : "+unTableau.getRowCount());
						
					}
				}else if (e.getClickCount()==1) { //quand on clique une fois cela va remplir les trois case pour les modifier 
					//remplir les champs du formulaire 
					numLigne = tableProprietaire.getSelectedRow();
					txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
					
					txtEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtTelephone.setText(unTableau.getValueAt(numLigne, 4).toString());
					txtMdp.setText(unTableau.getValueAt(numLigne, 5).toString());
					txtRoles.setText(unTableau.getValueAt(numLigne, 6).toString());
					
					btEnregistrer.setText("Modifier");
				}
			}
		});

	}


	// row data  n'accecpte pas Les arraylist donc on les met dans une matrice 
	public Object [] [] obtenirDonnees (String filtre){
		ArrayList <Proprietaire> lesProprietaires = Controleur.selectAllProprietaires(filtre);
		Object [] [] matrice = new Object [lesProprietaires.size()] [7];
		int i = 0;
		for (Proprietaire unProprietaire : lesProprietaires) {
			matrice[i][0] = unProprietaire.getId_utilisateur();
			matrice[i][1] = unProprietaire.getNom();
			matrice[i][2] = unProprietaire.getPrenom();
			matrice[i][3] = unProprietaire.getEmail();
			matrice[i][4] = unProprietaire.getTelephone();
			matrice[i][5] = unProprietaire.getMdp();
			matrice[i][6] = unProprietaire.getRoles();
			
			i ++ ; 
		}
		return matrice;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");

			this.txtEmail.setText("");
			this.txtTelephone.setText("");
			this.txtMdp.setText("");
			this.txtRoles.setText("");
			
			btEnregistrer.setText("Enregistrer");
			
		}
		else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")){ 
			//&&this.Enregistrer.getText().equals("Enregistrer") veut dire que le bouton il y aura ecrit enregistrer 
			//recuperer les champs
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String telephone = this.txtTelephone.getText();
			String mdp = this.txtMdp.getText();
			String roles = this.txtRoles.getText();
			
			
			
			//on instancie une classe 
			
			Proprietaire unProprietaire = new Proprietaire( nom , prenom , email , telephone , mdp , roles);
			
			//on insère dans la BDD
			Controleur.insertProprietaire (unProprietaire);
			
			//recuperer la nvll classe avec son ID 
			unProprietaire = Controleur.selectWhereProprietaire( nom ,prenom, email , telephone , mdp , roles);
			

			//on vide les champs
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtTelephone.setText("");
			this.txtMdp.setText("");
			this.txtRoles.setText("");
			
			
			//on met à jour l'affichage
			Object ligne [] = {unProprietaire.getId_utilisateur(),nom,prenom  , email , telephone , mdp , roles };
			this.unTableau.ajouterLigne(ligne);
			
			//on affiche le nombre de Proprietaire
			
			this.lbNbProprietaire.setText("Nombre de Proprietaire: "+this.unTableau.getRowCount());
			
			JOptionPane.showMessageDialog(this,"Insertion reussite" );
			
			PanelLogement.remplirCBXProprietaire();
		}
		else if (e.getSource() == this.btFiltrer){
				String filtre = this.txtFiltre.getText();
				Object matrice [] [] = this.obtenirDonnees(filtre);
				//mise a jour de l'affichage 
						this.unTableau.setDonnees(matrice);

	}
		else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier"))
	{ 
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			
			String email = this.txtEmail.getText();
			String telephone = this.txtTelephone.getText();
			String mdp = this.txtMdp.getText();
			String roles = this.txtRoles.getText();
		
		//on recupere l'id de la classe 
		int numLigne = tableProprietaire.getSelectedRow();
		int id_utilisateur = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		
		//on instancie une classe 
		Proprietaire unProprietaire = new Proprietaire( nom , prenom,  email , telephone , mdp , roles);
		//on insère dans la BDD
		Controleur.updateProprietaire(unProprietaire);
	
		
		//actualiser le tableau d'affichage 
		Object ligne [] = {unProprietaire.getId_utilisateur(),nom , prenom , email , telephone , mdp , roles };
		unTableau.modifierLigne(numLigne, ligne);
		JOptionPane.showMessageDialog(this, "Modification effectuée");
		
		//on vide les champs
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtTelephone.setText("");
		this.txtMdp.setText("");
		this.txtRoles.setText("");
		this.btEnregistrer.setText("Enregistrer");
		PanelLogement.remplirCBXProprietaire();//apres le bouton redeviens enregistrer 
	}


} 
	}


