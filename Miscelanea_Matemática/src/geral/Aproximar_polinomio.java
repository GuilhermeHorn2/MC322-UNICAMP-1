package geral;

import java.util.ArrayList;
import java.util.HashMap;

public class Aproximar_polinomio extends Aproximacao{
	
	private static Polinomio _pol;
	
	public Aproximar_polinomio(Polinomio pol){
		_pol = pol;
	}
	
	/*
	 * O metodo parcial usa o metodo de Newton para aproximar raizes de funcoes,o metodo generalizado e um algoritmo
	 * que eu criei usando como como base o metodo de Newton para encontrar as raizes de um polinomio.
	 */
	
	@Override
	public double metodo_parcial(double chute) {
		ArrayList<ArrayList<Integer>> base = _pol.gerar_listas();
		ArrayList<ArrayList<Integer>>  derivada = _pol.derivada(base);
		
		//xn = xn-1 -pol(xn-1)/derivada(pol(xn-1))
		
		double raiz = chute;
		for(int i = 0;i < 100;i++) {
			
			double pol_x = _pol.calcular_expressao(base, raiz);
			double derivada_pol_x = _pol.calcular_expressao(derivada, raiz);
			if(derivada_pol_x == 0) {
				
				/*
				 * Gambiarra: se a derivada for uma linha horizontal(coef angula 0),o métode de Newton fica meio estranho
				 * entao oque eu fiz foi deslocar um pouco o ponto
				 */
				raiz -= 0.01;
				continue;
			}
			raiz = raiz - (pol_x/derivada_pol_x);
			//para limpar um pouco a lista
			if(Math.abs(raiz) <= 0.0001) {
				raiz = 0;
			}
			if(Math.abs(_pol.calcular_expressao(base, raiz)) == 0){
				break;
			}
		}
		return raiz;
	}
	
	//sobreescrita para usar em derivadas:
	
	public double metodo_parcial(ArrayList<ArrayList<Integer>> base,double chute) {
		ArrayList<ArrayList<Integer>>  derivada = _pol.derivada(base);
		
		//xn = xn-1 -pol(xn-1)/derivada(pol(xn-1))
		
		double raiz = chute;
		for(int i = 0;i < 100;i++) {
			
			double pol_x = _pol.calcular_expressao(base, raiz);
			double derivada_pol_x = _pol.calcular_expressao(derivada, raiz);
			if(derivada_pol_x == 0) {
				
				/*
				 * Gambiarra: se a derivada for uma linha horizontal(coef angula 0),o métod de Newton fica meio estranho
				 * entao oque eu fiz foi deslocar um pouco o ponto
				 */
				
				raiz += 0.1;
				continue;
			}
			raiz = raiz - (pol_x/derivada_pol_x);
			if(Math.abs(_pol.calcular_expressao(base, raiz)) == 0){
				break;
			}
		}
		return raiz;
	}
	
	public boolean in_list(ArrayList<Double> raizes,double raiz){
		
		for(int i = 0;i < raizes.size();i++) {
			if(raizes.get(i) == raiz) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Double> raizes_por_range(double range){
		
		int n = _pol.grau();
		
		/*
		 * Pelo teorema fundamental da algebra todo polinomio de grau n tem n raizes complexas,estou procurando as raizes reais
		 * :aquelas que tem parte complexa = 0.Como isso é um tópico complexo que nem os matematicos conseguiram uma boa generali-
		 * zacao para lidar com esse problema,eu vou procurar essas raizes em ranges e se as raizes nao estiverem nesse range
		 * meu algoritmo nao vai conseguir acha-las.
		 */
		
		ArrayList<Double> raizes = new ArrayList<>();
		
		int num_raizes = 0;
		
		double chute = range;

		int num_iteracoes = 0;
		while(num_raizes < n){
			
			double possivel_raiz = metodo_parcial(chute);
			if(Math.abs(_pol.calcular_expressao(_pol.gerar_listas(), possivel_raiz)) <= 0.001) {
				if(!in_list(raizes,possivel_raiz)) {
					num_raizes++;
					raizes.add(possivel_raiz);
				}
				
				/*procurar um ponto de sela(o primeiro),ponto onde a derivada é 0,esses pontos determinarao para qual raiz
				  eu vou chegar ao aplicar o metodo paricial(algoritmo do Newton para aproximacao)
				*/
				
				chute = metodo_parcial(_pol.derivada(_pol.gerar_listas()),possivel_raiz)*0.8;//deslocar para em direcao a 0
				
				/*
				 * Apos alguns testes eu percebi que multiplicar o valor por 0.8 e um bom valor para deslocar apos achar um ponto
				 * de sela,esse valor foi determinado expérimentalmente,pode haver alguma forma de achar um valor otimizado
				 * mas esse parametro faz seu trabalho.Gambiarra!.
				 */
			
				
			}
			if(num_iteracoes >= 10_000){
				break;
			}
			num_iteracoes++;
			
		}
		
		return raizes;
		
		
	}
		
	
	//funçao para limpar a lista,caso tiver repeticoes
	
	public ArrayList<Double> limpar(ArrayList<Double> raizes){
		
		HashMap<Double,Integer> qntd = new HashMap<>();
		
		ArrayList<Double> limpo = new ArrayList<>();
		
		for(int i = 0;i < raizes.size();i++) {
			
			double r = raizes.get(i);
			
			if(qntd.containsKey(r)){
				continue;
			}
			else {
				qntd.put(r, 1);
				limpo.add(r);
			}
		}
		return limpo;
	}
	
	@Override
	public ArrayList<Double> metodo_generalizado() {
		
		ArrayList<Double> raizes_negativas = raizes_por_range(-1_000);
		ArrayList<Double> raizes_positivas = raizes_por_range(1_000);
		raizes_negativas.addAll(raizes_positivas);
		return limpar(raizes_negativas);
	}

}
