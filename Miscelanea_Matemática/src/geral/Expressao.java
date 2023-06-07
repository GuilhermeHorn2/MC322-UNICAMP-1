package geral;

import java.util.ArrayList;

public interface Expressao {
	
	public static final String expression = "";
	
	//métodos:
	
	ArrayList<ArrayList<Integer>> gerar_listas();
	
	double calcular_expressao(ArrayList<ArrayList<Integer>> all,double x);
	
	ArrayList<ArrayList<Integer>> derivada(ArrayList<ArrayList<Integer>> all);

}
