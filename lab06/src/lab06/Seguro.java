package lab06;

import java.util.ArrayList;

public abstract class Seguro{

	private final int _id;
	private Date datainicio;
	private Date datafim;
	private Seguradora seguradora;
	protected ArrayList<Sinistro> listaSinistros;
	protected ArrayList<Condutor> listaCondutores;
	private double valorMensal;
	
	public Seguro(int id,Date inicio,Date fim,Seguradora seg) {
		_id = id;
		datainicio = inicio;
		datafim = fim;
		seguradora = seg;
		//valorMensal = valor;
		listaSinistros = new ArrayList<>();
		listaCondutores = new ArrayList<>();
	}
	
	public int get_id(){
		return _id;
	}
	public Date get_datainicio(){
		return datainicio;
	}
	public Date get_datafim(){
		return datafim;
	}
	public Seguradora get_seguradora(){
		return seguradora;
	}
	public ArrayList<Sinistro> get_listaSinistros(){
		return listaSinistros;
	}
	public ArrayList<Condutor> get_listaCondutores(){
		return listaCondutores;
	}
	public double get_valorMensal() {
		return valorMensal;
	}
	

	public void set_datainicio(Date inicio){
		datainicio = inicio;
	}
	public void set_datafim(Date fim){
		datafim = fim;
	}
	public void set_seguradora(Seguradora s){
		seguradora = s;
	}
	public void set_valorMensal(double valor) {
		valorMensal = valor;
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
	
	public abstract boolean autorizarCondutor(Condutor c);
	public abstract boolean desautorizarCondutor(Condutor c);
	public abstract double calcularValor(Date atual);
	public abstract void gerarSinistro(Date data,String endereco,Condutor condutor); 
	public abstract int get_type();
	
	
}
