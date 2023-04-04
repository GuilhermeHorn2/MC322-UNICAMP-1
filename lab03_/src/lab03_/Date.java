package lab03_;

public class Date {
	//s[0] = dia,s[1] = mes,s[2] = ano
	private static String[] s = new String[3];
	
	/*contrutor*/
	public Date(String dia,String mes,String ano) {
		//
		s[0] = dia;
		s[1] = mes;
		s[2] = ano;
	}
	
	/*get*/
	public String getDate() {
		//quero retornar algo do tipo:xx/xx/xxxx
		return s[0] + "/" + s[1] + "/" + s[2];
	} 
	
	/*set*/
	public void setDate(String data) {
		
		//Quero converter de xx/xx/xxxx para {{x,x},{x,x},{x,x,x,x}}
		
		int j = 0;
		for(int i = 0;i < data.length();i++) {
			if(data.substring(i, i+1).equals("/")){
				//Quando chegar aqui troca para a próxima string
				j++;
			}
			if(!data.substring(i, i+1).equals("/")) {
				s[j] += data.substring(i,i+1);
			}
		}
	}

}
