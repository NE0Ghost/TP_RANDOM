package Generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoiNormale extends Loi {
	
	private double moyenne;
	private double sigma;
	
	/**
	 * 
	 * @param moyenne
	 * @param sigma
	 */
	public LoiNormale(double moyenne, double sigma) {
		this.moyenne = moyenne;
		this.sigma = sigma;
	}
	
	public LoiNormale() {
		this.moyenne = 0;
		this.sigma = 1;
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
			
			sortie = rdm.nextGaussian();
			sortie = (sortie*sigma)+moyenne;
			list.add(sortie);
		}
		return list;
	}
	
}
