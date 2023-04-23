package lab04_;

public class ClientePJ extends Cliente{
	
	private final String _cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	
	public ClientePJ(String nome, String endereco, double valor_seguro, Veiculo v,String cnpj,Date fund,int qtde) {
		super(nome, endereco, valor_seguro, v);
		_cnpj = cnpj;
		dataFundacao = fund;
		qtdeFuncionarios = qtde;
	}
	
	//gets
	public String get_cnpj(){
		return _cnpj;
	}
	public Date get_dataFundacao() {
		return dataFundacao;
	}
	public int get_qtdeFuncionarios() {
		return qtdeFuncionarios;
	}
	
	//sets
	
	public void set_dataFundacao(Date data){
		dataFundacao = data;
	}
	public void set_qtdeFuncionarios(int qtde){
		qtdeFuncionarios = qtde;
	}
	
	//toString
	
	public String toString() {
		return "{cnpj: "+ _cnpj + "/dataFundacao: " + dataFundacao + "/qtdeFuncionarios" + qtdeFuncionarios + "}";
	}
	
	//calcular score
	
	public double calcula_score(){
		CalcSeguro c = CalcSeguro.VALOR_BASE;
		double x = c.fator;
		double y = 1 + (qtdeFuncionarios)/100;
		return x*y*(this.get_veiculos().size());
	}

}
