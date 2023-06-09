package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gerador_Frota implements I_Arquivo{
	
	private ArrayList<Frota> frotas = new ArrayList<>();
	
	private ArrayList<String> placas = new ArrayList<>();
	
	private String file;
	
	
	public Gerador_Frota(String arq) {
		file = arq;
	}
	
	public ArrayList<String> get_placas(){
		return placas;
	}
	
	public ArrayList<Frota> get_frotas(){
		return frotas;
	}
	
	@Override
	public boolean gravar_arquivo() {
		//Nao sera utilizado para esse caso.
		return false;
	}
	@Override
	public String ler_arquivo() {
		
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null){

				String[] infos = line.split(",");
				//Instanciando os objetos e colocando na lista
				
				Frota c = new Frota("");
				for(int i = 0;i < infos.length;i++){
					if(i == 0){
						c.set_code(infos[i]);
					}
					else {
						placas.add(infos[i]);
					}
				}
				
				/*frotas.add(new ArrayList<>());
				frotas.get(frotas.size()-1).add(c);*/
				frotas.add(c);
			}
		}
		catch(Exception e){
			System.out.println("Arquivo Invalido.");
		}
		finally{
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Arquivo Invalido.");
			}
		}
		
		//nao entendi o motivo de retornar String...
		return null;
	}
	
}
