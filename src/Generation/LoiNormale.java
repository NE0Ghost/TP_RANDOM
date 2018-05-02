package Generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoiNormale extends Loi {
	
/* ************************* Attributs  ************************* */
	private double moyenne;
	private double sigma;
	
/* ************************* Constructeur  ************************* */
	
	public LoiNormale(double moyenne, double sigma) {
		this.moyenne = moyenne;
		this.sigma = sigma;
	}
	
	/* Constructeur par défaut */
	public LoiNormale() {
		this.moyenne = 0;
		this.sigma = 1;
	}
	
/* ************************* Méthodes  ************************* */	
	/* Simulation des tirages
	 * Remplace la liste en attribut de la superclass par le nouveau tirage
	 */
	@Override
	public void tirage(int nbTirage) {
		
		List<Double> list = new ArrayList<Double>(); //liste que l'on retournera
		double sortie = 0;
		Random rdm = new Random();
		
		for(int i = 0; i<nbTirage; i++) {
			
			sortie = rdm.nextGaussian();
			sortie = (sortie*sigma)+moyenne;
			list.add(sortie);
		}
		super.setListTiree(list);
	} // Fin tirage
	
}
