package geral;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

//log de operacoes de polinomios

public class Pol_log {
	
	private String file;
	
	private ArrayList<Double> raizes = new ArrayList<>();
	private String pol;
	
	public Pol_log(String arq,String p,ArrayList<Double> rz){
		file = arq;
		pol = p;
		raizes = rz;
		
	}
	
	public boolean gravar_log(){
		
		try {
			
			BufferedWriter writer  =new BufferedWriter(new FileWriter(file,true));
			
			StringBuilder b = new StringBuilder();
			writer.write("\n");
			writer.write("polinomio :" +pol+",");
			writer.append("raizes: "+raizes.toString());
			writer.close();
			return true;
		}
		catch(Exception E) {
			System.out.println("Operacao nao foi guardada.");
		}
		return false;
	}
	
	
}
