package Generation;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		LoiNormale uniforme = new LoiNormale(15, 4);
		List<Double> list = uniforme.tirage(20);
		
		for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
	}

}
