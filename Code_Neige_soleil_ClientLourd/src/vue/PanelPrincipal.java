package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel{
	
	public PanelPrincipal(String message) {
		this.setBackground(new Color(255, 160, 122 ));
		this.setLayout(null);
		this.setBounds(20,40,1000,400);
		JLabel unTitre = new JLabel(message);
		unTitre.setBounds(300,20,200,20);
		this.add(unTitre);
		
		this.setVisible(false);
	}

	public void actionPerformed1(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	

}
