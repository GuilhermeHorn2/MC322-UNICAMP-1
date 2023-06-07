package geral;

import java.util.ArrayList;

public class Polinomio  implements Expressao{
	
	
	private String _pol;
	
	//
	
	public Polinomio(String pol){
		_pol = pol;
	}
	public String toString() {
		return _pol;
	}
	
	public int grau() {
		
		for(int i = 0;i < _pol.length();i++) {
			String s = _pol.substring(i,i+1);
			if(s.equals("^")) {
				if(i+1 < _pol.length()){
					String n = _pol.substring(i+1, i+2);
					if(is_number(n)) {
						return Integer.parseInt(n);
					}
				}
			}
		}
		return -1;
		
	}
	
	public boolean is_number(String s){
		
		if(s.equals("0")||s.equals("1")||s.equals("2")||s.equals("3")){
			return true;
		}
		if(s.equals("4")||s.equals("5")||s.equals("6")||s.equals("7")){
			return true;
		}
		if(s.equals("8")||s.equals("9")){
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<ArrayList<Integer>> gerar_listas() {
		
		ArrayList<ArrayList<Integer>> listas = new ArrayList<>();
		
		ArrayList<Integer> mult = new ArrayList<>();
		ArrayList<Integer> exp = new ArrayList<>();
		
		String sinal = "";
		boolean tem_constante = false;
		//Colocar os multiplicadores
		
		for(int i = 0;i < _pol.length();i++){
			
			String s = _pol.substring(i, i+1);
			
			if(s.equals("-") || s.equals("+")){
				sinal = s;
			}
			
			//adicionando a constante
			
			if(i == _pol.length()-1) {
				if(is_number(s) && !_pol.substring(i-1, i).equals("^")) {
					if(sinal.equals("-")) {
						mult.add(-Integer.parseInt(s));
					}
					else {
						mult.add(Integer.parseInt(s));
					}
					tem_constante = true;
				}
				
			}
			
			if(s.equals("x")){
				if(i == 0){
					if(sinal.equals("-")) {
						mult.add(-1);
					}
					else {	
						mult.add(1);				
					}
					
				}
					
				if(i!= 0){
					if(is_number(_pol.substring(i-1, i))){
						
						if(sinal.equals("-")) {
							mult.add(-Integer.parseInt(_pol.substring(i-1, i)));
						}
						else {
							mult.add(Integer.parseInt(_pol.substring(i-1, i)));
						}
					}
					else {
						if(sinal.equals("-")) {
							mult.add(-1);
						}
						else {
							mult.add(1);
						}
					}
				}
				
			}
			
		}
		
		//colocando os expoentes
		
		for(int i = 0;i < _pol.length();i++) {
			
			String s = _pol.substring(i, i+1);
			
			if(s.equals("x")){
				
				if(i+1 == _pol.length()){
					exp.add(1);
				}
				else {
					if(_pol.substring(i+1, i+2).equals("^")){
						if(i+3 < _pol.length()){
							if(_pol.substring(i+2, i+3).equals("-")){
								if(is_number(_pol.substring(i+3, i+4))){
									exp.add(-Integer.parseInt(_pol.substring(i+3, i+4)));
								}
							}
							else {
								if(is_number(_pol.substring(i+2, i+3))){
									exp.add(Integer.parseInt(_pol.substring(i+2, i+3)));
								}
							}
						}
						else {
							//System.out.println(_pol.substring(i+2, i+3));
							if(is_number(_pol.substring(i+2, i+3))){
								exp.add(Integer.parseInt(_pol.substring(i+2, i+3)));
							}
						}
					}
					else {
						exp.add(1);
					}
				}
				
			}
			
		}
		
		exp.add(0);
		
		listas.add(mult);
		listas.add(exp);
		return listas;
	}
	@Override
	public double calcular_expressao(ArrayList<ArrayList<Integer>> all,double x) {
		
		
		double valor = 0;
		ArrayList<Integer> mult = all.get(0);
		ArrayList<Integer> exp = all.get(1);
		/*System.out.println(mult);
		System.out.println(exp);*/
		for(int i = 0;i < mult.size();i++) {
			double pow = 0;
			if(exp.get(i) < 0) {
				pow = 1/Math.pow(x, -exp.get(i));
			}
			else {
				pow = Math.pow(x, exp.get(i));
			}
			valor += mult.get(i) * pow;
			
		}
		return valor;
	}
	@Override
	public ArrayList<ArrayList<Integer>> derivada(ArrayList<ArrayList<Integer>> all) {
		
		ArrayList<ArrayList<Integer>> derivada = new ArrayList<>();
		
		//Vou usar a regra informal do tombo,para polinomios
		
		ArrayList<Integer> mult = all.get(0);
		ArrayList<Integer> exp = all.get(1);
		
		ArrayList<Integer> mult_novo = new ArrayList<>();
		ArrayList<Integer> exp_novo = new ArrayList<>();
		for(int i = 0;i < mult.size();i++){
			mult_novo.add(mult.get(i)*exp.get(i));
		}
		for(int i = 0;i < exp.size();i++) {
			if(exp.get(i) != 0) {
				exp_novo.add(exp.get(i)-1);
				continue;
			}
			exp_novo.add(exp.get(i));
			if(i >= 0 && i < mult.size()) {
				mult_novo.remove(i);
				mult_novo.add(i,0);
			}
			/*mult_novo.remove(i);
			mult_novo.add(i,0);*/
		}
		derivada.add(mult_novo);
		derivada.add(exp_novo);
		return derivada;
		
	}



}
