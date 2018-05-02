package Generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoiExponentielle extends Loi {
	
	private double lambda;
	
	/**
	 * 
	 * @param lambda paramètre de la distribution
	 */
	public LoiExponentielle(double lambda) {
		this.lambda = lambda;
	}
	
	public LoiExponentielle() {
		this.lambda = 1;
	}
	
	/**
	 * 
	 * @param nbTirage
	 * @return liste des tirages
	 */
	public List<Double> tirage(int nbTirage) {
		
		List<Double> list = new ArrayList<Double>(); //liste que l'on retournera
		double sortie = 0;
		Random rdm = new Random();
		
		for(int i = 0; i<nbTirage; i++) {
			sortie = 0;//stub
			
			list.add(sortie);
		}
		return list;
	}
	
	
}
