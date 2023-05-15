package lab05;

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
	private static int num_seg = 0;
	private static HashMap<Seguro,Integer> idx_seg = new HashMap<>();
	
	
	public Seguradora(String cnpj,String nome,String telefone,String email,String endereco) {
		_cnpj = cnpj;
		_nome = nome;
		_telefone = telefone;
		_email = email;
		_endereco = endereco;
	}
	
	public String get_cnpj(){
		return _cnpj;
	}
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
	
	/*public boolean gerar_seguro(String tipo,Date inicio,Date fim,int valor){
		
		boolean gerou = false;
		
		if(tipo.equals("PF")) {
			
			int id = num_seg;
			Seguro_PF s = new Seguro_PF();
			
		}
		
		
	}*/
	
	
	
}
