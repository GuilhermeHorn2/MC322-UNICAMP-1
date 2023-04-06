package lab03_;

public class Sinistro {
	private int _id;
	private String _data;
	private String _endereco;
	private Seguradora _seguradora;
	private Veiculo _veiculo;
	private Cliente _cliente;
	
	/*Construtor*/
	
	public Sinistro(int id,String data,String endereco,Seguradora seguradora,Veiculo veiculo,Cliente cliente) {
		_id = id;
		_data = data;
		_endereco = endereco;
		_seguradora = seguradora;
		_veiculo = veiculo;
		_cliente = cliente;
	}
	
	/*gets*/
	
	public int getId() {
		return _id;
	}
	public String getData() {
		return _data;
	}
	public String getEndereco() {
		return _endereco;
	}
	public Seguradora getSeguradora() {
		return _seguradora;
	}
	public Veiculo getVeiculo() {
		return _veiculo;
	}
	public Cliente getCliente() {
		return _cliente;
	}
	
	/*sets*/
	
	public void setId(int id) {
		_id = id;
	}
	public void setData(String data) {
		_data = data;
	}
	public void setEndereco(String endereco) {
		_endereco = endereco;
	}
	public void setSeguradora(Seguradora seguradora) {
		_seguradora = seguradora;
	}
	public void setVeiculo(Veiculo veiculo) {
		_veiculo = veiculo;
	}
	public void setCliente(Cliente cliente) {
		_cliente = cliente;
	}
	
	/*toString*/
	
	public String toString() {
		return "{ID: " + _id + "/ Data: " + _data + "/ Endereço: " + _endereco + "/ Seguradora: " + _seguradora + "/ Veiculo: " + _veiculo + "/ Cliente:" + _cliente +"}";
	}

}
