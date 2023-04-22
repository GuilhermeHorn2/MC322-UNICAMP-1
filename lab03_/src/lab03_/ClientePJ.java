package lab03_;

public class ClientePJ extends Cliente {
	
	
	private final String _cnpj;//cnpj final
	private Date _dataFundacao;

	
	public ClientePJ(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, Veiculo v,String cnpj,Date dataFundacao) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica, v);
		_cnpj = cnpj;
		_dataFundacao = dataFundacao;
		
	}
	
	/*gets*/
	
	public String getCnpj() {
		return _cnpj;
	}
	
	public String getDataFundacao() {
		return _dataFundacao.getDate();
	}
	
	/*sets*/

	
	public void setDataFundacao(String Date) {
		//vai que a pessoa digitou errado
		_dataFundacao.setDate(Date);
	}
	
	/*Validar Cnpj*/
	
	public  boolean validar_cnpj(String c) {
	/*Tirar . e -*/
	c = c.replaceAll("[\\s.-]+","");
		
	/*Passar todos os digitos de c para inteiros em v*/
	int[] v = new int[c.length()];
	for(int i = 0;i < c.length();i++) {
		int k = Integer.parseInt(c.substring(i, i+1));
		v[i] = k;
	}
		
	/*Calculo do primeiro digito verificador*/
	
	int p = 2;//peso
	int s = 0;//soma
	
	for(int i = 11;i >= 0;i--) {
		if(p == 10) {
			p = 2;
		}
		s += v[i]*p;
		p++;
	}
	
	int d = s % 11;
	if(d == 0 || d == 1){
		d = 0;
	}
	else {
		d = 11 - d;
	}
	
	if(v[12] != d) {
		//verificação falhou
		return false;
	}
	
	/*calculo do segundo digito*/
	
	p = 2;
	s = 0;
	for(int i = 12;i >= 0;i--) {
		if(p == 10) {
			p = 2;
		}
		s += v[i]*p;
		p++;
	}
	
	d = s % 11;
	
	if(d == 0 || d == 1) {
		d = 0;
	}
	else {
		d = 11 - d;
	}
	if(v[13] != d) {
		
		return false;
	}					
	
		return true;
	}	
	

	/*toString*/
	
	public String toString() {
		return "{Cnpj: " + _cnpj + "/ Data de Fundacao: " + _dataFundacao + "}";
	}
	
	


	

}
