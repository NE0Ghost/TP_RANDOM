package Generation;

import java.util.ArrayList;
import java.util.List;

public class LoiPoisson {

	private double lambda;
	
	public LoiPoisson(double lambda) {
		this.setLambda(lambda);
	}
	
	public LoiPoisson() {
		setLambda(1);
	}
	
	
	/**
	 * 
	 * @param nbTirage
	 * @return list des tirages
	 */
	public List<Double> tirage(int nbTirage) {
		
		List<Double> list = new ArrayList<Double>(); //liste que l'on retournera
		
		double L = Math.exp(-lambda);
		double cnt; //returned value
		double p;
		
		//Tirage
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
		return list;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
	
}
