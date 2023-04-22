package lab04_;
public class ClientePF extends Cliente{

	private final String _cpf;
	private String genero;
    private Date dataLicensa;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;
	
	public ClientePF(String nome, String endereco, double valor_seguro, Veiculo v,String cpf,String gen,Date dataL,String edu,Date dataN,String eco) {
		super(nome, endereco, valor_seguro, v);
		_cpf = cpf;
		genero = gen;
		dataLicensa = dataL;
		educacao = edu;
		dataNascimento = dataN;
		classeEconomica = eco;
	}	
	
	//gets
	
	public String get_cpf(){
		return _cpf;
	}
	public String get_genero(){
		return genero;
	}
	public Date get_dataLicensa(){
		return dataLicensa;
	}
	public String get_educacao(){
		return educacao;
	}
	public Date get_dataNascimento(){
		return dataNascimento;
	}
	public String get_classeEconomica(){
		return classeEconomica;
	}
	
	//set 
	
	public void set_genero(String g){
		genero = g;
	}
	public void set_dataLicensa(Date d){
		dataLicensa = d;
	}
	public void set_educacao(String e) {
		educacao = e;
	}
	public void set_dataNascimento(Date d) {
		dataNascimento = d;
	}
	public void set_classeEconomica(String c) {
		classeEconomica = c;
	}
	
	//toString
	
	public String toString() {
		return "{cpf: "+_cpf+"/genero: "+genero+"/dataLicensa: " +dataLicensa+"/dataNascimento: "+dataNascimento+"/classeEconomica: " +classeEconomica+"}"; 
	}
	
	//calcularScore
	
	public int calc_idade(Date d) {
		//recebe a data atual
		
		int anos = d.get_ano() - dataNascimento.get_ano();
		
		if(d.get_mes() < dataNascimento.get_mes()) {
			return anos-1;
		}
		if(d.get_mes() == dataNascimento.get_mes()) {
			if(d.get_dia() < dataNascimento.get_dia()) {
				return anos-1;
			}
		}
		return anos;
		
	}
	
	public double calcular_score(){
		CalcSeguro c1 = CalcSeguro.VALOR_BASE;
		double x = c1.fator;
		
		int idade = calc_idade(dataNascimento);
		
		CalcSeguro c2;
		if(idade >= 18 && idade < 30) {
			c2 = CalcSeguro.FATOR_18_30;
			double y = c2.fator;
			return x*y*(this.get_veiculos().size());
		}
		if(idade >= 30 && idade < 60) {
			c2 = CalcSeguro.FATOR_30_60;
			double y = c2.fator;
			return x*y*(this.get_veiculos().size());
		}
		if(idade >= 60 && idade < 90) {
			c2 = CalcSeguro.FATOR_60_90;
			double y = c2.fator;
			return x*y*(this.get_veiculos().size());
		}
		return -1;
		
	}
	

}
