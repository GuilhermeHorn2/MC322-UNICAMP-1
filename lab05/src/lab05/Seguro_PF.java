package lab05;

public class Seguro_PF extends Seguro {
	
	private Veiculo _veiculo;
	private Cliente_PF _cliente;
	

	public Seguro_PF(Veiculo veiculo,Cliente_PF cliente,int id, Date inicio, Date fim, Seguradora seg, int valor) {
		super(id, inicio, fim, seg, valor);
		_veiculo = veiculo;
		_cliente = cliente;
	}
	
	public Veiculo get_veiculo(){
		return _veiculo;
	}
	public Cliente_PF get_cliente(){
		return _cliente;
	}
	
	public void set_veiculo(Veiculo v) {
		_veiculo = v;
	}
	public void set_cliente(Cliente_PF c) {
		_cliente = c;
	}
	
	public boolean check_cpf(String cpf) {
		
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
	
	@Override
	public boolean autorizarCondutor(Condutor c) {
		boolean aut = false;
		if(this.check_cpf(c.get_cpf())){
			//talvez validar o telefone tbm,mas o nome n sei,o Elon Musk colocou o nome do filho com numeros lá,vai saber
			listaCondutores.add(c);
			aut = true;
		}
		return aut;
	}

	@Override
	public boolean desautorizarCondutor(Condutor c) {
		boolean des = false;
		
		//Vendo se esse condutor está cadastrado mesmo
		for(int i = 0;i < listaCondutores.size();i++) {
			if(listaCondutores.get(i) == c) {
				des = true;
				listaCondutores.remove(i);
			}
		}
		return false;
	}
	
	public int calc_idade(Date data,Date Nasc) {
		//recebe a data atual e a data de nascimento
		
		int anos = data.get_ano() - Nasc.get_ano();
		
		
		if(data.get_mes() < Nasc.get_mes()) {
			return anos-1;
		}
		if(data.get_mes() == Nasc.get_mes()) {
			if(data.get_dia() < Nasc.get_dia()) {
				return anos-1;
			}
		}
		return anos;
		
	}

	@Override
	protected double calcularValor(Date atual) {
		
		int idade = calc_idade(atual,_cliente.get_dataNasc());
		int qnt_veiculos = _cliente.get_lista_veiculos().size();
		int qnt_sinistros_cliente = listaSinistros.size();
		int qnt_sinistro_condutor = 0;
		
		//Qnt de sinistros para os condutores registrados no segurop
		for(int i = 0;i < listaCondutores.size();i++) {
			Condutor c = listaCondutores.get(i);
			qnt_sinistro_condutor += c.get_listaSinistros().size();
		}
		
		double fator = 0;
		if(idade >= 18 && idade < 30) {
			fator = 1.25;
		}
		if(idade >= 30 && idade < 60) {
			fator = 1.0;
		}
		else if(idade >= 60) {
			fator = 1.5;
		}
		return 10*fator*(1/qnt_veiculos+2)*(2+(qnt_sinistros_cliente/10)) * (5 + (qnt_sinistro_condutor)/10);
		
		
	}

	@Override
	public void gerarSinistro() {
		// TODO Auto-generated method stub
		
	}

}
