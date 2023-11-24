package lab05;

import java.util.ArrayList;

public class Cliente_PF extends Cliente{
	
	private final String _cpf;
	private String _genero;
	private String _educacao;
	private Date _dataNasc;
	private static ArrayList<Veiculo> lista_veiculos = new ArrayList<>();
	
	public Cliente_PF(String cpf,String genero,String educacao,Date dataNasc,String nome, String telefone, String endereco, String email) {
		super(nome, telefone, endereco, email);
		
		_cpf = cpf;
		_genero = genero;
		_educacao = educacao;
		_dataNasc = dataNasc;
	}
	
	public String get_cpf() {
		return _cpf;
	}
	public String get_genero() {
		return _genero;
	}
	public String get_educacao() {
		return _educacao;
	}
	public Date get_dataNasc() {
		return _dataNasc;
	}
	public ArrayList<Veiculo> get_lista_veiculos() {
		return lista_veiculos;
	}
	

	public void set_genero(String genero) {
		_genero = genero;
	}
	public void set_educacao(String educacao) {
		_educacao = educacao;
	}
	public void get_dataNasc(Date dataNasc) {
		_dataNasc =  dataNasc;
	}
	
	public boolean cadastrar_veiculo(Veiculo v) {
		lista_veiculos.add(v);
		return true;
	}
	
	public boolean remover_veiculo(Veiculo v) {
		boolean rmv = false;
		for(int i = 0;i < lista_veiculos.size();i++){
			if(lista_veiculos.get(i) == v) {
				rmv = true;
				lista_veiculos.remove(i);
			}
		}
		return rmv;
	} 
	
	public String toString(){
		
		StringBuilder res = new StringBuilder();
		res.append("nome: " + this.get_nome());
		res.append(" / telefone: " + this.get_telefone());
		res.append(" / endereco: " + this.get_endereco());
		res.append(" / email: " + this.get_email());
		res.append(" / cpf: " + this.get_cpf());
		res.append(" / genero:" + this.get_genero());
		res.append(" / educacao: " + this.get_educacao());
		res.append(" / data Nascimento: " + this.get_dataNasc());
		return res.toString();
	}

	@Override
	public int get_tipo() {
		//PF --> 0
		return 0;
	}

}
