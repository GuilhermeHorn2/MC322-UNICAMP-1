package lab05;

import java.util.ArrayList;

public abstract class Seguro {

	private final int _id;
	private Date datainicio;
	private Date datafim;
	private Seguradora seguradora;
	protected ArrayList<Sinistro> listaSinistros;
	protected ArrayList<Condutor> listaCondutores;
	private int valorMensal;
	
	public Seguro(int id,Date inicio,Date fim,Seguradora seg,int valor) {
		_id = id;
		datainicio = inicio;
		datafim = fim;
		seguradora = seg;
		valorMensal = valor;
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
	public int get_valorMensal() {
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
	public void set_valorMensal(int valor) {
		valor =  valorMensal;
	}
	
	public abstract boolean autorizarCondutor(Condutor c);
	public abstract boolean desautorizarCondutor(Condutor c);
	protected abstract double calcularValor(Date atual);
	public abstract void gerarSinistro(); 
	
	
	
}
