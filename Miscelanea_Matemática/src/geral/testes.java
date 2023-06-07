package geral;

import java.util.ArrayList;
import java.util.Random;


/*Grupo:
 * Al:Guilherme Azevedo Horn;RA:247127
 * Newton
 * Gauss
 * Laplace
 */

public class testes {

	public static void main(String[] args) {
		
		//1)Achar as raizes rais de um polinomio dado: 
		/*
		 * Esse algoritmo consegue achar boa parte das raizes reais de um polinomio dado,algumas podem escapar em casos muito
		 * extremos,pois a procura dessas raizes e feita por um range arbitrario e um numero de iteracoes limite,mas para boa
		 * parte dos casos esse algoritmo consegue realizar essa tarefa.Caso o usuario nao use o formato de entrada adequado
		 * oque deve ocorrer e que o resultado das raizes sera algo nao previsivel,mas nao retornora algum erro que quebre o
		 * programa.Para desenvolver essa funcionalidade,foram desenvolvidas algumas outras como:derivar polinomios de forma
		 * conveniente,calcular expressao de um polinomio dado um parametro...
		 */
		
		
		//Teste:
		System.out.println("Teste Raizes:");
		String s1 = "x^5 - 3x^2 + x";
		Polinomio pol1 = new Polinomio(s1);
		Aproximar_polinomio aprox1 = new Aproximar_polinomio(pol1);
		System.out.println("Raizes Reais de: (" + s1 +") -->"+aprox1.metodo_generalizado());
		
		String s2 = "x^4 - 2x^2";
		Polinomio pol2 = new Polinomio(s2);
		Aproximar_polinomio aprox2 = new Aproximar_polinomio(pol2);
		System.out.println("Raizes Reais de: (" + s2 +") -->"+aprox2.metodo_generalizado());
		
		//2)Matrizes:multiplicar matrizes: MxN por NxK
		
		//Testando:
		System.out.println();
		System.out.println("Teste Multiplicacao");
		int M = 3;
		int N = 3;
		ArrayList<ArrayList<Double>> valor = new ArrayList<>();
		double k = 1;
		for(int i = 0;i < M;i++){
			valor.add(new ArrayList<>());
			for(int j = 0;j < N;j++){
				valor.get(i).add(k);
				k++;
			}
		}
		Matriz A = new Matriz();
		try {
			
			System.out.println("A:");
			A = new Matriz(valor);
			A.print_matriz();
			System.out.println("B:");
			Matriz B = new Matriz(valor);
			B.print_matriz();
			Matriz A_B = A.multiplicar(B);
			System.out.println("AB:");
			A_B.print_matriz();
			
		}
		catch(TamanhoDistintoException tde) {
			System.out.println("Entrada Invalida.");
		}
		
		
		//3)Matrizes:inverter matriz --> Algoritmo do Gauss
		
		//*********To Do:Checar se o determiante de matriz a ser invertidade e 0 ou nao*******//
		
		//Teste:
		System.out.println();
		System.out.println("Teste Inversa");
		valor = new ArrayList<>();
		
		for(int i = 0;i < 3;i++){
			valor.add(new ArrayList<>());
		}
		valor.get(0).add(-3.0);
		valor.get(0).add(2.0);
		valor.get(0).add(-1.0);
		
		valor.get(1).add(6.0);
		valor.get(1).add(-6.0);
		valor.get(1).add(7.0);
		
		valor.get(2).add(3.0);
		valor.get(2).add(-4.0);
		valor.get(2).add(4.0);
		
		A = new Matriz();
		try {
			
			A = new Matriz(valor);
			
		}
		catch(TamanhoDistintoException tde) {
			System.out.println("Entrada Invalida.");
		}
		System.out.println("A:");
		A.print_matriz();
		System.out.println("Inversa:");
		Matriz B = A.calcular_inversa();
		if(B != null) {
			B.print_matriz();
		}

		
		//4)Matrizes:determinante de uma matriz NxN --> Algoritmo do Laplace
		
		//Testando:
		System.out.println();
		System.out.println("Teste Dterminante");
				valor = new ArrayList<>();
				k = 1;
				for(int i = 0;i < M;i++){
					valor.add(new ArrayList<>());
					for(int j = 0;j < N;j++){
						valor.get(i).add(k);
						k++;
					}
				}
				A = new Matriz();
				try {
					System.out.println("Determinate de A:");
					A = new Matriz(valor);
					A.print_matriz();
					System.out.println("det(A) = "+A.calcular_determinante(A));
					
					
				}
				catch(TamanhoDistintoException tde) {
					System.out.println("Entrada Invalida.");
				}
		
	}

}
