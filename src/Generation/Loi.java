package Generation;

import java.util.List;

public abstract class Loi {
	
/* ************************* Gestion de la liste tirée  ************************* */
	
	/* Dernière série de nombre aléatoire généré */
	private List<Double> listTiree;
	
	public List<Double> getListTiree() {
		return listTiree;
	}

	public void setListTiree(List<Double> listTiree) {
		this.listTiree = listTiree;
	}
	
	public void clearListTiree() {
		this.listTiree = null;
	}
	
	/* Retourne la plus grande valeur de la liste */
	public double getMax() {
		double max = listTiree.get(0);
		
		for( int i = 1; i < listTiree.size(); i++) {
			if(max < listTiree.get(i)) {
				max = listTiree.get(i);
			}
		}
		return max;
	}
	
	/* Retourne la plus petite valeur de la liste */
	public double getMin() {
		double min = listTiree.get(0);
		
		for( int i = 1; i < listTiree.size(); i++) {
			if(min > listTiree.get(i)) {
				min = listTiree.get(i);
			}
		}
		return min;
	}
	
/* *********************** Methodes  ************************* */
	
	
	
	public void tirage(int nbTirage) {
		//Overrided method
	}
	
	
}
