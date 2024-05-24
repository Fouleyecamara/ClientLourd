package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

 
import controleur.NS;
import controleur.Gestionnaire;

public class VueGenerale extends JFrame implements ActionListener {
	
	private JPanel panelMenu =new JPanel();
	private JButton btProfil = new JButton("Profil");
	private JButton btClient= new JButton("Client");
	private JButton btProprietaire= new JButton("Proprietaire");
	private JButton btLogement= new JButton("Logement");
	private JButton btReservation= new JButton("Réservation");
	
	
	
	private JButton btQuitter = new JButton("Quitter");
	
	//Instanciation des panels
	
	private PanelProfil unPanelProfil;
	
	private PanelClient unPanelClient=new PanelClient();
	private PanelProprietaire unPanelProprietaire=new PanelProprietaire();
	private PanelLogement unPanelLogement=new PanelLogement();
	private PanelReservation unPanelReservation=new PanelReservation();
	
	
	
	
	public VueGenerale(Gestionnaire unGestionnaire) {
		unPanelProfil = new PanelProfil(unGestionnaire);
		this.setTitle("Neige et Soleil");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(255, 160, 122 ));
		this.setBounds(100,100,900,500);
		
		// construction du panel menu
		this.panelMenu.setBounds(100,10,700,30);
		this.panelMenu.setBackground(new Color(255, 160, 122 ));
		this.panelMenu.setLayout(new GridLayout(1,7));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btClient);
		this.panelMenu.add(this.btProprietaire);
		this.panelMenu.add(this.btLogement);
		this.panelMenu.add(this.btReservation);
		
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		this.setVisible(true);
		
		//insertion des panels dans la fenetre 
		
		this.add(unPanelProfil); 
		this.add(unPanelClient);
		this.add(unPanelProprietaire);
		this.add(unPanelLogement);
		this.add(unPanelReservation);
		
		
		//rendre les boutons ecoutables
		
		
		this.btProfil.addActionListener(this);
		this.btClient.addActionListener(this);
		this.btProprietaire.addActionListener(this);
		this.btLogement.addActionListener(this);
		this.btReservation.addActionListener(this);
		
		this.btQuitter.addActionListener(this);
		
		this.setVisible(true);
	}
		
		public void afficherPanel(int choix) {
			
			this.unPanelProfil.setVisible(false);
			this.unPanelClient.setVisible(false);
			this.unPanelProprietaire.setVisible(false);
			this.unPanelLogement.setVisible(false);
			this.unPanelReservation.setVisible(false);

			switch (choix) {
			case 1 : this.unPanelProfil.setVisible(true);break;
			case 2 : this.unPanelClient.setVisible(true);break;
			case 3 : this.unPanelProprietaire.setVisible(true);break;
			case 4 : this.unPanelLogement.setVisible(true);break;
			case 5 : this.unPanelReservation.setVisible(true);break;
		
			}
		}
		
		
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==this.btQuitter) {
			NS.rendreVisibleVueGenerale(false, null);
			NS.rendreVisibleVueConnexion(true);
		}
		else if (e.getSource()==this.btProfil) {
			this.afficherPanel(1);
		}
		else if (e.getSource()==this.btClient) {
			this.afficherPanel(2);
		}
		else if (e.getSource()==this.btProprietaire) {
			this.afficherPanel(3);
			
		}else if (e.getSource()==this.btLogement) {
			this.afficherPanel(4);
		}else if (e.getSource()==this.btReservation) {
			this.afficherPanel(5);
		}
		
		
		
		
		
		
		
	}

}
