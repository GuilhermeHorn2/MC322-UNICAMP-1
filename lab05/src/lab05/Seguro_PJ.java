package lab05;

public class Seguro_PJ extends Seguro{
	
	

	public Seguro_PJ(int id, Date inicio, Date fim, Seguradora seg, int valor) {
		super(id, inicio, fim, seg, valor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean autorizarCondutor(Condutor c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean desautorizarCondutor(Condutor c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected double calcularValor(Date atual) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void gerarSinistro() {
		// TODO Auto-generated method stub
		
	}

}
