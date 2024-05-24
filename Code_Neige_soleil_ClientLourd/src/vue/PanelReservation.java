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
import controleur.Reservation;
import controleur.Tableau;

public class PanelReservation extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel(); // panel formulaire
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtDateDeb = new JTextField();
    private JTextField txtDateFin = new JTextField();
    private JComboBox<String> statutComboBox = new JComboBox<>(new String[]{"Reservé"});
    private JTextField txtPrixTotal = new JTextField();

    private static JComboBox<String> txtIdLogement = new JComboBox<String>();
    private static JComboBox<String> txtIdClient = new JComboBox<String>();

    private JPanel panelListe = new JPanel(); // permet d'afficher ce qu'on a mis dans le formualaire
    private JTable tableReservation;
    private JScrollPane uneScroll;

    private Tableau unTableau;
    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JLabel lbNbReservation = new JLabel("Nombre de reservations : ");

    public PanelReservation() {
        super("Gestion des reservation");
        this.lbNbReservation.setBounds(400, 360, 300, 20);
        this.add(this.lbNbReservation);

        this.panelForm.setBounds(20, 80, 300, 250);
        this.panelForm.setBackground(Color.white);
        this.panelForm.setLayout(new GridLayout(7, 2));

        this.panelForm.add(new JLabel("Date de début : "));
        this.panelForm.add(this.txtDateDeb);

        this.panelForm.add(new JLabel("Date de fin : "));
        this.panelForm.add(this.txtDateFin);

        this.panelForm.add(new JLabel("Statut : "));
        this.panelForm.add(this.statutComboBox);

        this.panelForm.add(new JLabel("Prix Total : "));
        this.panelForm.add(this.txtPrixTotal);

        this.panelForm.add(new JLabel("Logement : "));
        this.panelForm.add(this.txtIdLogement);

        this.panelForm.add(new JLabel("Client : "));
        this.panelForm.add(this.txtIdClient);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);

        this.add(this.panelForm);
        this.remplirCBXLogement();
        this.remplirCBXClient();

        // construire le panelList
        this.panelListe.setBounds(370, 130, 500, 200);
        this.panelListe.setBackground(new Color(255, 160, 122));
        this.panelListe.setLayout(null);
        String entetes[] = {"ID Reservation",
            "Date de début", "Date de fin", "Statut", "Prix", "ID Logement",
            "Client "};

        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.tableReservation = new JTable(this.unTableau);

        this.uneScroll = new JScrollPane(this.tableReservation);
        this.uneScroll.setBounds(0, 0, 460, 200);
        this.panelListe.add(this.uneScroll);
        this.add(this.panelListe);

        // construction du Panel filtre
        this.panelFiltre.setBackground(new Color(255, 160, 122));
        this.panelFiltre.setBounds(370, 90, 460, 30);
        this.panelFiltre.setLayout(new GridLayout(1, 3));

        this.panelFiltre.add(new JLabel("Filtrer les Reservations : "));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(this.btFiltrer);
        this.add(this.panelFiltre);

        // rendre les bouton ecoutable
        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
        this.btFiltrer.addActionListener(this);

        // rendre la table non editable
        this.tableReservation.getTableHeader().setReorderingAllowed(false);

        // on affiche le nombre de logement
        this.lbNbReservation.setText("Nombre de Reservation: " + this.unTableau.getRowCount());

        // mise en place de MouseListener sur la table
        this.tableReservation.addMouseListener(new MouseListener() {

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
                int id_reservation = 0;
                if (e.getClickCount() >= 2) { // quand on clique deux fois cela va afficher le bouton pour le supprimer
                    numLigne = tableReservation.getSelectedRow();
                    id_reservation = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                    int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette réservation  ?", "Suppression de la réservation", JOptionPane.YES_NO_OPTION);
                    if (reponse == 0) {
                        // Suppression dans la BDD
                        Controleur.deleteReservation(id_reservation);

                        // Actualisation de l'affichage
                        unTableau.supprimerLigne(numLigne);
                        txtDateDeb.setText("");
                        txtDateFin.setText("");
                        statutComboBox.setSelectedIndex(0);
                        txtPrixTotal.setText("");
                        txtIdLogement.setSelectedIndex(0);
                        txtIdClient.setSelectedIndex(0);

                        // Mise à jour du nombre de reservations affichés
                        lbNbReservation.setText("Nombre de Réservations : " + unTableau.getRowCount());
                    }
                } else if (e.getClickCount() == 1) { // Quand on clique une fois cela va remplir les champs pour les modifier
                    // Remplir les champs du formulaire

                	numLigne = tableReservation.getSelectedRow();
                    txtDateDeb.setText(unTableau.getValueAt(numLigne, 1).toString());
                    txtDateFin.setText(unTableau.getValueAt(numLigne, 2).toString());
                    statutComboBox.setSelectedItem(unTableau.getValueAt(numLigne, 3).toString());
                    txtPrixTotal.setText(unTableau.getValueAt(numLigne, 4).toString());

                    // Extration de l'id du client : id-nom-prenom et du logement
                    String chaineLogement = unTableau.getValueAt(numLigne, 5).toString();
                    String tabLogement[] = chaineLogement.split("-");
                    int id_logement = Integer.parseInt(tabLogement[0]);

                    
                    txtIdLogement.setSelectedItem(id_logement + "-" + unTableau.getValueAt(numLigne, 5).toString());
                    
                    
                    String chaineClient = unTableau.getValueAt(numLigne, 6).toString();
                    String tabClient[] = chaineClient.split("-");
                    int id_client = Integer.parseInt(tabClient[0]);
                    
                    // Sélection du client dans la JComboBox
                    txtIdClient.setSelectedItem(id_client + "-" + unTableau.getValueAt(numLigne, 6).toString());
                    btEnregistrer.setText("Modifier");
                      // Par exemple, effacer les champs ou afficher un message d'erreur
                    }
                }

            
        });
        

    }

    // row data  n'accecpte pas Les arraylist donc on les met dans une matrice
    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Reservation> lesReservations = Controleur.selectAllReservations(filtre);
        Object[][] matrice = new Object[lesReservations.size()][7];
        int i = 0;
        for (Reservation uneReservation : lesReservations) {
            matrice[i][0] = uneReservation.getId_reservation();
            matrice[i][1] = uneReservation.getDate_debut();
            matrice[i][2] = uneReservation.getDate_fin();
            matrice[i][3] = uneReservation.getStatut();
            matrice[i][4] = uneReservation.getPrix_total();
            matrice[i][5] = uneReservation.getId_logement();
            matrice[i][6] = uneReservation.getId_utilisateur();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtDateDeb.setText("");
            this.txtDateFin.setText("");
            this.statutComboBox.setSelectedIndex(0);
            this.txtPrixTotal.setText("");
            this.txtIdLogement.setSelectedIndex(0);
            this.txtIdClient.setSelectedIndex(0);

            btEnregistrer.setText("Enregistrer");

        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String date_debut = this.txtDateDeb.getText();
            String date_fin = this.txtDateFin.getText();
            String statut = (String)this.statutComboBox.getSelectedItem();
            String prixText = this.txtPrixTotal.getText();
            float prix_total = 0.0f; // Valeur par défaut
			if (!prixText.isEmpty()) {
			    try {
			        prix_total = Float.parseFloat(prixText);
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide.");
			        return; // Ne pas continuer si une valeur non valide est saisie
			    }
			}
            
            
            String chaine = this.txtIdLogement.getSelectedItem().toString();
	        String tab[] = chaine.split("-");
            int id_logement = Integer.parseInt(tab[0]);
            
            String chaine2 = this.txtIdClient.getSelectedItem().toString();
	        String tab2[] = chaine2.split("-");
            int id_client = Integer.parseInt(tab2[0]);

            // Instancier la classe RESERVATION
            Reservation uneReservation = new Reservation(date_debut, date_fin, statut, prix_total, id_logement, id_client);

            // Insertion dans la BDD
            Controleur.insertReservation(uneReservation);

            // Mise à jour des données affichées dans le tableau
            Object[][] nouvellesDonnees = obtenirDonnees("");
            unTableau.setDonnees(nouvellesDonnees);

            // Nettoyer les champs du formulaire
            this.txtDateDeb.setText("");
            this.txtDateFin.setText(""); 
            this.statutComboBox.setSelectedIndex(0);
            this.txtPrixTotal.setText("");
            this.txtIdLogement.setSelectedIndex(0);
            this.txtIdClient.setSelectedIndex(0);

            // on affiche le nombre de logements 

            this.lbNbReservation.setText("Nombre de Reservations : " + this.unTableau.getRowCount());

            JOptionPane.showMessageDialog(this, "Insertion réussie");

        } else if (e.getSource() == this.btFiltrer) {
            String filtre = this.txtFiltre.getText();
            Object matrice[][] = this.obtenirDonnees(filtre);
            // mise a jour de l'affichage 
            this.unTableau.setDonnees(matrice);

        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String date_debut = this.txtDateDeb.getText();
            String date_fin = this.txtDateFin.getText();
            String statut = (String) this.statutComboBox.getSelectedItem();
            float prix_total = Float.parseFloat(this.txtPrixTotal.getText());
            int id_logement = Integer.parseInt(txtIdLogement.getSelectedItem().toString().split("-")[0]);
            int id_utilisateur = Integer.parseInt(txtIdClient.getSelectedItem().toString().split("-")[0]);

            // Récupérer l'ID de la reservation sélectionné dans la table
            
            
            int numLigne = tableReservation.getSelectedRow();
            int id_reservation = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());

            // Instancier une classe Logement avec l'ID du logement
            Reservation uneReservation = new Reservation(date_debut, date_fin, statut, prix_total, id_logement, id_utilisateur);
            // Mettr à jour dans la BDD
            Controleur.updateReservation(uneReservation);

            // Actualiser le tableau d'affichage 
            Object ligne[] = {uneReservation.getId_reservation(), date_debut , date_fin, statut, prix_total, id_logement, id_utilisateur};
            unTableau.modifierLigne(numLigne, ligne);
            JOptionPane.showMessageDialog(this, "Modification effectuée");

            // Réinitialiser les champs

            this.txtDateDeb.setText("");
            this.txtDateFin.setText("");
            this.statutComboBox.setSelectedIndex(0);
            this.txtPrixTotal.setText("");
            this.txtIdLogement.setSelectedIndex(0);
            this.txtIdClient.setSelectedIndex(0);

            this.btEnregistrer.setText("Enregistrer"); // Remettre le texte par défaut au bouton
        }
    }

    public static void remplirCBXLogement() {
        ArrayList<Logement> lesLogements = Controleur.selectAllLogements("");

        // vider le ComboBox des logements
        txtIdLogement.removeAllItems();

        // parcourir les logements et les insérer dans le ComboBox : id-adresse-ville
        for (Logement unLogement : lesLogements) {
            txtIdLogement.addItem(unLogement.getId_logement() + "-" + unLogement.getAdresse() + "-" + unLogement.getVille());
        }
    }

    public static void remplirCBXClient() {
        ArrayList<Client> lesClients = Controleur.selectAllClients("");

        // vider le ComboBox des clients
        txtIdClient.removeAllItems();

        // parcourir les clients et les insérer dans le ComboBox : id-nom-prenom
        for (Client unClient : lesClients) {
            txtIdClient.addItem(unClient.getId_utilisateur() + "-" + unClient.getNom() + "-" + unClient.getPrenom());
        }
    }
}
