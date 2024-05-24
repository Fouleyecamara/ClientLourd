package controleur;

import vue.VueGenerale;
import vue.vueConnexion;

public class NS {
	
	private static vueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	
	public static void rendreVisibleVueConnexion(boolean action) 
	{
		uneVueConnexion.setVisible(action);
	}
	
	public static void rendreVisibleVueGenerale(boolean action, Gestionnaire unGestionnaire) {
		if(action== true) {
			uneVueGenerale=new VueGenerale(unGestionnaire);
			uneVueGenerale.setVisible(true);
		}
		else {
			uneVueGenerale.dispose();
		}
	}
	public static void main(String[] args) {
		
		uneVueConnexion=new vueConnexion();
		
	}
	
	

}
