package lab03_;

public class Veiculo {
	private String _placa;
	private String _marca;
	private String _modelo;
    private int _anoFabricacao;	
    
	/*Contrutores*/
	
	public Veiculo() {};
	public Veiculo(String placa,String marca,String modelo,int anoFabricacao) {
		_placa = placa;
		_marca = marca;
		_modelo = modelo;
		_anoFabricacao = anoFabricacao;
	};
	
	/*gets*/
  
	public String getPlaca(){
		return _placa;
	}
	public String getMarca(){
		return _marca;
	}	
	public String getModelo(){
		return _modelo;
	}	
	public int getAnoFabricacao() {
		return _anoFabricacao;
	}
		
	/*sets*/
		
	public void setPlaca(String placa) {
		_placa = placa;
	}
	public void setMarca(String marca) {
		_placa = marca;
	}
	public void setModelo(String modelo) {
		_placa = modelo;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		_anoFabricacao = anoFabricacao;
	}
	
	/*toString*/
	
	public String toString() {
		return "{Placa: " + _placa + "/ Marca: " + _marca + "/ Modelo: " + _modelo + "/ Ano de Fabricação: " + _anoFabricacao;
	}


}
