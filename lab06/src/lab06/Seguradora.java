package lab06;

import java.util.ArrayList;
import java.util.HashMap;



public class Seguradora {
	
	private final String _cnpj;
	private String _nome;
	private String _telefone;
	private String _email;
	private String _endereco;
	private static ArrayList<Seguro> lista_seguro = new ArrayList<>();
	private static ArrayList<Cliente> lista_clientes = new ArrayList<>();
	
	//
	private int inst = 0;
	
	
	
	
	
	public Seguradora(String cnpj,String nome,String telefone,String email,String endereco) {
		_cnpj = cnpj;
		_nome = nome;
		_telefone = telefone;
		_email = email;
		_endereco = endereco;
	}
	public Seguradora(String cnpj){
		_cnpj = cnpj;
	}
	
	public String get_cnpj(){
		return _cnpj;
	}
	public String get_nome() {
		return _nome;
	}
	public String get_telefone() {
		return _telefone;
	}
	public String get_email() {
		return _email;
	}
	public String get_endereco() {
		return _endereco;
	}
	public int get_inst() {
		return inst;
	}

	public void set_nome(String nome) {
		_nome = nome;
	}
	public void set_telefone(String telefone) {
		_telefone = telefone;
	}
	public void set_email(String email) {
		_email = email;
	}
	public void set_endereco(String endereco) {
		_endereco = endereco;
	}
	
	public ArrayList<Cliente> listar_clientes(String tipo) {
		//tipo 0 --> PF
		//tipo 1 --> PJ
		ArrayList<Cliente> temp = new ArrayList<>();
		if(tipo.equals("PF")){
			for(int i = 0;i < lista_clientes.size();i++){
				Cliente c = lista_clientes.get(i);
				if(c.get_tipo() == 0) {
					temp.add(c);
				}
			}
			return temp;
		}
		if(tipo.equals("PJ")) {
			for(int i = 0;i < lista_clientes.size();i++) {
				Cliente c = lista_clientes.get(i);
				if(c.get_tipo() == 1) {
					temp.add(c);
				}
			}
			return temp;
		}	
		//teoricamente alguma exception aqui
		return null;
	}
	public ArrayList<Cliente> listar_clientes() {
		return lista_clientes;
	}
	
	//
	public boolean validar_datas(Date inicio,Date fim){
		boolean valido = false;
		if(fim.get_ano() > inicio.get_ano()) {
			valido = true;
		}
		else if(fim.get_ano() == inicio.get_ano()){
			if(fim.get_mes() > inicio.get_mes()) {
				valido  = true;
			}
			else if(fim.get_mes() == inicio.get_mes()){
				if(fim.get_dia() > inicio.get_dia()) {
					valido = true;
				}
			}
		}
		return valido;
	}
	
	//gerar seguro para cliente PF
	
	public boolean gerar_seguro(Cliente_PF cliente,Veiculo v,Date inicio,Date fim,Date atual){
		
		boolean gerou = false;
		int id = lista_seguro.size();
		Seguro_PF seg = new Seguro_PF(v,cliente,id,inicio,fim,this);
		
		//checando a validade das datas
		
		if(validar_datas(inicio,fim)) {
			lista_seguro.add(seg);
			seg.set_valorMensal(seg.calcularValor(atual));
			System.out.println("valor pf: "+seg.get_valorMensal());
			gerou = true;
		}
		return gerou;	
	}
	
	//gerar seguro para cliente PJ
	
	public boolean gerar_seguro(Cliente_PJ cliente,Frota f,Date inicio,Date fim,Date atual){
		
		boolean gerou = false;
		int id = lista_seguro.size();
		Seguro_PJ seg = new Seguro_PJ(id,inicio,fim,this,f,cliente);
		
		if(validar_datas(inicio,fim)) {
			//System.out.println("---");
			lista_seguro.add(seg);
			seg.set_valorMensal(seg.calcularValor(atual));
			System.out.println("valor pj: "+seg.get_valorMensal());
			//System.out.println("---");
			gerou = true;
		}
		return gerou;
	}
	
