package lab04_;

import java.util.Scanner;

public class AppMain {

	public static class validacao{		
		private final String _cpf;
		private final String _cnpj;
		private final String _nome;
		
		public validacao(String cpf,String cnpj,String nome) {
			_cpf = cpf;//se for PJ cpf = null
			_cnpj = cnpj;//se for PF cnpj = null
			_nome = nome;
		}
		
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
		
		public boolean validaNome(String nome){
			
			//checa se o nome so tem letras
			
			String cmp = nome.replaceAll("[^a-zA-Z]","");
			if(cmp.length() != nome.length()){
				
				//teve que tirar alguma coisa do nome
				
				return  false;
			}
			return true;
		}
			
	}
	
	public static void main(String[] args) {
		
		
		
		/*Instanciando uma seguradora*/
		Seguradora s = new Seguradora("nome","telefone","email","endereco");
		
		/*Validação*/
		validacao val = new validacao("","","nome");
		
		if(!val.validaNome(s.getNome())) {
			System.out.println("Nome inválido");
		}
		
		
		/*Instanciando 2 clientes e 2 veiculos*/
		
		Veiculo v1 = new Veiculo("placa","marca","modelo",2019);
		Veiculo v2 = new Veiculo("placa","marca","modelo",2018);
		
		/*ClientePF*/
		
		Date dataNascimento = new Date("12","11","1999");
		Date dataLicensa = new Date("12","11","2030");
		ClientePF c1 = new ClientePF("nome","endereço","084.688.310-42","gen",dataLicensa,"educação",dataNascimento,"classe");
		c1.add_veiculo(v1);
		

		
		validacao val1 = new validacao(c1.get_cpf(),"",c1.get_nome());
		if(!val1.validaCPF(c1.get_cpf()) && !val1.validaNome(c1.get_nome())) {
			System.out.println("Cliente Inválido");
		}
		else {
			s.cadastrarCliente(c1);
			s.calcular_preco_seguro_clientes();//atualizar preço
		}
		
		/*valor_seguro de c1:*/
		System.out.print("Valor de c1: ");
		System.out.println(c1.get_valor());
		
		/*ClientePJ*/
		
		ClientePJ c2 = new ClientePJ("nome","endereço","14.572.457.0001-85",dataLicensa,100);
		c2.add_veiculo(v2);
		
		validacao val2 = new validacao("",c2.get_cnpj(),c2.get_nome());
		if(!val1.validaCPF(c2.get_cnpj()) && !val1.validaNome(c2.get_nome())) {
			System.out.println("Cliente Inválido");
		}
		else {
			s.cadastrarCliente(c2);
			s.calcular_preco_seguro_clientes();//atualizar preço
		}
		
		/*valor_seguro de c2:*/
		System.out.print("Valor de c2: ");
		System.out.println(c2.get_valor());
		
		
		/*Gerar 2 Sinistros*/
		
		s.gerarSinistro("data", "endereco", v1, c1, s);
		s.gerarSinistro("data", "endereco", v2, c2, s);
		
		/*A adição do sinistro mudou o valor do seguro do c1*/
		System.out.print("Novo valor de c1: ");
		System.out.println(c1.get_valor());
		
		/*Listar ClientesPF*/
		System.out.println(s.listarClientes("PF"));
		
		/*Listar ClientesPJ*/
		System.out.println(s.listarClientes("PJ"));
		
		/*Visualizar Sinistro de c1*/
		s.visualizarSinistro(c1);
		
		/*Visualizar Sinistro de c2*/
		s.visualizarSinistro(c2);
		
		/*Listar Sinistros*/
		System.out.println(s.listarSinistros());
		
		/*Calcular Receita*/
		System.out.print("Valor da receita: ");
		System.out.println(s.calcular_receita());
		
		
		
		menu();
		
	}
	
