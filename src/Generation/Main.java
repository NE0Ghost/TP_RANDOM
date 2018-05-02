package Generation;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		LoiPoisson uniforme = new LoiPoisson(10);
		List<Double> list = uniforme.tirage(20);
		
		for(int i = 0; i < list.size(); i++) {
            System.out.println("tirage " + i + ": " + list.get(i));
        }
	}

}
