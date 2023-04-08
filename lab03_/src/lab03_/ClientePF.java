package lab03_;

public class ClientePF extends Cliente {

	private String _cpf;//cpf final
	private Date _dataNascimento;

	
	public ClientePF(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, Veiculo v,String cpf,Date dataNascimento) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica, v);
		_cpf = cpf;
		_dataNascimento = dataNascimento;
		
	}
	
	/*gets*/
	
	public String getCpf() {
		return _cpf;
	}
	
	public String getDataNascimento() {
		return _dataNascimento.getDate();
	}
	
	/*sets*/
	
	public void setCpf(String cpf) {
		//vai que a pessoa digitou errado
		_cpf = cpf;
	}
	
	public void setDataNascimento(String Date) {
		//vai que a pessoa digitou errado
		_dataNascimento.setDate(Date);
	}
	
	/*Validar Cpf*/
	
	public boolean validarCPF(String cpf) {
		
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
	
	
	/*toString*/
	
	public String toString() {
		return "{Cpf: " + _cpf + "/ Data de Nascimento: " + _dataNascimento + "}";
	}
	
	


	

}
