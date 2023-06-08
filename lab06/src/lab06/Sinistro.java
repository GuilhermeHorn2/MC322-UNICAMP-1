package lab06;

public class Sinistro {
	
	private final int _id;
	private Date _data;
	private String _endereco;
	private Condutor _condutor;
	private Seguro _seguro;
	
	public Sinistro(int id,Date data,String endereco,Condutor condutor,Seguro seguro) {	
		_id = id;
		_data = data;
		_endereco = endereco;
		_condutor = condutor;
		_seguro = seguro;
	}
	
	public int get_id() {
		return _id;
	}
	
	public Date get_data(){
		return _data;
	}
	public String get_endereco(){
		return _endereco;
	}
	public Condutor get_condutor(){
		return _condutor;
	}
	public Seguro get_seguro(){
		return _seguro;
	}
	
	public void set_data(Date data){
		_data = data;
	}
	public void set_endereco(String endereco){
		_endereco = endereco;
	}
	public void set_condutor(Condutor condutor){
		_condutor = condutor;
	}
	public void set_seguro(Seguro seguro){
		_seguro = seguro;
	}
	
	public String toString(){
		
		StringBuilder res = new StringBuilder();
		res.append("id: " + this.get_id());
		res.append("/Data: " + this.get_data());
		res.append("/Endereco: " + this.get_endereco());
		res.append("/Condutor: " + this.get_condutor());
		res.append("/Seguro: " + this.get_seguro());
		return res.toString();
	}
	
}
