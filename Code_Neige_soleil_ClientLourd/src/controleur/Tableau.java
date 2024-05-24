package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel{
	private Object  donnees [][];
	private String entetes [];
	public Tableau(Object[][] donnees, String[] entetes) {
		super();
		this.donnees = donnees;
		this.entetes = entetes;
	}
	@Override
	public int getRowCount() {
		
		return this.donnees.length ;
	}
	@Override
	public int getColumnCount() {
		
		return this.entetes.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return this.donnees[rowIndex][columnIndex];
	}
	@Override
	public String getColumnName(int column) {
		return(String) this.entetes[column];
	}
	public void ajouterLigne (Object ligne[]) {
		Object matrice[][] = new Object[this.getRowCount() +1][this.getColumnCount()];
		int i =0;
		for(i=0; i< this.getRowCount(); i++) {
			matrice [i] = this.donnees[i];
			
		}
		matrice [this.getRowCount()] = ligne;
		this.donnees = matrice ;
		this.fireTableDataChanged();
		
	}
public void supprimerLigne (int  numLigne) {
	Object matrice [][]= new Object[this.getRowCount() -1][this.getColumnCount()];
	int i =0;
	int j =0;
	for(i=0; i< this.getRowCount(); i++) {
		if (i != numLigne)  {
			matrice [j] =this.donnees[i];
			j++;
		}
	}
	
	this.donnees = matrice ;
	this.fireTableDataChanged();
	}
public void modifierLigne (int numLigne, Object ligne[]) {
	Object matrice[][] = new Object[this.getRowCount()][this.getColumnCount()];
	int i =0;
	int j =0; 
	for(i=0; i< this.getRowCount(); i++) {
		if (i != numLigne)  {
			matrice [j] =this.donnees[i];
			j++;
		}else {
			matrice [j]= ligne;
			j++;
		}
		
		
	}
	
	this.donnees = matrice ;
	this.fireTableDataChanged();
}
public void setDonnees (Object matrice [][]) {
	this.donnees = matrice ;
	this.fireTableDataChanged(); //actualisation de l'affichage
}
	
	

}
