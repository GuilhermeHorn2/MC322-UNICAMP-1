package geral;

import java.util.ArrayList;

public class Vetor {
	
	
	private ArrayList<Double> valores = new ArrayList<>();
	private int tamanho;
	
	public Vetor(ArrayList<Double> val){
		
		valores.addAll(val);
		tamanho = valores.size();
	}
	
	public ArrayList<Double> get_valores(){
		return valores;
	}
	
	public int get_tamanho(){
		return tamanho;
	}
	
	public double prod_interno(Vetor b) throws TamanhoDistintoException{
		
		ArrayList<Double> val_b = b.get_valores();
		
		if(tamanho != b.get_tamanho()){
			//fazer alguma exception aqui,invalido calcular prod interno
			throw new TamanhoDistintoException();
		}
		
		int s = 0;
		
		for(int i = 0;i < tamanho;i++){
			s += valores.get(i)*val_b.get(i);
		}
		return s;
		
	}

}