	//cancelar seguro
	
	public boolean cancelar_seguro(Seguro s){
		for(int i = 0;i < lista_seguro.size();i++) {
			if(lista_seguro.get(i) == s){
				lista_seguro.remove(i);
				return true;
			}
		}
		return false;
	}
	
	//cadastrar cliente
	
	public boolean cadastrar_cliente(Cliente c){
		lista_clientes.add(c);
		return true;
	}
	
	//remover cliente:
	
	public boolean remover_cliente(Cliente c){
		for(int i = 0;i < lista_clientes.size();i++){
			if(lista_clientes.get(i) == c) {
				lista_seguro.remove(i);
				return true;
			}
		}
		return false;
	}
	
	//get seguros por cliente PF
	
	public ArrayList<Seguro_PF> get_seguros_cliente(Cliente_PF c){
		
		ArrayList<Seguro_PF> all = new ArrayList<>();
		
		for(int i = 0;i < lista_seguro.size();i++){
			
			//Seu eu tenho certeza que e PF eu faço downcast
			
			if(lista_seguro.get(i).get_type() == 1){
				Seguro_PF seg = (Seguro_PF) lista_seguro.get(i);
				if(seg.get_cliente() == c) {
					all.add(seg);
				}
			}
			
		}
		return all;
		
	}
	
	//get seguros por cliente PJ
	
	public ArrayList<Seguro_PJ> get_seguros_cliente(Cliente_PJ c){
		
		ArrayList<Seguro_PJ> all = new ArrayList<>();
		
		for(int i = 0;i < lista_seguro.size();i++){
			
			//Seu eu tenho certeza que e PF eu faço downcast
			
			if(lista_seguro.get(i).get_type() == 0){
				Seguro_PJ seg = (Seguro_PJ) lista_seguro.get(i);
				if(seg.get_cliente() == c) {
					all.add(seg);
				}
				
			}
			
		}
		return all;
		
	}
	
	
	
	//get sinistros por cliente PF
	
	public ArrayList<Sinistro> get_sinistro(Cliente_PF c){
		
		ArrayList<Sinistro> all = new ArrayList<>();

		ArrayList<Seguro_PF> seg = get_seguros_cliente(c);
		for(int i = 0;i < seg.size();i++){
			
			Seguro s = (Seguro) seg.get(i);
			for(int j = 0;j < s.get_listaSinistros().size();j++) {
				all.add(s.get_listaSinistros().get(j));
			}
			
		}
		return all;
		
	}
	
	//get sinistros por cliente PJ
	
	public ArrayList<Sinistro> get_sinistro(Cliente_PJ c){
		
		ArrayList<Sinistro> all = new ArrayList<>();

		ArrayList<Seguro_PJ> seg = get_seguros_cliente(c);
		for(int i = 0;i < seg.size();i++){
			
			Seguro s = (Seguro) seg.get(i);
			for(int j = 0;j < s.get_listaSinistros().size();j++) {
				all.add(s.get_listaSinistros().get(j));
			}
			
		}
		return all;
		
	}
	
	//calcular receita
	
	public double calcular_receita(){
		
		double valor = 0;
		for(int i = 0;i < lista_seguro.size();i++) {
			
			if(lista_seguro.get(i).get_type() == 1) {
				Seguro_PF seg = (Seguro_PF) lista_seguro.get(i);
				valor += seg.get_valorMensal();
			}
			else {
				Seguro_PJ seg = (Seguro_PJ) lista_seguro.get(i);
				valor += seg.get_valorMensal();
			}
			
		}
		return valor;
		
	}
	
	public String toString(){
		
		StringBuilder res = new StringBuilder();
		res.append("cnpj: " + this.get_cnpj());
		res.append("/Nome: " + this.get_nome());
		res.append("/Telefone: " + this.get_telefone());
		res.append("/Email: " + this.get_email());
		res.append("/Endereco: " + this.get_endereco());
		return res.toString();
	}

	
	
}
