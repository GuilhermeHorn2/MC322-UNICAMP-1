package lab05;

import java.util.ArrayList;

public abstract class Cliente {
	
	private String _nome;
	private String _telefone;
	private String _endereco;
	private String _email;
	
	
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
	
	
	//Validacao:
	
	//validar cpf:
	
	public boolean validaCPF(String cpf) {
		//r é o cpf mas com apenas numeros
		String r = cpf.replaceAll("[\\s.-]+","");
		//
		if(r.length() != 11) {
			return false;
		}
		
		//forma para verificar se o cpf tem todos os digitos iguais
		String s = "";
		int c = 0;
		for(int i = 0;i < r.length();i++) {
			s = r.substring(0, 1);
			if(i != 0 && r.substring(i, i+1).equals(s)){
				c++;
			}
		}
		if(c == r.length() -1) {
			return false;
		}
		
		//calculo dos digitos verificadores:
		
		//passar os  digitos para um vetor como inteiros
		int[] v = new int[11];
		for(int i = 0;i < 11;i++) {
			v[i] = Integer.parseInt(r.substring(i, i+1));
		}
		
		//implementação do primeiro algoritmo 
		int prim = 0;//checando os digitos de 1 até 9
		int mult = 10;//multiplicador
		for(int i = 0;i < 9;i++){
			prim += v[i]*mult;
			mult--;
		}
		if(prim % 11 == 0 || prim % 11 == 1) {
			prim = 0;
		}
		else if(prim % 11 != 0 || prim % 11 != 1) {
			prim = 11 - prim%11;
		}
		//checando:
		
		if(v[9] == prim) {
			return true;
		}
		else if(v[9] != prim){
			//o 10 digito do cpf não está de acordo com o algoritmo
			
			return false;
		}
		mult = 10;
		//implementação do segundo algoritmo
		
		int sec = 0;//checando do 2 até o 10
		for(int i = 1;i < 10;i++) {
			//
			sec += v[i]*mult;
			mult--;
		}
		if(sec % 11 == 0 || sec % 11 == 1) {
			sec = 0;
		}
		else if(sec % 11 != 0 || sec % 11 != 1){
			sec = 11 - sec%11;
		}
		//checando:
		
		if(v[10] == sec) {
			return true;
		}
		else if(v[10] != sec){
			//o 11 digito do cpf não está de acordo com o algoritmo
			
			return false;
		}		
		return true;	
		
	}
	
	//validar cnpj:
	
	public boolean validaCNPJ(String cnpj) {
		/*Tirar . e -*/
		String c = cnpj;
		c = c.replaceAll("[\\s.-]+","");
			
		/*Passar todos os digitos de c para inteiros em v*/
		int[] v = new int[c.length()];
		for(int i = 0;i < c.length();i++) {
			int k = Integer.parseInt(c.substring(i, i+1));
			v[i] = k;
		}
			
		/*Calculo do primeiro digito verificador*/
		
		int p = 2;//peso
		int s = 0;//soma
		
		for(int i = 11;i >= 0;i--) {
			if(p == 10) {
				p = 2;
			}
			s += v[i]*p;
			p++;
		}
		
		int d = s % 11;
		if(d == 0 || d == 1){
			d = 0;
		}
		else {
			d = 11 - d;
		}
		
		if(v[12] != d) {
			//verificação falhou
			return false;
		}
		
		/*calculo do segundo digito*/
		
		p = 2;
		s = 0;
		for(int i = 12;i >= 0;i--) {
			if(p == 10) {
				p = 2;
			}
			s += v[i]*p;
			p++;
		}
		
		d = s % 11;
		
		if(d == 0 || d == 1) {
			d = 0;
		}
		else {
			d = 11 - d;
		}
		if(v[13] != d) {
			
			return false;
		}					
		
			return true;
		}	
	
	//valida nome:
	
	public boolean validaNome(String nome){
		
		//checa se o nome so tem letras
		
		String cmp = nome.replaceAll("[^a-zA-Z]","");
		if(cmp.length() != nome.length()){
			
			//teve que tirar alguma coisa do nome
			
			return  false;
		}
		return true;
	}
	
	//
	
	//metodo que identifica o tipo do cliente para ajudar nos metodos da seguradora
	public abstract int get_tipo();
	
	


	

}
