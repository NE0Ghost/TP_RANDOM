package Generation;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Loi uniforme = new LoiPoisson(50);
		uniforme.tirage(45);
		
		List<Double> list = uniforme.getListTiree();
		
		for(int i = 0; i < list.size(); i++) {
            System.out.println("tirage " + i + ": " + list.get(i));
        }
		System.out.println("Le max est : " + uniforme.getMax());
		System.out.println("Le min est : " + uniforme.getMin());
	}

}
