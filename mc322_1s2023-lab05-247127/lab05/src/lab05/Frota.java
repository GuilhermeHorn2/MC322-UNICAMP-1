package lab05;

import java.util.ArrayList;

public class Frota {
	
	private String _code;
	private ArrayList<Veiculo> listaVeiculo = new ArrayList<>();
	
	public Frota(String code){
		_code = code;
	}
	
	public String get_code() {
		return _code;
	}
	public void set_code(String code) {
		_code = code;
	}
	
	public boolean add_veiculo(Veiculo v){
		listaVeiculo.add(v);
		return true;
	}
	public boolean remove_veiculo(Veiculo v) {
		boolean rmv = false;
		for(int i = 0;i < listaVeiculo.size();i++){
			if(listaVeiculo.get(i) == v) {
				rmv = true;
				listaVeiculo.remove(i);
			}
		}
		return rmv;
	}
	
	public ArrayList<Veiculo> get_listaVeiculo(){
		return listaVeiculo;
	}

}
