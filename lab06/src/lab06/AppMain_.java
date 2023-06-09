package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppMain_ {

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
		
		
		/*
		 * As classes do tipo Gerador_*,serao classes que implemenam I_Arquivo e serao responsaveis por pegar um file csv
		 * e instanciar os objetos,as classes Gravar_* sao as classes que vao gerar os arquivos com os dados dos seguros e sinistros
		 */
		
		//1)Lendo os arquivos
		
		String pf = "src\\clientesPF.csv";
		Gerador_PF ger1 = new Gerador_PF(pf);
		ger1.ler_arquivo();
		//System.out.println(ger1.get_clientes());
		
		String pj = "src\\clientesPJ.csv";
		Gerador_PJ ger2 = new Gerador_PJ(pj);
		ger2.ler_arquivo();
		//System.out.println(ger2.get_clientes());
		
		String conds = "src\\condutores.csv";
		Gerador_Condutor ger3 = new Gerador_Condutor(conds);
		ger3.ler_arquivo();
		//System.out.println(ger3.get_condutores());
		
		String vcs = "src\\veiculos.csv";
		Gerador_Veiculos ger4 = new Gerador_Veiculos(vcs); 
		ger4.ler_arquivo();
		//System.out.println(ger4.get_veiculos());
		
		String frts = "src\\veiculos.csv";
		Gerador_Frota ger5 = new Gerador_Frota(frts);
		ger5.ler_arquivo();
		//System.out.println(ger5.get_placas());
		
		//1)
		
		//2)Adicionando os veiculos e frotas nos respectivos clientes
		
		//Veiculo em Clientes_PF:
		ArrayList<Cliente_PF> clientes_pf = ger1.get_clientes();
		ArrayList<String> placas = ger1.get_placas();
		ArrayList<Veiculo> veiculos = ger4.get_veiculos();
		for(int i = 0;i < placas.size();i++){
			String placa = placas.get(i);
			for(int j = 0;j < veiculos.size();j++) {
				Veiculo v = veiculos.get(j);
				if(v.get_placa().equals(placa)){
					clientes_pf.get(i).cadastrar_veiculo(v);
				}
			}
		}
		
		//Veiculos em Frotas:
		ArrayList<Frota> frotas = ger5.get_frotas();
		placas = ger5.get_placas();
		
		for(int i = 0;i < placas.size();i++) {
			
			String placa = placas.get(i);
			for(int j = 0;j < veiculos.size();j++){
				Veiculo v = veiculos.get(j);
				if(v.get_placa().equals(placa)){
					frotas.get(i).add_veiculo(v);
				}
			}
		}
		
		//Frotas em Clientes_PJ
		
		ArrayList<Cliente_PJ> clientes_pj = ger2.get_clientes();
		ArrayList<String> id_frota =  ger2.get_id();
		for(int i = 0;i < frotas.size();i++){
			Frota f = frotas.get(i);
			for(int j = 0;j < id_frota.size();j++){
				String id = id_frota.get(j);
				if(f.get_code().equals(id)){
					clientes_pj.get(i).cadastrar_frota(f);
				}
			}
		}
		
		//2)
		
		//3)Criando seguros para os clientes e sinistros para os condutores
		
		//1 Seguro_PF e 1 Seguro_PJ,cada um tera 1 sinistro 
		ArrayList<Condutor> condutores = ger3.get_condutores();
		Cliente_PF pf_ex = clientes_pf.get(1);
		Cliente_PJ pj_ex = clientes_pj.get(1);
		Veiculo v_ex = veiculos.get(1);
		Frota f_ex = frotas.get(1);
		Condutor ex_1 = condutores.get(1);
		Condutor ex_2 = condutores.get(2);
		Condutor ex_3 = condutores.get(3);
		
		Date atual = new Date("09","06","2023");
		Seguradora seguradora = new Seguradora("54.812.000.0001-15","Pedro Seguros","telefone","email","endereco");
		
		seguradora.gerar_seguro(pf_ex, v_ex, new Date("2023-07-01"), new Date("2024-07-01"), atual);
		seguradora.gerar_seguro(pj_ex, f_ex, new Date("2023-08-07"), new Date("2026-03-11"), atual);
		
		Seguro_PF seg_pf = seguradora.get_seguros_cliente(pf_ex).get(0);
		Seguro_PJ seg_pj = seguradora.get_seguros_cliente(pj_ex).get(0);
		seg_pf.autorizarCondutor(ex_1);
		seg_pj.autorizarCondutor(ex_1);
		seg_pj.autorizarCondutor(ex_2);
		seg_pj.autorizarCondutor(ex_3);
		
		seg_pf.gerarSinistro(atual, "Endereco", ex_1);
		seg_pj.gerarSinistro(atual, "Endereco", ex_2);
		
		Sinistro sin1 = seg_pf.get_listaSinistros().get(0);
		Sinistro sin2 = seg_pj.get_listaSinistros().get(0);
		
		//3)
		
		//4)Gerando os arquivos csv com os dados dos Seguros e Sinistros	
		
		ArrayList<Seguro> seguros = new ArrayList<>();
		seguros.add(seg_pj);
		seguros.add(seg_pf);
		
		String file1 = "src\\Seguros1.csv";
		Gravar_Seguro grav1 = new Gravar_Seguro(file1,seguros);
		grav1.gravar_arquivo();
		
		ArrayList<Sinistro> sinistros = new ArrayList<>();
		sinistros.add(sin1);
		sinistros.add(sin2);
		
		String file2 = "src\\Sinistros1.csv";
		Gravar_Sinistro grav2 = new Gravar_Sinistro(file2,sinistros);
		grav2.gravar_arquivo();
		
		//4)
		
		//5)Exemplos dos metodos
		
		//listar clientes
		System.out.println(seguradora.listar_clientes("PF"));
		System.out.println(seguradora.listar_clientes("PJ"));
				
		//get sinistros por cliente pf
		System.out.println(seguradora.get_sinistro(pf_ex));
		System.out.println(seguradora.get_sinistro(pj_ex));
				
		/*
		 * Os ids podem ser os mesmos,pois os sinsitros estao em seguros distintos.
		 */
				
		//calcular receita
		System.out.println(seguradora.calcular_receita());
		
		
		menu();
		
		//5)


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