	public static void menu() {
		MenuOperacoes op1 = MenuOperacoes.CADASTROS;
		MenuOperacoes op2 = MenuOperacoes.LISTAR;
		MenuOperacoes op3 = MenuOperacoes.EXCLUIR;
		MenuOperacoes op4 = MenuOperacoes.GERAR_SINISTRO;
		MenuOperacoes op5 = MenuOperacoes.TRANSFERIR_SEGURO;
		MenuOperacoes op6 = MenuOperacoes.CALCULAR_RECEITA_SEGURADORA;
		MenuOperacoes op0 = MenuOperacoes.SAIR;
		
		while(true) {
			System.out.println("Operacao(0 para sair): ");
			Scanner sc1 = new Scanner(System.in);
			int x = sc1.nextInt();
			
			Seguradora s = new Seguradora();
			
			if(x == op0.getOperacao()){
				break;
			}
			if(x == op1.getOperacao()){
				//CADASTROS:
				while(true) {
					
					System.out.println("CADASTRAR(4 para voltar):");
					Scanner sc2 = new Scanner(System.in);
					int x1 = sc2.nextInt();
					//Seguradora s = new Seguradora();
					if(x1 == 4) {
						break;
					}
					if(x1 == 1) {
						//Cadastrar Cliente PF/PJ
						if(s.get_inst() == 1){
							//Para cadastrar um cliente precisa haver uma seguradora						
							
							System.out.println("Nome: ");
					    	Scanner sc10 = new Scanner(System.in);
					    	String nome = sc10.next();
					    	
					    	System.out.println("Endereço: ");
					    	Scanner sc11 = new Scanner(System.in);
					    	String end = sc11.next();
					    	
							//Cliente c = new Cliente(nome,end,0);
					    	
						    System.out.println("PF OU PJ: ");
						    Scanner sc3 = new Scanner(System.in);
						    String tipo = sc3.next();
						    
						    sc10.close();
						    sc11.close();
						    sc3.close();
						    
						    if(tipo.equals("PF")){
						    	System.out.println("CPF: ");
						    	Scanner sc4 = new Scanner(System.in);
						    	String cpf = sc4.next();
						    	
						    	System.out.println("Genero: ");
						    	Scanner sc5 = new Scanner(System.in);
						    	String genero = sc5.next();
						    	
						    	System.out.println("Data Licensa: ");
						    	Scanner sc6 = new Scanner(System.in);
						    	String data_str = sc6.next();
						    	//data_str:xx/xx/xxxx
						    	Date data = new Date(data_str.substring(0, 1),data_str.substring(3,4),data_str.substring(6,9));
						    	
						    	System.out.println("Educacao: ");
						    	Scanner sc7 = new Scanner(System.in);
						    	String edu = sc7.next();
						    	
						    	System.out.println("Data Nascimento: ");
						    	Scanner sc8 = new Scanner(System.in);
						    	String data_str2 = sc8.next();
						    	//data_str:xx/xx/xxxx
						    	Date data2 = new Date(data_str2.substring(0, 1),data_str2.substring(3,4),data_str2.substring(6,9));
						    	
						    	System.out.println("Classe: ");
						    	Scanner sc9 = new Scanner(System.in);
						    	String classe = sc9.next();
						    	
						    	ClientePF c = new ClientePF(nome,end,cpf,genero,data,edu,data2,classe);
						    	s.cadastrarCliente(c);
						    	
						    	sc4.close();
                                sc5.close();
                                sc6.close();
                                sc7.close();
                                sc8.close();
                                sc9.close();
						    }
						    if(tipo.equals("PJ")){
						    	System.out.println("CNPJ: ");
						    	Scanner sc4 = new Scanner(System.in);
						    	String cnpj = sc4.next();
						    	
						    	System.out.println("Data Fundação: ");
						    	Scanner sc5 = new Scanner(System.in);
						    	String data_str = sc5.next();
						    	//data_str:xx/xx/xxxx
						    	Date data = new Date(data_str.substring(0, 1),data_str.substring(3,4),data_str.substring(6,9));
						    	
						    	System.out.println("Quantidade de funcionários: ");
						    	Scanner sc6 = new Scanner(System.in);
						    	int qtde = sc6.nextInt();
						    	
						    	ClientePJ c = new ClientePJ(nome,end,cnpj,data,qtde);
						    	s.cadastrarCliente(c);
						    	
						    	sc4.close();
						    	sc5.close();
						    	sc6.close();
						    	
						    }
						    
					}
						else {
							System.out.println("Não há seguradora");
						}
					
				}
				if(x1 == 2) {
					//Cadastrar Veiculo v no cliente de index idx,precisa haver uma seguradora cadastrada antes
					
					if(s.get_inst() == 1) {
						//Há seguradora cadastrada
						
						System.out.println("Placa: ");
				    	Scanner sc4 = new Scanner(System.in);
				    	String placa = sc4.next();
				    	
				    	System.out.println("Marca: ");
				    	Scanner sc5 = new Scanner(System.in);
				    	String marca = sc5.next();
				    	
				    	System.out.println("modelo: ");
				    	Scanner sc6 = new Scanner(System.in);
				    	String modelo = sc6.next();
				    	
				    	System.out.println("Ano de Fabricação: ");
				    	Scanner sc7 = new Scanner(System.in);
				    	int ano = sc7.nextInt();
				    	
				    	Veiculo v = new Veiculo(placa,marca,modelo,ano);
				    	
				    	sc4.close();
                        sc5.close();
                        sc6.close();
                        sc7.close();

						System.out.println("Index do cliente: ");
				    	Scanner sc8 = new Scanner(System.in);
				    	int idx = sc8.nextInt();
				    	
				    	if(s.clientes().size() > idx) {
				    		s.index_cliente(idx).add_veiculo(v);
				    	}
				    	else {
				    		System.out.println("Index inválido");
				    	}
				    	
				    	
				    	sc8.close();
					}
					else {
						System.out.println("Não há seguradora");
					}
					
				}
				if(x == 3) {
					//Cadastrar seguradora
					
					System.out.println("Nome: ");
			    	Scanner sc4 = new Scanner(System.in);
			    	String nome = sc4.next();
			    	
			    	System.out.println("Telefone: ");
			    	Scanner sc5 = new Scanner(System.in);
			    	String telefone = sc5.next();
			    	
			    	System.out.println("Email: ");
			    	Scanner sc6 = new Scanner(System.in);
			    	String email = sc6.next();
			    	
			    	System.out.println("Endereço: ");
			    	Scanner sc7 = new Scanner(System.in);
			    	String end = sc4.next();
			    	
			    	s = new Seguradora(nome,telefone,email,end);
			    	
			    	sc4.close();
                    sc5.close();
                    sc6.close();
                    sc7.close();
					
				}
			
			}
		}
		if(x == op2.getOperacao()) {
			
			System.out.println("Lista: ");
			Scanner sc2 = new Scanner(System.in);
			int x1 = sc2.nextInt();
			
			while(true) {
				
				if(x1 == 6){
					break;
				}
				if(x1 == 1) {
					//Listar clientes PF/PJ
					System.out.println("Tipo do cliente a ser listado: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	String tipo = sc3.next();
			    	
			    	if(tipo.equals("PF")){
			    		System.out.println(s.listarClientes("PF"));
			    	}
			    	if(tipo.equals("PJ")){
			    		System.out.println(s.listarClientes("PF"));
			    	}
				}
				if(x1 == 2){
					//Listar sinistros por seguradora
					System.out.println(s.listarSinistros());
				}
				if(x1 == 3) {
					//Listar sinistro por cliente
					System.out.println("Index do cliente: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	int idx = sc3.nextInt();
			    	
			    	for(int i = 0;i < s.listarSinistros().size();i++) {
			    		if(s.listarSinistros().get(i).getCliente() == s.index_cliente(idx)){
			    			System.out.println(s.listarSinistros().get(i));
			    		}
			    	}
					
				}
				if(x1 == 4) {
					//Listar veiculos por clientes
					System.out.println("Index do cliente: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	int idx = sc3.nextInt();
			    	
			    	System.out.println(s.index_cliente(idx).get_veiculos());
					
				}
				if(x1 == 5) {
					//Listar veiculos por seguradora
					for(int i = 0;i < s.clientes().size();i++) {
						System.out.println(s.index_cliente(i).get_veiculos());
					}
				}
				}
				
			}
		if(x == op3.getOperacao()) {
			//EXCLUIR
			System.out.println("Excluir: ");
			Scanner sc2 = new Scanner(System.in);
			int x1 = sc2.nextInt();
			
			while(true) {
				if(x1 == 4) {
					break;
				}
				if(x1 == 1) {
					//Excluir cliente
					
					System.out.println("Index do cliente: ");
					Scanner sc3 = new Scanner(System.in);
					int idx = sc3.nextInt();
					
					s.removerCliente(s.index_cliente(idx).get_nome());
					
				}
				if(x1 == 2) {
					//Excluir veiculo
					
					System.out.println("Index do cliente: ");
					Scanner sc3 = new Scanner(System.in);
					int idx_c = sc3.nextInt();
					
					System.out.println("Index do veiculo: ");
					Scanner sc4 = new Scanner(System.in);
					int idx_v = sc4.nextInt();
					
					s.index_cliente(idx_c).remover_veiculo(idx_v);
				}
				if(x1 == 3){
					//Excluir Sinistro
					
					System.out.println("Index do cliente: ");
					Scanner sc3 = new Scanner(System.in);
					int idx = sc3.nextInt();
					
					for(int i = 0;i < s.listarSinistros().size();i++){
						if(s.listarSinistros().get(i).getCliente() == s.index_cliente(idx)) {
							s.remover_sinistro(s.listarSinistros().get(i));
						}
					}
					
				}
			}
		}
		if(x == op4.getOperacao()) {
			//Gerar Sinistro
			
            if(s.get_inst() == 1) {
            	//Há seguradora
            	
    	    	
    	    	System.out.println("Data: ");
    	    	Scanner sc3 = new Scanner(System.in);
    	    	String data = sc3.next();
    	    
    	    	
    	    	System.out.println("Endereço: ");
    	    	Scanner sc4 = new Scanner(System.in);
    	    	String end = sc4.next();
    	    	
    	    	System.out.println("Index veiculo: ");
    	    	Scanner sc5 = new Scanner(System.in);
    	    	int idx_v = sc5.nextInt();
    	    	
    	    	System.out.println("Index cliente: ");
    	    	Scanner sc6 = new Scanner(System.in);
    	    	int idx_c = sc6.nextInt();
    	    	
            	s.gerarSinistro(data, end,s.index_cliente(idx_c).get_veiculo(idx_v),s.index_cliente(idx_c),s);
            	
            	sc3.close();
                sc4.close();
                sc5.close();
                sc6.close();
            }
            else {
            	System.out.println("Não há seguradora");
            }
		}
		if(x == op5.getOperacao()) {
			//Transferir seguro
			
			System.out.println("Index cliente 1: ");
	    	Scanner sc3 = new Scanner(System.in);
	    	int idx_1 = sc3.nextInt();
	    	
	    	System.out.println("Index cliente 2: ");
	    	Scanner sc4 = new Scanner(System.in);
	    	int idx_2 = sc4.nextInt();
	    	
	    	s.transferir_seguro(s.index_cliente(idx_1),s.index_cliente(idx_2));
			
		}
		if(x == op6.getOperacao()){
			//Calcular receita
			s.calcular_preco_seguro_clientes();//atualizar dados
			System.out.println(s.calcular_receita());
			
		}
			
		}
		
	}

}
	

