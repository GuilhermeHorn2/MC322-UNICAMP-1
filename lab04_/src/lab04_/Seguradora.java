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
	private int inst = 0;
	
	/*construtor*/
	
	public Seguradora(String nome,String telefone,String email,String endereco) {
		_nome = nome;
		_telefone = telefone;
		_email = email;
		_endereco = endereco;
		
		inst = 1;
	}
	
	public Seguradora() {}
	
	public int get_inst(){
		return inst;
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
	public HashMap<Cliente,Double> get_preco_seguro(){
		return precoSeguro;
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
	
	public boolean cadastrarCliente(Cliente c) {
		listaClientes.add(c);
		return true;
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
	public ArrayList clientes() {
		return listaClientes;
	}
	
	public Cliente index_cliente(int i) {
		return listaClientes.get(i);
	}
	
	public ArrayList listarClientes(String tipoCliente) {
		ArrayList<Cliente> tipo = new ArrayList<>();
		for(int i = 0;i < listaClientes.size();i++){
			if(listaClientes.get(i).tipo_cliente().equals(tipoCliente)){
				tipo.add(listaClientes.get(i));
			}
		}	
		return tipo;	
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
	
	public ArrayList<Sinistro> listarSinistros() {
		return listaSinistros;
	}
	
	public ArrayList<Sinistro> sinistros_por_cliente(Cliente c){
		ArrayList<Sinistro> l = new ArrayList<>();
		
		for(int i = 0;i < listaSinistros.size();i++){
		   if(listaSinistros.get(i).getCliente() == c){
			   l.add(listaSinistros.get(i));
		   }
		}
		return l;
	}
	
	public ArrayList<Veiculo> veiculos_por_clientes(Cliente c){
		return c.get_veiculos();
	}
	
	public ArrayList<ArrayList<Veiculo>> veiculos_por_seguradora(){
		ArrayList<ArrayList<Veiculo>> v = new ArrayList<>();
		for(int i = 0;i < listaClientes.size();i++) {
			v.add(listaClientes.get(i).get_veiculos());
		}
		return v;
	}
	
	//calcular_preco_seguro_clientes	
	
	public int qtde_sinistros(Cliente c) {
		int k = 0;
		for(int i = 0;i < listaSinistros.size();i++){
			if(listaSinistros.get(i).getCliente() == c){
				k++;
			}
		}
		return k;
	}
	
     public void calcular_preco_seguro_clientes(){
    	 for(int i = 0;i < listaClientes.size();i++){
    		 Cliente c = listaClientes.get(i);
    		 double x = c.calcular_score() * (1+qtde_sinistros(c));
    		 c.set_valor(x);
    	 }
	}
     


    public double get_preco_seguro(Cliente c){
    	return precoSeguro.get(c);
    }
	
	//calcular_receita
	
	public double calcular_receita(){
		
		double receita = 0;
		
		for(int i = 0;i < listaClientes.size();i++) {
			receita += listaClientes.get(i).get_valor();
		}
		return receita;
	}
	
	//transferir_seguro
	
	public void transferir_seguro(Cliente a,Cliente b){
		//transferir de do cliente a para o cliente b
		
		for(int i = 0;i < a.get_veiculos().size();i++){
			b.get_veiculos().add(a.get_veiculos().get(i));
			a.get_veiculos().remove(i);
			
			//atualizar o preço do seguro dos clientes,chamando calcular_preco_seguro_clientes(),após uma sequencia de operações
			
			
		}		
	}
	
	//remover sinistro
	
	public void remover_sinistro(Sinistro s){
		
		listaSinistros.remove(s);
		
		//atualizar o preço do seguro dos clientes,chamando calcular_preco_seguro_clientes(),após uma sequencia de operações
	}
	
/*toString*/
	
	public String toString() {
		return "{"+ "Nome: " + _nome + "/ " + "Telfone: "+_telefone+"/ "+"Endereco: " + _endereco +"/ " + "Endereco: " + _endereco+"}";
	}
	
	

}
