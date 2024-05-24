package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import controleur.Controleur;
import controleur.Gestionnaire;

public class PanelProfil extends PanelPrincipal implements ActionListener
{
	private Gestionnaire unGestionnaire;
	private JTextArea txtInfos = new JTextArea ();
	private JButton btModifier = new JButton("Modifier");
	
	private JPanel panelForm = new JPanel ();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtNom = new JTextField();
	
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JTextField txtRoles = new JTextField();
	
	
	public PanelProfil(Gestionnaire unGestionnaire ) {
		super("Gestion du profil");
		this.unGestionnaire = unGestionnaire;
		//construction du panel form : modification du profil
		this.panelForm.setBounds(350,80,300,250);
		this.panelForm.setBackground(new Color(255, 160, 122 ));
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
		//construction de TextArea
		this.txtInfos.setBounds(20,80 , 250 , 250);
		this.add(this.txtInfos);
		this.txtInfos.setBackground(new Color(229, 188, 201 ));
		this.txtInfos.setText(
				"________INFOS PROFILS_______\n"
				+"\n\n Nom     : "+this.unGestionnaire.getNom()
				+"\n\n Prenom    : "+this.unGestionnaire.getPrenom()
				+"\n\n Email     : "+this.unGestionnaire.getEmail()
				+"\n\n Téléphone     : "+this.unGestionnaire.getTelephone()
				+"\n\n Role     : "+this.unGestionnaire.getRoles()
				);
		this.btModifier.setBounds(80,360,100,30);
		this.add(this.btModifier);
		
		//rendre le formualire non visible 
		this.panelForm.setVisible(false);
	//rendre les voutons ecoutable
			this.btModifier.addActionListener(this);
			this.btAnnuler.addActionListener(this);
			this.btEnregistrer.addActionListener(this);

	}
	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getSource()== this.btModifier) {
			this.panelForm.setVisible(true);
			this.txtNom.setText(unGestionnaire.getNom());
			this.txtPrenom.setText(unGestionnaire.getPrenom());
			this.txtEmail.setText(unGestionnaire.getEmail());
			this.txtTelephone.setText(unGestionnaire.getTelephone());
			
			
			this.txtMdp.setText(unGestionnaire.getMdp());
			
			if (unGestionnaire.getRoles().equals("admin")) {
				this.txtRoles.setEditable(false);
			}
			this.txtRoles.setText(unGestionnaire.getRoles());
			
		}else if (e.getSource() == this.btAnnuler) {
			this.panelForm.setVisible(false);
			
		}else if (e.getSource()== this.btEnregistrer) {
			//on enregistre la modif dans la BDD à la semaine prochaine.
			String nom =this.txtNom.getText();
			String prenom =this.txtPrenom.getText();
			String email =this.txtEmail.getText();
			String telephone = this.txtTelephone.getText();
			String mdp =new String (this.txtMdp.getPassword());
			String role =this.txtRoles.getText();
			
			//instencier un Gestionnaire
			
			this.unGestionnaire = new Gestionnaire(unGestionnaire.getId_utilisateur(), nom , prenom , email , telephone , mdp, role);
			
			//réaliser la modificiation dans la BDD
			
			Controleur.updateGestionnaire(this.unGestionnaire);
			
			//actualiser l'affichage des données Profil
			this.txtInfos.setText(
					"________INFOS PROFILS_______\n"
					+"\n\n Nom     : "+this.unGestionnaire.getNom()
					+"\n\n Prenom    : "+this.unGestionnaire.getPrenom()
					+"\n\n Email     : "+this.unGestionnaire.getEmail()
					+"\n\n Téléphone     : "+this.unGestionnaire.getTelephone()
					+"\n\n Role     : "+this.unGestionnaire.getRoles()
					);
			JOptionPane.showMessageDialog(this, "Modification réussis des infos Profils");
			
			this.panelForm.setVisible(false);
		}
	}
}

