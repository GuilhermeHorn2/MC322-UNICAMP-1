package lab03_;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Instanciando a Seguradora*/
		Seguradora s = new Seguradora("nome","telefone","email","endereco");
		
		/*Cadastrar e remover 1 cliente,com 1 veiculo*/		
		
		Veiculo v = new Veiculo("placa","marca","modelo",2019);
		
		Date dataLicensa = new Date("00","00","0000");
		Date dataNascimento = new Date("00","00","0000");
		ClientePF c =  new ClientePF("nome","endereco",dataLicensa,"educacao","genero","classe",v,"084.688.310-42",dataNascimento);
		
		/*Cadastrar*/
		
		s.cadastrarCliente(c);
		System.out.println(s.listarClientes("ClientePF"));
		
		/*Remover*/
		
		s.removerCliente(c.getNome());
		System.out.println(s.listarClientes("ClientePF"));
		
		/*Cadastrar 1 clientePF e 1 clientePJ na seguradora e chamar os metodos validarcpf e validarcnpj*/
		
		Date dataLicensa1 = new Date("00","00","0000");
		Date dataFundacao = new Date("00","00","0000");
		ClientePJ c1 =  new ClientePJ("nome","endereco",dataLicensa1,"educacao","genero","classe",v,"14.572.457.0001-85",dataFundacao);
		
		Date dataLicensa2 = new Date("00","00","0000");
		Date dataNascimento2 = new Date("00","00","0000");
		ClientePF c2 =  new ClientePF("nome","endereco",dataLicensa2,"educacao","genero","classe",v,"084.688.310-42",dataNascimento2);
		
		s.cadastrarCliente(c1);
		s.cadastrarCliente(c2);
		System.out.println(s.listarClientes(""));
		
		/*validar cpf*/
		
		System.out.println(c1.validar_cnpj(c1.getCnpj()));
		
		/*validar cnpj*/
		
		System.out.println(c2.validarCPF(c2.getCpf()));
		
		/*Gerear 1 Sinistro e visualizar*/
		
		Date data = new Date("00","00","0000");
		
		//Vou usar o veiculo já instanciado e o Cliente c2
		
		s.gerarSinistro(data.getDate(),"endereco", v, c2,s);
		s.visualizarSinistro(c2);
		System.out.println(s.listarSinistros());
		
		/*metodo testar_veiculo*/
		
		testar_veiculo();
		
		
    
    
	}
	private static void testar_veiculo() {
		
		System.out.println("PLACA: ");
		Scanner sc1 = new Scanner(System.in);
		String placa = sc1.next();
		

		System.out.println("MARCA: ");
		Scanner sc2 = new Scanner(System.in);
		String marca = sc2.next();
		
		
		System.out.println("MODELO: ");
		Scanner sc3 = new Scanner(System.in);
		String modelo = sc3.next();
		
		
		System.out.println("ANO: ");
		Scanner sc4 = new Scanner(System.in);
		int ano = sc4.nextInt();
		
		
		Veiculo v = new Veiculo(placa,marca,modelo,ano);
		
		System.out.println(v);
		
		
	}
	
	}


