package geral;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Matriz{
	
	private ArrayList<ArrayList<Double>> matriz = new ArrayList<>();
	private int N;//linhas
	private int M;//colunas
	
	public Matriz() {}
	
	public Matriz(ArrayList<ArrayList<Double>> val) throws TamanhoDistintoException{
		
		/*for(int i = 0;i < n;i++) {
			matriz.add(new ArrayList<>());
			for(int j = 0;j < m;j++) {
				
				System.out.println("("+i+","+j+")");
				Scanner sc = new Scanner(System.in);
				int r = sc.nextInt();
				
			}
		}*/
		
		//todas as sub-arrays precisam ter o mesmo tamanho,por definicao
		int m = val.get(0).size();
		for(int i = 1;i < val.size();i++){
			if(val.get(i).size() != m){
				throw new TamanhoDistintoException();
				
			}
			
		}
		
		
		matriz.addAll(val);
		N = matriz.size();
		M = m;
		
	}
	
	public int get_n() {
		return N;
	}
	public int get_m(){
		return M;
	}
	public ArrayList<ArrayList<Double>> get_matriz(){
		return matriz;
	}
	
	public Matriz multiplicar(Matriz B){
		
		//try catch no prod interno
		
		Matriz A = this;
		
		/*Vou pegar as linhas de A e transformar em vetores,e as colunas de B e transfromar em vetores,dai fazer o prod interno
		 * a1 . b1,a1 . b2,...,an . bn
		 */
		
		ArrayList<ArrayList<Double>> matriz_b = B.get_matriz();
		
		//Definindo as arrays que vao guardar os valores:
		ArrayList<ArrayList<Double>> temp_a = new ArrayList<>();
		ArrayList<ArrayList<Double>> temp_b = new ArrayList<>();
		
		//Vetores de A e B
		ArrayList<Vetor> a = new ArrayList<>();
		ArrayList<Vetor> b = new ArrayList<>();
		
		//completando temp_a e temp_b
		for(int i = 0;i < N;i++) {
			temp_a.add(new ArrayList<>());
			temp_b.add(new ArrayList<>());
			for(int j = 0;j < M;j++){
				temp_a.get(i).add(matriz.get(i).get(j));//por linha
				temp_b.get(i).add(matriz.get(j).get(i));//por coluna
			}
		}
		
		//completando a e b
		for(int i = 0;i < temp_a.size();i++) {
			Vetor v = new Vetor(temp_a.get(i));
			a.add(v);
		}
		for(int i = 0;i < temp_b.size();i++) {
			Vetor v = new Vetor(temp_b.get(i));
			b.add(v);
		}
		
		ArrayList<ArrayList<Double>> resultado = new ArrayList<>();
		
		for(int i = 0;i < a.size();i++){
			resultado.add(new ArrayList<>());
			for(int j = 0;j < b.size();j++){
				try {
					resultado.get(i).add(a.get(i).prod_interno(b.get(j)));
				}
				catch(TamanhoDistintoException tde){
					System.out.println("Multiplicacao Invalida.");
				}
			}
		}
		Matriz A_B = new Matriz();
		try {
			
			A_B = new Matriz(resultado);
			
		}
		catch(TamanhoDistintoException tde) {
			System.out.println("Multiplicacao Invalida.");
		}
		
		return A_B;
		
	}
	
	//apenas uma funcao para facilitar os testes
	
	public void print_matriz(){
		for(int i = 0;i < N;i++) {
			System.out.print("[");
			for(int j = 0;j < M;j++){
				System.out.format("%.2f",matriz.get(i).get(j));
				//Nao sei porque o java nao quer colocar o double com ponto,mas assim serve...
				if(j != M-1) {
					System.out.print(" ");
				}
			}
			System.out.print("]");
			System.out.println();
		}
		
	}
	
	//Usando o Algoritmo de Gauss para computar inverso de uma matriz
	
	public void operar_pivo(Matriz M,int a,int b,int k){
			/*M:seria a matriz aclopada
			*(a,b):posicao do pivo/a:linha,b:coluna
			*k:linha que vamos operar
			*/
		
			ArrayList<ArrayList<Double>> matriz = M.get_matriz();
			//matriz.addAll(this.get_matriz());
			
			double pivo = matriz.get(a).get(b);
			double dual_pivo = matriz.get(k).get(b);//termo que precisamos que seja 0
			double razao = dual_pivo/pivo;
			
			//eu nao quero modificar esse valores diretamente em M,essas listas sao temporarias,o valor que vai pra M
			//e somente a modifcacao na linha k
			
			ArrayList<Double> linha_pivo = new ArrayList<>();
			ArrayList<Double> linha_dual = new ArrayList<>();
			linha_dual.addAll(matriz.get(k));
			
			//multiplicando a linha do pivo pela razao
			
			for(int i = 0;i < matriz.get(a).size();i++) {
				linha_pivo.add(razao*matriz.get(a).get(i));
			}
			
			//fazendo linha_pivo - linha_dual e jogando na linha k de M
			
			ArrayList<Double> linha_k = matriz.get(k);//pointer para a linha k
			linha_k.clear();
			for(int i = 0;i < linha_pivo.size();i++) {
				linha_k.add(linha_pivo.get(i) - linha_dual.get(i));
			}
			
		}
	
	public Matriz calcular_inversa(){
		
		if(M != N) {
			return null;
		}
		if(this.calcular_determinante(this) == 0) {
			System.out.println("Determinante = 0,Inversa nao possui interpetacao algebrica");
			return null;
		}
		
		//eu nao modificar a matriz original,entao eu preciso delcarar uma nova na memoria para fazer as modificacoes
		
		Matriz M = new Matriz();
		try {
			
			//Segundo o Algoritmo do Gauss eu preciso acoplar a matriz identidade corresponte em M
			
			ArrayList<ArrayList<Double>> matriz = new ArrayList<>();
			matriz.addAll(this.get_matriz());
			
			ArrayList<ArrayList<Double>> I = new ArrayList<>();//IDENTIDADE
			
			for(int i = 0;i < this.N;i++) {
				I.add(new ArrayList<>());
				for(int j = 0;j < this.M ;j++){
					if(i == j) {
						I.get(i).add(1.0);
						continue;
					}
					I.get(i).add(0.0);
				}
			}
			
			for(int i = 0;i < matriz.size();i++) {
				matriz.get(i).addAll(I.get(i));
			}
			
			//matriz acoplada:
			
			M = new Matriz(matriz);
			//M.print_matriz();
			//System.out.println();
	
			
		}
		catch(TamanhoDistintoException tde) {
			System.out.println("Entrada Invalida.");
		}
		
		//Aplicando o Algoritmo de Gauss propriamente:
		
		
		//Escolhemos como pivo as diagonais da matriz original
		
		for(int i = 0;i < this.N;i++) {
			for(int j = 0;j < this.M;j++){
				
				//System.out.println();
				//M.print_matriz();
				if(i == j & M.get_matriz().get(i).get(j) != 0) {
					
					//operar para cima:
					
					if(i-1 >= 0){
						for(int v = i-1;v >= 0;v--){
							if(M.get_matriz().get(v).get(j) != 0) {
								operar_pivo(M,i,j,v);
							}
						}
					}
					
					//operar para baixo
					
					if(i+1 < this.N){
						for(int v = i+1; v < this.N;v++) {
							if(M.get_matriz().get(v).get(j) != 0) {
								operar_pivo(M,i,j,v);
							}
						}
					}
					
				}
				continue;
				
			}
		}
		
		//System.out.println(pivos);
		//Agora temos a matriz acoplada,precisamos pegar a segunda parte e dividir cada linha por seus pivos correspondentes
		
		
		ArrayList<Double> pivos = new ArrayList<>();
		
		for(int i = 0;i < this.N;i++) {
			for(int j = 0;j < this.M;j++){
				if(i == j) {
					pivos.add(M.get_matriz().get(i).get(j));
				}
			}
		}
		
		ArrayList<ArrayList<Double>> inversa = new ArrayList<>();
		//System.out.println(M.get_matriz());
		
		int k = 0;
		for(int i = 0;i < this.N;i++) {
			inversa.add(new ArrayList<>());
			for(int j = this.M;j < 2*this.M;j++){
				double x = M.get_matriz().get(i).get(j);
				if(pivos.get(k) != 0){
					x /= pivos.get(k);
				}
				inversa.get(i).add(x);
			}
			k++;
		}
		Matriz A = new Matriz();
		try {	
			A = new Matriz(inversa);
		}
		catch(TamanhoDistintoException tde) {
			System.out.println("Entrada Invalida.");
		}
		
		return A;
		
	}
	
	/*Usando o algoritmo do Laplace para caluclar o determinante de uma matriz,vou usar recursao pois e mais intuitivo,mas suponho
	 * que implementar iterativamente seja mais seguro e mais facil de testar...
	 */
	
	public Matriz sub_matriz(Matriz M,int a,int b){
		
		//dado uma posicao (a,b):pegar a submatriz,que por sua vez e definida como: M sem a linha a e sem a coluna b
		
		ArrayList<ArrayList<Double>> matriz = new ArrayList<>();
		matriz.addAll(M.get_matriz());
		ArrayList<ArrayList<Double>> sub_matriz = new ArrayList<>();
		int v = 0;
		for(int i = 0;i < matriz.size();i++) {
			if(i != a) {
				sub_matriz.add(new ArrayList<>());
				for(int j = 0;j < matriz.get(i).size();j++){
					if(j != b){
						sub_matriz.get(v).add(matriz.get(i).get(j));
					}
				}
				v++;
			}
		}
		
		Matriz A = new Matriz();
		try {
			//System.out.println(sub_matriz);
			A = new Matriz(sub_matriz);
			
		}
		catch(TamanhoDistintoException tde) {
			System.out.println("Entrada Invalida.");
		}
		return A;
		
	}
	
	public double calcular_determinante(Matriz M){
		
		if(M.get_matriz().size() == 1) {
			return M.get_matriz().get(0).get(0);
		}
		
		double det = 0;
		
		//pode escolher qualquer linha,mas vou arbitrar a linha 0
		for(int i = 0;i < M.get_matriz().get(0).size();i++){
			Matriz A = sub_matriz(M,0,i);
			if(!A.get_matriz().isEmpty()) {
				det += Math.pow(-1, 0+i)*M.get_matriz().get(0).get(i)*calcular_determinante(A);
			}
		}
		
		
		return det;
	}
		

}
