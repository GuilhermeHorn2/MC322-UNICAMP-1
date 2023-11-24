package lab02_;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
        /*Instanciando os objetos e os metodos da classe Cliente*/
		Cliente cliente = new Cliente("","280.012.389−38","",20,"");
		cliente.getNome();
		cliente.getCpf();
		cliente.getDataNascimento();
		cliente.getIdade();
		cliente.getEndereco();
		
		cliente.setNome("nome");
		cliente.setCpf("280.012.389-38");
		cliente.setDataNascimento("DATA");
		cliente.setIdade(20);
		cliente.setEndereco("ENDERECO");
		
		/*Testando o toString;*/
		System.out.println(cliente);
		
		boolean v = cliente.validarCPF(cliente.getCpf());
		
		/*testando um cpf aleatório:*/
		System.out.println("CPF VALIDADO: "+v);
		
		/*Instanciando os objetos e os metodos da classe Seguradora*/
		Seguradora seguradora = new Seguradora("","","","");
		seguradora.getNome();
		seguradora.getTelefone();
		seguradora.getEmail();
		seguradora.getEndereco();
		
		seguradora.setNome("nome");
		seguradora.setNome("telefone");
		seguradora.setNome("email");
		seguradora.setNome("endereco");
		
		/*Instanciando os objetos e os metodos da classe Veiculo*/
		Veiculo veiculo = new Veiculo("","","");
		veiculo.getPlaca();
		veiculo.getMarca();
		veiculo.getModelo();
		
		
		veiculo.setPlaca("placa");
		veiculo.setMarca("marca");
		veiculo.setModelo("modelo");
		
		/*Instanciando os objetos e os metodos da classe Sinistro*/
		
		Sinistro sinistro = new Sinistro("","");
		
		/*ao instanciar um objeto da classe Sinistro um ID único aleatório entre 1-999 é gerado*/
		sinistro.getData();
		sinistro.getEndereco();
		
		/*testando o id gerado*/
		int id = sinistro.getid();
		System.out.println("ID:" + id);
		
		sinistro.setData("data");
		sinistro.setEndereco("endereco");
		/*como o id é algo fixo e gerado automaticamente não vou usar um set*/
	}

}
