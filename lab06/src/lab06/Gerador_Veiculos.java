package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gerador_Veiculos implements I_Arquivo{
	
	//private ArrayList<ArrayList<Veiculo>> veiculos = new ArrayList<>();
	
	private ArrayList<Veiculo> veiculos = new ArrayList<>();
	
	private String file;
	
	
	public Gerador_Veiculos(String arq) {
		file = arq;
	}
	
	public ArrayList<Veiculo> get_veiculos(){
		return veiculos;
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
				
				Veiculo c = new Veiculo("","","",0);
				for(int i = 0;i < infos.length;i++){
					if(i == 0){
						c.set_placa(infos[i]);
					}
					if(i == 1) {
						c.set_marca(infos[i]);
					}
					if(i == 2){
						c.set_modelo(infos[i]);
					}
					if(i == 3){
						try {
							c.set_anoFabricacao(Integer.parseInt(infos[i]));
						}
						catch(NumberFormatException nfe) {
							System.out.println("ano invalido.");
						}
					}
					
				}
				/*veiculos.add(new ArrayList<>());
				veiculos.get(veiculos.size()-1).add(c);*/
				veiculos.add(c);
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
