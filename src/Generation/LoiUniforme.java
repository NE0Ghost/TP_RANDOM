package Generation;

import java.util.ArrayList;
import java.util.List;

public class LoiUniforme extends Loi {
	
	
	/**
	 * 
	 * @param min valeur minimale de l'intervalle
	 * @param max valeur maximale de l'intervalle
	 */
	private double min;
	private double max;
	
	public LoiUniforme (double min, double max) {
		if(max<min) {
			double temp = max;
			max = min;
			min = temp;
		}
		this.min = min;
		this.max = max;
	}
	
	public LoiUniforme() {
		this.min = 0;
		this.max = 1;
	}
	
	/**
	 * 
	 * @param nbTirage
	 * @return list des tirages
	 */
	public List<Double> tirage(int nbTirage) {
		
		List<Double> list = new ArrayList<Double>(); //liste que l'on retournera
		double sortie = 0;
		
		for(int i = 0; i<nbTirage; i++) {
			
			sortie = Math.random();
			sortie = sortie*(max - min);
			sortie = sortie + min;
			
			list.add(sortie);
		}
		return list;
	}
	
}
