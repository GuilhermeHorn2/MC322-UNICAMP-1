package lab05;

import java.util.ArrayList;

public class Cliente_PJ extends Cliente{

	private final String _cnpj;
	private Date data_fundacao;
	private static ArrayList<Frota> lista_frota = new ArrayList<>();
	
	public Cliente_PJ(String cnpj,Date data,String nome, String telefone, String endereco, String email) {
		super(nome, telefone, endereco, email);
		_cnpj = cnpj;
		data_fundacao = data;
	}
	
	public String get_cnpj() {
		return _cnpj;
	}
	public Date get_data_fundacao() {
		return data_fundacao;
	}
	public ArrayList<Frota> get_lista_frota(){
		return lista_frota;
	}
	
	public void set_data_fundacao(Date data) {
		data_fundacao = data;
	}
	
	public boolean cadastrar_frota(Frota f) {
		lista_frota.add(f);
		return true;
	}
	
	//atualizar_frota
	
	//adicionar/remover veiculo específico
	
	public boolean atualizar_frota(Frota f,Veiculo v){
		
		//Se v existe em f --> remove v de f
		//Se v n existe me f --> adiciona v em f
		
		for(int i = 0;i < lista_frota.size();i++) {
			Frota x = lista_frota.get(i);
			if(x == f) {
				for(int j = 0;j < x.get_listaVeiculo().size();j++) {
					Veiculo y = x.get_listaVeiculo().get(j);
					if(y == v) {
						//o veiculo existe na frota,então remove
						x.get_listaVeiculo().remove(y);
						return true	;
					}
				}
				//O veiculo n esta na frota
				x.add_veiculo(v);
				return true;
			}
		}
	    return false;	
	}
	
	//remover frota
	
	public boolean atualizar_frota(Frota f){
		for(int i = 0;i < lista_frota.size();i++) {
			if(lista_frota.get(i) == f) {
				lista_frota.remove(i);
				return true;
			}
		}
		return false;
		
	}
	
	//
	
	public ArrayList<Veiculo> get_veiculos_por_frota(Frota f){
		return f.get_listaVeiculo();
	}
	
	
public String toString(){
		
		StringBuilder res = new StringBuilder();
		res.append("nome: " + this.get_nome());
		res.append(" / telefone: " + this.get_telefone());
		res.append(" / endereco: " + this.get_endereco());
		res.append(" / email: " + this.get_email());
		res.append(" / cnpj: " + this.get_cnpj());
		res.append(" / data fundacao: " + this.get_data_fundacao());
		return res.toString();
	}

	@Override
	public int get_tipo() {
		// PJ ---> 1
		return 1;
	}

}
