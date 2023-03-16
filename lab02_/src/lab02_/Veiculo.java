package lab02_;

public class Veiculo {
	private String _placa;
	private String _marca;
	private String _modelo;
	
	//contrutores:
	public Veiculo() {}
	public Veiculo(String placa,String marca,String modelo) {
		_placa = placa;
		_marca = marca;
		_modelo = modelo;
	}
	
	//gets
	public String getPlaca(){
		return _placa;
	}
	public String getMarca(){
		return _marca;
	}	
	public String getModelo(){
		return _modelo;
	}	
	
	//sets
	public void setPlaca(String placa) {
		_placa = placa;
	}
	public void setMarca(String marca) {
		_placa = marca;
	}
	public void setModelo(String modelo) {
		_placa = modelo;
	}

}
