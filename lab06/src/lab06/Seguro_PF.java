package lab06;

public class Seguro_PF extends Seguro {
	
	private Veiculo _veiculo;
	private Cliente_PF _cliente;
	

	public Seguro_PF(Veiculo veiculo,Cliente_PF cliente,int id, Date inicio, Date fim, Seguradora seg) {
		super(id, inicio, fim, seg);
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
	
	
	@Override
	public boolean autorizarCondutor(Condutor c) {
		listaCondutores.add(c);
		return true;
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
	


	@Override
	public double calcularValor(Date atual) {
		//atual é a data do momento em que o valor é calculado
		int idade = calc_idade(atual,_cliente.get_dataNasc());
		int qnt_veiculos = _cliente.get_lista_veiculos().size();
		int qnt_sinistros_cliente = listaSinistros.size();
		int qnt_sinistro_condutor = 0;
		
		//Qnt de sinistros para os condutores registrados no seguro
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
	public void gerarSinistro(Date data,String endereco,Condutor condutor){
		int id = listaSinistros.size();
		Sinistro novo = new Sinistro(id,data,endereco,condutor,this);
		listaSinistros.add(novo);
		condutor.get_listaSinistros().add(novo);
		
	}
	
	public String toString(){
		
		StringBuilder res = new StringBuilder();
		res.append("id: " + this.get_id());
		res.append("/data incio: " + this.get_datainicio());
		res.append("/data fim: " + this.get_datafim());
		res.append("/Seguradora: " + this.get_seguradora());
		res.append("/Veiculo: " + this.get_veiculo());
		res.append("/cliente:" + this.get_cliente());
		return res.toString();
	}

	@Override
	public int get_type() {
		// PF --> 1
		return 1;
	}

}
