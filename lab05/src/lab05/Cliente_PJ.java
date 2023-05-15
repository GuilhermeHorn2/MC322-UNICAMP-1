package lab05;

import java.util.ArrayList;

public class Cliente_PJ extends Cliente{

	private final String _cnpj;
	private Date data_fundacao;
	private static ArrayList<Frota> lista_frota;
	
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
	
	public boolean atualizar_frota(Frota f,Veiculo v){
		
		boolean atl = false;
		
		for(int i = 0;i < lista_frota.size();i++) {
			Frota x = lista_frota.get(i);
			if(x == f) {
				//checa se o veiculo nao esta na frota
				for(int j = 0;j < x.get_listaVeiculo().size();j++) {
					Veiculo y = x.get_listaVeiculo().get(j);
					if(y == v) {
						break;
					}
				}
				atl = true;
				x.add_veiculo(v);
			}
		}
		return atl;
	}
	
	public ArrayList<Veiculo> get_veiculos_por_frota(Frota f){
		return f.get_listaVeiculo();
	}

	@Override
	public int get_tipo() {
		// PJ ---> 1
		return 1;
	}

}
