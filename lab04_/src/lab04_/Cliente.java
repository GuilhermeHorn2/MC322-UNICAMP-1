package lab04_;

import java.util.ArrayList;

public class Cliente {

	private String _nome;
    private String _endereco;
    private static ArrayList<Veiculo> veiculos =  new ArrayList<>();
    private double _valor_seguro;
   
    
    //constructor
    
    public Cliente(String nome,String endereco,double valor_seguro,Veiculo v) {
    	_nome = nome;
    	_endereco = endereco;
    	_valor_seguro = valor_seguro;
    	veiculos.add(v);
    }
    
    //gets
    
    public String get_nome() {
    	return _nome;
    }
    
    public String get_endereco(){
    	return _endereco;
    }
    
    public ArrayList get_veiculos() {
    	return veiculos;
    }
    
    public double get_valor() {
    	return _valor_seguro;
    }
    
    //set
    
    public void set_nome(String s) {
    	_nome = s;
    }
    
    public void set_endereco(String e){
    	_endereco = e;
    }
    
    public void set_valor(double d){
    	_valor_seguro = d;
    }
    
    //calcular score
    
    public double calcular_score(){
         return 0;
    }
	
}
