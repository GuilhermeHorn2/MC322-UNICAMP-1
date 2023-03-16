package lab02_;

public class Seguradora {
	String _nome;
	String _telefone;
	String _email;
	String _endereco;
	
	//constutor
	public Seguradora(String nome,String telefone,String email,String endereco){
		_nome = nome;
		_telefone = telefone;
		_email = email;
		_endereco = endereco;
	}
	
	//gets
	public String getNome() {
		return _nome;
	}
	public String getTelefone() {
		return _telefone;
	}
	public String getEmail() {
		return _email;
	}
	public String getEndereco() {
		return _endereco;
	}
	
	//set
	public void setNome(String nome) {
		_nome = nome;
	}
	public void setTelefone(String telefone) {
		_nome = telefone;
	}
	public void setEmail(String email) {
		_nome = email;
	}
	public void setEndereco(String endereco) {
		_nome = endereco;
	}
}
