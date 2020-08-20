package logica;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TFA_DescPrimos {
	
	private boolean esPrimoRecursivo(int num, int divisor) {
		if (num / 2 < divisor)
			return true;
		else {
			if (num % divisor == 0) {
				return false;
			} else
				return esPrimoRecursivo(num, divisor + 1);
		}
	}
	
	public LinkedList<Integer> generadorDeNumerosPrimosHastaN(int n) {
		LinkedList<Integer> listaPrimos = new LinkedList<>(); 
		int cont=1;

		while (cont<n) {
			cont++;
			if (esPrimoRecursivo(cont,2))
				listaPrimos.add(cont);
		}
		
		return listaPrimos;
	}

	
	private HashMap<Integer,Integer> descomponerEnPrimosTFA(int nADescomponer, LinkedList<Integer> numerosPrimos) {
		//valor,cantidad
		HashMap<Integer,Integer> descomposicion = new HashMap<Integer,Integer>();
		int nActual = nADescomponer;

		for (int i = 0; i < numerosPrimos.size();) {

			while (nActual % numerosPrimos.get(i) == 0) {
				if (!descomposicion.containsKey(numerosPrimos.get(i)))
					descomposicion.put(numerosPrimos.get(i), 1);
				else {
					int auxCant=descomposicion.get(numerosPrimos.get(i));
					descomposicion.put(numerosPrimos.get(i), auxCant+1);
				}
				nActual = nActual / numerosPrimos.get(i);
			}i++;
		}
		return descomposicion;
	}
	
	public String mostrarTFA(int N,LinkedList<Integer> listprimos) {
		String ret=N+" = ";
		
		for (Map.Entry<Integer, Integer> entry : descomponerEnPrimosTFA(N,listprimos).entrySet()) 
			ret=ret+ entry.getKey() +"^"+entry.getValue()+" x ";

		return (ret.substring(0, ret.length() -2));
	}


}
