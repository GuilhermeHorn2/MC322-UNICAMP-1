package lab06;

public class Date {
	//s[0] = dia,s[1] = mes,s[2] = ano
	private  String[] s = new String[3];
	
	
	/*contrutor*/
	public Date(String dia,String mes,String ano) {
		//
		s[0] = dia;
		s[1] = mes;
		s[2] = ano;
	}
	
	public Date(String s) {
		String ano = "";
		String mes = "";
		String dia = "";
		StringBuilder data = new StringBuilder();
		int c = 0;
		for(int i = 0;i < s.length();i++){
			String x = s.substring(i, i+1);
			if(x.equals("-")){
				if(c == 0){
					ano = data.toString();
				}
				if(c == 1) {
					mes = data.toString();
				}
				data = new StringBuilder();
				c++;
				continue;
			}
			data.append(x);
		}
		dia = data.toString();
		
		this.s[0] = dia;
		this.s[1] = mes;
		this.s[2] = ano;
	}
	
	
	public Date() {}
	
	/*get*/
	public String getDate() {
		//quero retornar algo do tipo:xx/xx/xxxx
		return s[0] + "/" + s[1] + "/" + s[2];
	} 
	
	public int get_ano() {
		int x = Integer.parseInt(s[2]);
		return x;
	}
	public int get_mes() {
		int x = Integer.parseInt(s[1]);
		return x;
	}
	public int get_dia(){
		int x = Integer.parseInt(s[0]);
		return x;
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
	
    /*toString*/
	public String toString() {
		return s[0]+"/"+s[1]+"/"+s[2];
	}

}
