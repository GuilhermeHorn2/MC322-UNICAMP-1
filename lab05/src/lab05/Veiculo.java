package lab05;

public class Veiculo {
	
	private String _placa;
	private String _marca;
	private String _modelo;
	private String _email;
	
	public Veiculo(String placa,String marca,String modelo,String email){
		_placa = placa;
		_marca = marca;
		_modelo = modelo;
		_email = email;
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
	public String get_email() {
		return _email;
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
	public void set_email(String email) {
		_email = email;
	}

}
