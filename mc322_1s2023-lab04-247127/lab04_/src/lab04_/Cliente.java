package lab04_;

import java.util.ArrayList;

public class Cliente {

	private String _nome;
    private String _endereco;
    private static ArrayList<Veiculo> veiculos =  new ArrayList<>();
    private double _valor_seguro;
   
    
    //construtor
    
    public Cliente(String nome,String endereco) {
    	_nome = nome;
    	_endereco = endereco;
    	//_valor_seguro = valor_seguro;
    	//veiculos.add(v);
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
    
    public Veiculo get_veiculo(int idx) {
    	return veiculos.get(idx);
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
    
    //tipo cliente
    
    public String tipo_cliente() {
    	return "";
    }
    
    //remover veiculo
    
    public void remover_veiculo(int idx) {
    	veiculos.remove(idx);
    }
	
    //add veiculo
    
    public void add_veiculo(Veiculo v) {
    	veiculos.add(v);
    }
}
