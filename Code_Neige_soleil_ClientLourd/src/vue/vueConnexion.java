 package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.NS;
import controleur.Gestionnaire;

public class vueConnexion extends JFrame implements ActionListener , KeyListener{
    
    private JPanel panelConnexion = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btSeConnecter = new JButton("Se connecter");
    private JTextField txtEmail;
    private JPasswordField txtMdp;
    
    public vueConnexion() {
        
        this.setTitle("Neige et Soleil");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(255, 160, 122 ));
        this.setBounds(100, 100, 600, 500);
        ImageIcon uneImage = new ImageIcon("src/images/logoo.png");
        
        JLabel unLogo = new JLabel(uneImage);
        unLogo.setBounds(20, 20, 220, 300);
        
        // Construction du panneau de connexion
        this.panelConnexion.setBounds(260, 20, 300, 220);
        this.panelConnexion.setBackground(new Color(255, 160, 122 ));
        this.panelConnexion.setLayout(new GridLayout(3, 2)); // 3 lignes 2 colonnes
        
        this.txtEmail = new JTextField();
        this.txtMdp = new JPasswordField();
        
        this.panelConnexion.add(new JLabel("Email : "));
        this.panelConnexion.add(this.txtEmail);
        this.panelConnexion.add(new JLabel("Mot de passe : "));
        this.panelConnexion.add(this.txtMdp);
        this.panelConnexion.add(this.btAnnuler);
        this.panelConnexion.add(this.btSeConnecter);
        
        this.add(this.panelConnexion);
        this.add(unLogo);
        
        this.setVisible(true);
        
        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btSeConnecter.addActionListener(this);
        
        // Rendre les zones de texte écoutables
        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btAnnuler) {
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        } else if (e.getSource() == this.btSeConnecter) {
            traitement();
        }
    }
    
    public void traitement () {
        String email = this.txtEmail.getText();
        String mdp = new String(this.txtMdp.getPassword());
        
        // On vérifie dans la BDD
        Gestionnaire unGestionnaire = Controleur.selectwhereGestionnaire(email, mdp);
        if (unGestionnaire != null) {
            JOptionPane.showMessageDialog(this, "Bienvenue M./MM " + unGestionnaire.getNom() + "   " + unGestionnaire.getPrenom());
            // On ouvre le logiciel 
            NS.rendreVisibleVueConnexion(false);
            NS.rendreVisibleVueGenerale(true, unGestionnaire);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
            traitement();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
