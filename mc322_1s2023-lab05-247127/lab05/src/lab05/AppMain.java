package lab05;

import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		
		//data atual:
		
		Date atual = new Date("19","05","2023");
		
		//Seguradora:
		
		Seguradora seguradora = new Seguradora("54.812.000.0001-15","Pedro Seguros","telefone","email","endereco");
		
		//veiculos:
		
		Veiculo v1 = new Veiculo("placa","marca","modelo",2020);
		Veiculo v2 = new Veiculo("placa","marca","modelo",2019);
		
		//instanciando 1 cliente pf
		
		Date nasc_pf = new Date("20","01","1990");
		Cliente_PF pf = new Cliente_PF("504.309.690-07","genero","educacao",nasc_pf,"nome","telefone","endereco","email");
		pf.cadastrar_veiculo(v1);
		validacao val = new validacao("504.309.690-07","","nome");
		if(val.validaCPF(pf.get_cpf())) {
			seguradora.cadastrar_cliente(pf);
		}
		
		Date inicio = atual;
		Date fim = new Date("19","05","2025");
		
		//seguro pf
		seguradora.gerar_seguro(pf, v1, inicio, fim, atual);
		
		Date nasc = new Date("20","01","1991");
		Condutor Pedro_conducoes = new Condutor("845.857.770-40","nome","tel","end","email",nasc);
		
		//gerando 1 sinistro do cliente pf
		
		ArrayList<Seguro_PF> segs_pf = seguradora.get_seguros_cliente(pf);
		for(int i = 0;i < segs_pf.size();i++){
			
			Seguro_PF seg = segs_pf.get(i);
			val = new validacao("845.857.770-40","","nome"); 
			seg.gerarSinistro(atual, "endereco", Pedro_conducoes);
			seg.set_valorMensal(seg.calcularValor(atual));
			
		}
		
		//instanciando 1 cliente pj
		
		Date fund = new Date("12","02","2001");
		Cliente_PJ pj = new Cliente_PJ("62.045.674.0001-60",fund,"nome","telefone","end","email");
		val = new validacao("","62.045.674.0001-60","nome");
		if(val.validaCNPJ("62.045.674.0001-60") && val.validaNome("nome")){
			seguradora.cadastrar_cliente(pj);
		}
		
		Frota f = new Frota("code");
		f.add_veiculo(v1);
		f.add_veiculo(v2);
		
		//gerando seguro pj
		seguradora.gerar_seguro(pj, f, inicio, fim, atual);
		
		
		//gerando 1 sinistro pj
		
		ArrayList<Seguro_PJ> segs_pj = seguradora.get_seguros_cliente(pj);
		for(int i = 0;i < segs_pf.size();i++){
			
			Seguro_PJ seg = segs_pj.get(i);
			val = new validacao("845.857.770-40","","nome"); 
			seg.gerarSinistro(atual, "endereco", Pedro_conducoes);
			seg.set_valorMensal(seg.calcularValor(atual));
		}
		
		
		
		//listar clientes
		System.out.println(seguradora.listar_clientes("PF"));
		System.out.println(seguradora.listar_clientes("PJ"));
		
		//get sinistros por cliente pf
		System.out.println(seguradora.get_sinistro(pf));
		System.out.println(seguradora.get_sinistro(pj));
		
		/*
		 * Os ids podem ser os mesmos,pois os sinsitros estao em seguros distintos.
		 */
		
		//calcular receita
		System.out.println(seguradora.calcular_receita());
		
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
			
			Seguradora s = new Seguradora("54.812.000.0001-15");
			
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
					    	
					    	System.out.println("Telefone: ");
					    	Scanner sc12 = new Scanner(System.in);
					    	String tel = sc12.next();
					    	
					    	System.out.println("email: ");
					    	Scanner sc13 = new Scanner(System.in);
					    	String email = sc13.next();
					
					    	
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
						    	
						    	System.out.println("Data Nascimento: ");
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
						    	
						    	Cliente_PF c = new Cliente_PF(cpf,genero,edu,data,nome,tel,end,email);
						    	s.cadastrar_cliente(c);
						    	
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
						    	
						    	/*System.out.println("Quantidade de funcionários: ");
						    	Scanner sc6 = new Scanner(System.in);
						    	int qtde = sc6.nextInt();*/
						    	
						    	Cliente_PJ c = new Cliente_PJ(cnpj,data,nome,tel,end,email);
						    	s.cadastrar_cliente(c);
						    	
						    	sc4.close();
						    	sc5.close();
						    	
						    	
						    }
						    
					}
						else {
							System.out.println("Não há seguradora");
						}
					
				}
				if(x1 == 2) {
					//Cadastrar Veiculo v no cliente de documento doc,precisa haver uma seguradora cadastrada antes
					
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
				    	
				    	System.out.println("documento do cliente: ");
				    	Scanner sc8 = new Scanner(System.in);
				    	String doc  = sc8.next();
				    	
				    	System.out.println("Tipo de cliente(PJ/PF): ");
				    	Scanner sc9 = new Scanner(System.in);
				    	String tipo = sc9.next();
				    	
				    	Veiculo v = new Veiculo(placa,marca,modelo,ano);
				    	
				    	sc4.close();
                        sc5.close();
                        sc6.close();
                        sc7.close();
                        
                        if(tipo.equals("PF")){
                        	
                        	ArrayList<Cliente> l = s.listar_clientes("PF");
                        	
                        	for(int i = 0;i < l.size();i++) {
                        		
                        		Cliente_PF c = (Cliente_PF) l.get(i);
                        		if(c.get_cpf().equals(doc)) {
                        			c.cadastrar_veiculo(v);
                        		}
                        		
                        	}	
                        }
                        
                        if(tipo.equals("PJ")){
                        	
                        	System.out.println("code da frota: ");
    				    	Scanner sc10 = new Scanner(System.in);
    				    	String code  = sc10.next();
                        	
                        	ArrayList<Cliente> l = s.listar_clientes("PJ");
                        	
                        	for(int i = 0;i < l.size();i++) {
                        		
                        		Cliente_PJ c = (Cliente_PJ) l.get(i);
                        		if(c.get_cnpj().equals(doc)) {
                        			for(int j = 0;j < c.get_lista_frota().size();j++) {
                        				if(c.get_lista_frota().get(j).get_code().equals(code)){
                        					c.get_lista_frota().get(j).add_veiculo(v);
                        				}
                        				
                        				
                        			}
                        		}
                        		
                        	}	
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
			    	
			    	s = new Seguradora("54.812.000.0001-15",nome,telefone,email,end);
			    	
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
			    		System.out.println(s.listar_clientes("PF"));
			    	}
			    	if(tipo.equals("PJ")){
			    		System.out.println(s.listar_clientes("PF"));
			    	}
				}
				if(x1 == 2){
					//Listar sinistros por seguradora
					ArrayList<Cliente> l = s.listar_clientes();
					for(int i = 0;i < l.size();i++) {
						if(l.get(i).get_tipo() == 1) {
							Cliente_PJ c = (Cliente_PJ) l.get(i);
							System.out.println(s.get_sinistro(c));
						}
						if(l.get(i).get_tipo() == 0) {
							Cliente_PF c = (Cliente_PF) l.get(i);
							System.out.println(s.get_sinistro(c));
						}
					}
				}
				if(x1 == 3) {
					//Listar sinistro por cliente
					System.out.println("Documento do cliente: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	String doc = sc3.next();
			    	System.out.println("Tipo do cliente: ");
			    	Scanner sc4 = new Scanner(System.in);
			    	String tipo = sc3.next();
			    	
			    	if(tipo.equals("PF")) {
			    		for(int i = 0;i < s.listar_clientes("PF").size();i++) {
			    			Cliente_PF c = (Cliente_PF) s.listar_clientes("PF").get(i);
			    			if(c.get_cpf().equals(doc)) {
			    				System.out.println(s.get_sinistro(c));
			    			}
			    		}
			    	}
			    	if(tipo.equals("PJ")) {
			    		for(int i = 0;i < s.listar_clientes("PJ").size();i++) {
			    			Cliente_PJ c = (Cliente_PJ) s.listar_clientes("PJ").get(i);
			    			if(c.get_cnpj().equals(doc)) {
			    				System.out.println(s.get_sinistro(c));
			    			}
			    		}
			    	}
			    	
			    	
					
				}
				if(x1 == 4) {
					//Listar veiculos por clientes
					System.out.println("Documento do cliente: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	String doc = sc3.next();
			    	System.out.println("Tipo do cliente: ");
			    	Scanner sc4 = new Scanner(System.in);
			    	String tipo = sc3.next();
			    	
			    	if(tipo.equals("PF")) {
			    		for(int i = 0;i < s.listar_clientes("PF").size();i++) {
			    			Cliente_PF c = (Cliente_PF) s.listar_clientes("PF").get(i);
			    			if(c.get_cpf().equals(doc)) {
			    				System.out.println(c.get_lista_veiculos());
			    			}
			    		}
			    	}
			    	if(tipo.equals("PJ")) {
			    		for(int i = 0;i < s.listar_clientes("PJ").size();i++) {
			    			Cliente_PJ c = (Cliente_PJ) s.listar_clientes("PJ").get(i);
			    			if(c.get_cnpj().equals(doc)) {
			    				for(int j = 0;j < c.get_lista_frota().size();j++) {
			    					System.out.println(c.get_veiculos_por_frota(c.get_lista_frota().get(j)));
			    				}
			    			}
			    		}
			    	}
					
				}
				if(x1 == 5) {
					//Listar veiculos por seguradora
					for(int i = 0;i < s.listar_clientes().size();i++) {
						
						if(s.listar_clientes().get(i).get_tipo() == 1) {
							Cliente_PJ c = (Cliente_PJ) s.listar_clientes().get(i);
							for(int j = 0;j < c.get_lista_frota().size();j++) {
		    					System.out.println(c.get_veiculos_por_frota(c.get_lista_frota().get(j)));
		    				}
						}
						else {
							Cliente_PF c = (Cliente_PF) s.listar_clientes().get(i);			    			
			    			System.out.println(c.get_lista_veiculos());
			    			
						}
						
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
					
					System.out.println("Documento do cliente: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	String doc = sc3.next();
			    	System.out.println("Tipo do cliente: ");
			    	Scanner sc4 = new Scanner(System.in);
			    	String tipo = sc3.next();
			    	
			    	if(tipo.equals("PF")) {
			    		for(int i = 0;i < s.listar_clientes("PF").size();i++) {
			    			Cliente_PF c = (Cliente_PF) s.listar_clientes("PF").get(i);	
			    			if(c.get_cpf().equals(doc)) {
			    				s.remover_cliente(c);
			    			}
			    		}
			    	}
			    	if(tipo.equals("PJ")) {
			    		for(int i = 0;i < s.listar_clientes("PJ").size();i++) {
			    			Cliente_PJ c = (Cliente_PJ) s.listar_clientes("PJ").get(i);	
			    			if(c.get_cnpj().equals(doc)) {
			    				s.remover_cliente(c);
			    			}
			    		}
			    	}
					
				}
				if(x1 == 2) {
					//Excluir veiculo
					System.out.println("Placa do veiculo: ");
			    	Scanner sc5 = new Scanner(System.in);
			    	String placa = sc5.next();
					System.out.println("Documento do cliente: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	String doc = sc3.next();
			    	System.out.println("Tipo do cliente: ");
			    	Scanner sc4 = new Scanner(System.in);
			    	String tipo = sc3.next();
			    	
			    	if(tipo.equals("PF")) {
			    		for(int i = 0;i < s.listar_clientes("PF").size();i++) {
			    			Cliente_PF c = (Cliente_PF) s.listar_clientes("PF").get(i);	
			    			if(c.get_cpf().equals(doc)) {
			    				for(int j = 0;j < c.get_lista_veiculos().size();j++) {
			    					if(c.get_lista_veiculos().get(j).get_placa().equals(placa)) {
			    						c.remover_veiculo(c.get_lista_veiculos().get(j));
			    					}
			    				}
			    			}
			    		}
			    	}
			    	if(tipo.equals("PJ")) {
			    		for(int i = 0;i < s.listar_clientes("PJ").size();i++) {
			    			Cliente_PJ c = (Cliente_PJ) s.listar_clientes("PJ").get(i);	
			    			if(c.get_cnpj().equals(doc)) {
			    				for(int j = 0;j < c.get_lista_frota().size();j++) {
			    					for(int k = 0;k < c.get_veiculos_por_frota(c.get_lista_frota().get(j)).size();k++) {
			    						if(c.get_veiculos_por_frota(c.get_lista_frota().get(j)).get(k).get_placa().equals(placa)) 
			    						{
			    							c.atualizar_frota(c.get_lista_frota().get(j), c.get_veiculos_por_frota(c.get_lista_frota().get(j)).get(k));
			    						}
			    					}
			    				}
			    			}
			    		}
			    	}
				}
				/*if(x1 == 3){
					//Excluir Sinistro
					
					System.out.println("Documento do cliente: ");
			    	Scanner sc3 = new Scanner(System.in);
			    	String doc = sc3.next();
			    	System.out.println("Tipo do cliente: ");
			    	Scanner sc4 = new Scanner(System.in);
			    	String tipo = sc3.next();
			    	System.out.println("id: ");
			    	Scanner sc5 = new Scanner(System.in);
			    	int id = sc5.nextInt();
			    	
			    	if(tipo.equals("PF")) {
			    		for(int i = 0;i < s.listar_clientes("PF").size();i++) {
			    			Cliente_PF c = (Cliente_PF) s.listar_clientes("PF").get(i);	
			    			if(c.get_cpf().equals(doc)) {
			    				s.remover_cliente(c);
			    			}
			    		}
			    	}
			    	if(tipo.equals("PJ")) {
			    		for(int i = 0;i < s.listar_clientes("PJ").size();i++) {
			    			Cliente_PJ c = (Cliente_PJ) s.listar_clientes("PJ").get(i);	
			    			if(c.get_cnpj().equals(doc)) {
			    				s.remover_cliente(c);
			    			}
			    		}
			    	}
					
				}*/
			}
		}
		if(x == op4.getOperacao()) {
			//Gerar Sinistro
			
            if(s.get_inst() == 1) {
            	//Há seguradora
            	
    	    	
            	System.out.println("Data: ");
		    	Scanner sc5 = new Scanner(System.in);
		    	String data_str = sc5.next();
		    	//data_str:xx/xx/xxxx
		    	Date data = new Date(data_str.substring(0, 1),data_str.substring(3,4),data_str.substring(6,9));
    	    
    	    	
    	    	System.out.println("Endereço: ");
    	    	Scanner sc4 = new Scanner(System.in);
    	    	String end = sc4.next();
    	    	
    	    	
    	    	
    	    	System.out.println("Documento do cliente: ");
		    	Scanner sc6 = new Scanner(System.in);
		    	String doc = sc6.next();
		    	System.out.println("Tipo do cliente: ");
		    	Scanner sc7 = new Scanner(System.in);
		    	String tipo = sc7.next();
		    	System.out.println("id do seguro: ");
		    	Scanner sc8 = new Scanner(System.in);
		    	int id = sc8.nextInt();
		    	System.out.println("Documento do condutor: ");
		    	Scanner sc9 = new Scanner(System.in);
		    	String doc2 = sc9.next();
		    	
		    	if(tipo.equals("PF")) {
		    		for(int i = 0;i < s.listar_clientes("PF").size();i++) {
		    			Cliente_PF c = (Cliente_PF) s.listar_clientes("PF").get(i);	
		    			if(c.get_cpf().equals(doc)) {
		    				ArrayList<Seguro_PF> seguros = s.get_seguros_cliente(c);
		    				for(int j = 0;j < seguros.size();j++) {
		    					if(seguros.get(i).get_id() == id) {
		    						ArrayList<Condutor> condutores  = seguros.get(i).get_listaCondutores();
		    						for(int k = 0;k < condutores.size();k++) {
		    							if(condutores.get(k).get_cpf().equals(doc2)){
		    								seguros.get(i).gerarSinistro(data, end, condutores.get(k));
		    							}
		    						}
		    					}
		    				}
		    			}
		    		}
		    	}
		    	if(tipo.equals("PJ")) {
		    		for(int i = 0;i < s.listar_clientes("PJ").size();i++) {
		    			Cliente_PJ c = (Cliente_PJ) s.listar_clientes("PJ").get(i);	
		    			if(c.get_cnpj().equals(doc)) {
		    				ArrayList<Seguro_PJ> seguros = s.get_seguros_cliente(c);
		    				for(int j = 0;j < seguros.size();j++) {
		    					if(seguros.get(i).get_id() == id) {
		    						ArrayList<Condutor> condutores  = seguros.get(i).get_listaCondutores();
		    						for(int k = 0;k < condutores.size();k++) {
		    							if(condutores.get(k).get_cpf().equals(doc2)){
		    								seguros.get(i).gerarSinistro(data, end, condutores.get(k));
		    							}
		    						}
		    					}
		    				}
		    			}
		    		}
		    	}
            }
            else {
            	System.out.println("Não há seguradora");
            }
		}
		/*if(x == op5.getOperacao()) {
			//Transferir seguro
			
			System.out.println("Index cliente 1: ");
	    	Scanner sc3 = new Scanner(System.in);
	    	int idx_1 = sc3.nextInt();
	    	
	    	System.out.println("Index cliente 2: ");
	    	Scanner sc4 = new Scanner(System.in);
	    	int idx_2 = sc4.nextInt();
	    	
	    	s.transferir_seguro(s.index_cliente(idx_1),s.index_cliente(idx_2));
			
		}*/
		if(x == op6.getOperacao()){
			//Calcular receita
			s.calcular_receita();//atualizar dados
			System.out.println(s.calcular_receita());
			
		}
			
		}
		
	}

}
