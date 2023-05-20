package lab05;

public class Seguro_PJ extends Seguro{
	
	Frota frota;
	Cliente_PJ cliente;

	public Seguro_PJ(int id, Date inicio, Date fim, Seguradora seg,Frota f,Cliente_PJ c) {
		super(id, inicio, fim, seg);
		frota = f;
		cliente = c;
	}
	
	public Frota get_frota() {
		return frota;
	}
	
	public Cliente_PJ get_cliente() {
		return cliente;
	}

	@Override
	public boolean autorizarCondutor(Condutor c) {
		
		listaCondutores.add(c);
		return true;
	}

	@Override
	public boolean desautorizarCondutor(Condutor c) {
		for(int i = 0;i < listaCondutores.size();i++){
			if(listaCondutores.get(i) == c) {
				listaCondutores.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public double calcularValor(Date atual) {
		int AnosPosFundacao = atual.get_ano() - cliente.get_data_fundacao().get_ano();
		int base = 10*(10+(listaCondutores.size())/10);
		int qnt_veiculos = 0;
		for(int i = 0;i < cliente.get_lista_frota().size();i++){
			qnt_veiculos += cliente.get_lista_frota().size();
		}
		int qnt_sinistros_cliente = listaSinistros.size();
		int qnt_sinistros_condutores = 0;
		for(int i = 0;i < listaCondutores.size();i++) {
			qnt_sinistros_condutores += listaCondutores.get(i).get_listaSinistros().size();
		}
		
		double val = base*(1+1/(qnt_veiculos+2))*(1+(1/AnosPosFundacao+2))*(2+(qnt_sinistros_cliente/10))*(5 + (qnt_sinistros_condutores/10));
		
		//this.set_valorMensal(val);
		
		return val;
	
	}

	@Override
	public void gerarSinistro(Date data,String endereco,Condutor condutor) {
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
		res.append("/Frota: " + this.get_frota());
		res.append("/cliente:" + this.get_cliente());
		return res.toString();
	}

	@Override
	public int get_type() {
		//PJ --> 0
		return 0;
	}

}
