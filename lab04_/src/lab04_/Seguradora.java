package lab04_;

import java.util.ArrayList;
import java.util.HashMap;

public class Seguradora {
	private String _nome;
	private String _telefone;
	private String _email;
	private String _endereco;
	private static ArrayList<Sinistro> listaSinistros = new ArrayList<>();
	private static ArrayList<Cliente> listaClientes = new ArrayList<>();
	
	private static HashMap<Cliente,Double> precoSeguro = new HashMap<>();
	
	/*construtor*/
	
	public Seguradora(String nome,String telefone,String email,String endereco) {
		_nome = nome;
		_telefone = telefone;
		_email = email;
		_endereco = endereco;
	}
	
	/*gets*/
	
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
	
	/*sets*/
	
	public void setNome(String nome) {
		_nome = nome;
	}
	public void setTelefone(String telefone) {
		_telefone = telefone;
	}
	public void setEmail(String email) {
		_email = email;
	}
	public void setEndereco(String endereco) {
		_endereco = endereco;
	}
	
	/*Cliente*/
	
	public void cadastrarCliente(Cliente c) {
		listaClientes.add(c);
	}
	public boolean removerCliente(String c) {
         		
		boolean removeu = false;
	
		//como o input é uma string eu assumo que é o nome do cliente
		
		for(int i = 0;i < listaClientes.size();i++) {
			if(listaClientes.get(i).get_nome().equals(c)) {
				listaClientes.remove(i);
				removeu = true;
				break;
			}
		}
		return removeu;
		
	}
	public ArrayList listarClientes(String tipoCliente) {
		return listaClientes;
	}
	
	/*Sinistro*/
	
	public boolean gerarSinistro(String data,String endereco,Veiculo v,Cliente c,Seguradora essa) {
		boolean gerou = false;
		
		//Seguradora essa = new Seguradora(_nome,_telefone,_email,_endereco);//copiar a seguradora 
		int id = listaSinistros.size()+1;//gerar id
		
		/*checar se o veiculo e o cliente estão cadastrados na seguradora*/
		
		/*Cliente*/
		
		int j = 0;
		int idx = 0;
		for(int i = 0;i < listaClientes.size();i++) {
			if(listaClientes.get(i).equals(c)) {
				j++;
				idx = i;
				break;
			}
		}
		
		/*checando se o carro é dele mesmo*/
		
		Cliente cl = listaClientes.get(idx);
		
		for(int i = 0;i < cl.get_veiculos().size();i++) {
			if(cl.get_veiculos().get(i).equals(v)){
				j++;
				break;
			}
		}
		
		if(j >= 2) {
			gerou = true;
		}
		
		Sinistro sin = new Sinistro(id,data,endereco,essa,v,c);
		listaSinistros.add(sin);
		return gerou;
		
	}
	public boolean visualizarSinistro(Cliente cliente) {
		//
		boolean visualizou = false;
		
		for(int i = 0;i < listaSinistros.size();i++) {
			if(listaSinistros.get(i).getCliente().equals(cliente)) {
				visualizou = true;
				System.out.println(listaSinistros.get(i));
				break;
			}
		}
		
		return visualizou;
	}
	
	public ArrayList listarSinistros() {
		return listaSinistros;
	}
	
	//calcular_preco_seguro_clientes	
	
	public void calcular_preco_seguro_clientes(){
		
		//return c.calcular_score() * (1 + listaSinistros.size());
		for(int i = 0;i < listaClientes.size();i++){
			precoSeguro.put(listaClientes.get(i),listaClientes.get(i).calcular_score() * (1+listaSinistros.size()));
		}
		
	}
	
	//calcular_receita
	
	public double calcular_receita(){
		
		double receita = 0;
		for(int i = 0;i < listaClientes.size();i++){
			receita += precoSeguro.get(listaClientes.get(i));
		}
		return receita;
	}
	
	//transferir_seguro
	
	public void transferir_seguro(Cliente a,Cliente b){
		//transferir de do cliente a para o cliente b
		
		for(int i = 0;i < a.get_veiculos().size();i++){
			b.get_veiculos().add(a.get_veiculos().get(i));
			a.get_veiculos().remove(i);
		}
	
		
	}
	

}
