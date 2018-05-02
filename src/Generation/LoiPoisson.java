package Generation;

import java.util.ArrayList;
import java.util.List;

public class LoiPoisson extends Loi {

/* ************************* Attributs  ************************* */
	private double lambda;
	
/* ************************* Constructeur  ************************* */
	public LoiPoisson(double lambda) {
		this.setLambda(lambda);
	}
	
	/* Constructeur par défaut */
	public LoiPoisson() {
		setLambda(1);
	}
	
/* ************************* Méthodes  ************************* */
	
	/* Simulation des tirages
	 * Remplace la liste en attribut de la superclass par le nouveau tirage
	 */
	@Override
	public void tirage(int nbTirage) {
		
		/* liste tirée */
		List<Double> list = new ArrayList<Double>();
		
		/* init algo */
		double L = Math.exp(-lambda);
		double cnt; //returned value
		double p;
		
		/* Tirage */
		for( int i = 0 ; i < nbTirage ; i++ ) {
			/* Algorithme Random poisson : Knuth */
			cnt = 0.0;
			p = 1.0;
			do {
				cnt = cnt + 1;
				p = p * Math.random();
			
			}while( p > L );
			
			list.add(cnt-1);
			/* Fin algorithme */
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
