package Generation;

import java.util.ArrayList;
import java.util.List;

public class LoiUniforme extends Loi {
	
	
/* ************************* Attributs  ************************* */
	private double min;
	private double max;

	
/* ************************* Constructeur  ************************* */
	public LoiUniforme (double min, double max) {
		//verification min < max
		if(max<min) {
			double temp = max;
			max = min;
			min = temp;
		}
		//assignation aux attribut
		this.min = min;
		this.max = max;
	}
	
	/* Constructeur par défaut */
	public LoiUniforme() {
		this.min = 0;
		this.max = 1;
	}
	
/* ************************* Méthodes  ************************* */
	
	/* Simulation des tirages
	 * Remplace la liste en attribut de la superclass par le nouveau tirage
	 */
	@Override
	public void tirage(int nbTirage) {
		
		List<Double> list = new ArrayList<Double>(); //liste que l'on retournera
		double sortie = 0;
		
		for(int i = 0; i<nbTirage; i++) {
			
			sortie = Math.random();
			sortie = sortie*(max - min);
			sortie = sortie + min;
			
			list.add(sortie);
		}
		super.setListTiree(list);
	}
	
}
