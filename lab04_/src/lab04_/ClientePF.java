package lab04_;
public class ClientePF extends Cliente{

	private final String _cpf;
	private String genero;
    private Date dataLicensa;
    private String educacao;
    private final Date dataNascimento;
    private String classeEconomica;
	
	public ClientePF(String nome, String endereco,String cpf,String gen,Date dataL,String edu,Date dataN,String eco) {
		super(nome, endereco);
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
	/*public void set_dataNascimento(Date d) {
		dataNascimento = d;
	}*/
	public void set_classeEconomica(String c) {
		classeEconomica = c;
	}
	
	//toString
	
	public String toString() {
		return "{cpf: "+_cpf+"/genero: "+genero+"/dataLicensa: " +dataLicensa+"/dataNascimento: "+dataNascimento+"/classeEconomica: " +classeEconomica+"}"; 
	}
	
	//calcularScore
	
	public int calc_idade(Date data) {
		//recebe a data atual
		
		int anos = data.get_ano() - this.dataNascimento.get_ano();
		
		
		if(data.get_mes() < this.dataNascimento.get_mes()) {
			return anos-1;
		}
		if(data.get_mes() == this.dataNascimento.get_mes()) {
			if(data.get_dia() < this.dataNascimento.get_dia()) {
				return anos-1;
			}
		}
		return anos;
		
	}
	
	public double calcular_score(){
		CalcSeguro c1 = CalcSeguro.VALOR_BASE;
		double x = c1.fator;
		
		Date atual = new Date("26","04","2023");
		int idade = calc_idade(atual);
		
		
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
	
	//tipo cliente
	
	public String tipo_cliente(){
		return "PF";
	}

}
