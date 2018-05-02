package Generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoiExponentielle extends Loi {
	
/* ************************* Attributs  ************************* */
	private double lambda;
	
/* ************************* Constructeur  ************************* */
	public LoiExponentielle(double lambda) {
		this.lambda = lambda;
	}
	
	/* Constructeur par défaut */
	public LoiExponentielle() {
		this.lambda = 1;
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
			sortie = -( 1 / lambda ) * Math.log( 1 - rdm.nextDouble() );
			list.add(sortie);
		}
		super.setListTiree(list);
	}
	
	
	/* Getter & Setter du paramètre Lambda */
	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
}
