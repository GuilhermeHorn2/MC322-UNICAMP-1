package lab05;

import java.util.ArrayList;

public abstract class Cliente {
	
	private String _nome;
	private String _telefone;
	private String _endereco;
	private String _email;
	//
	private Seguradora seguradora;
	
	public Cliente(String nome,String telefone,String endereco,String email) {
		_nome = nome;
		_telefone = telefone;
		_endereco = endereco;
		_email = email;
	}
	
	public String get_nome() {
		return _nome;
	} 
	public String get_telefone() {
		return _telefone;
	}
	public String get_endereco() {
		return _endereco;
	}
	public String get_email() {
		return _email;
	}
	
	public void set_nome(String nome) {
		_nome = nome;
	} 
	public void set_telefone(String telefone) {
		_telefone  = telefone;
	}
	public void set_endereco(String endereco) {
		_endereco = endereco;
	}
	public void set_email(String email) {
		_email =  email;
	}
	
	//metodo que identifica o tipo do cliente para ajudar nos metodos da seguradora
	public abstract int get_tipo();
	
	/*public ArrayList<Sinistro> get_sinistros(){
		
	}
	
    public ArrayList<Seguro> get_seguros(){
		
	}*/
	


	

}
