package lab05;

import java.util.ArrayList;

public class Condutor {
	private final String _cpf;
	private String _nome;
	private String _telefone;
	private String _endereco;
	private String _email;
	private Date _dataNasc;
	private ArrayList<Sinistro> listaSinistros;
	
	public Condutor(String cpf,String nome,String telefone,String endereco,String email,Date dataNasc) {
		_cpf = cpf;
		_nome = nome;
		_telefone = telefone;
		_endereco = endereco;
		_email = email;
		_dataNasc = dataNasc;
	}
	
	public String get_cpf() {
		return _cpf;
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
	public Date get_dataNasc() {
		return _dataNasc;
	}
	public ArrayList<Sinistro> get_listaSinistros() {
		return listaSinistros;
	}
	
	
	public void set_nome(String nome) {
		_nome = nome;
	}
	public void set_telefone(String telefone) {
		_telefone = telefone;
	}
	public void set_endereco(String endereco) {
		_endereco = endereco;
	}
	public void set_email(String email) {
		_email = email;
	}
	public void get_dataNasc(Date dataNasc) {
		_dataNasc = dataNasc;
	}
	
	public void adicionar_sinistro(Sinistro s){
		listaSinistros.add(s);
	}
	
	

}
