package lab06;

public class Veiculo {
	
	private String _placa;
	private String _marca;
	private String _modelo;
	private int anoFabricacao;
	
	public Veiculo(String placa,String marca,String modelo,int ano){
		_placa = placa;
		_marca = marca;
		_modelo = modelo;
		anoFabricacao = ano;
	}
	
	public String get_placa() {
		return _placa;
	}
	public String get_marca() {
		return _marca;
	}
	public String get_modelo() {
		return _modelo;
	}
	public int get_anoFabricacao() {
		return anoFabricacao;
	}
	
	public void set_placa(String placa) {
		_placa = placa;
	}
	public void set_marca(String marca) {
		_marca = marca;
	}
	public void set_modelo(String modelo) {
		_modelo = modelo;
	}
	public void set_anoFabricacao(int ano) {
		anoFabricacao = ano;
	}
	
public String toString(){
		
		StringBuilder res = new StringBuilder();
		res.append("Placa: " + this.get_placa());
		res.append("/Marca: " + this.get_marca());
		res.append("/Modelo: " + this.get_modelo());
		res.append("/ano Fabricacao: " + this.get_anoFabricacao());
		return res.toString();
	}

}
