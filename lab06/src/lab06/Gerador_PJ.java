package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gerador_PJ implements I_Arquivo{
	
	private ArrayList<Cliente_PJ> clientes = new ArrayList<>();
	
	private ArrayList<String> id_frota = new ArrayList<>();
	private String file;
	
	
	public Gerador_PJ(String arq){
		file = arq;
	}
	
	public ArrayList<Cliente_PJ> get_clientes(){
		return clientes;
	}
	
	public ArrayList<String> get_id(){
		return id_frota;
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
				Date fund = new Date(infos[5]);
				Cliente_PJ c = new Cliente_PJ(infos[0],fund,"","","","");
				for(int i = 1;i < infos.length;i++){
					if(i == 1){
						c.set_nome(infos[i]);
					}
					if(i == 2) {
						c.set_nome(infos[i]);
					}
					if(i == 3){
						c.set_telefone(infos[i]);
					}
					if(i == 4){
						c.set_endereco(infos[i]);
					}
					if(i == 6) {
						//id_frota.add(Integer.parseInt(infos[i]));
						id_frota.add(infos[i]);
						
					}
					

				}
				/*clientes.add(new ArrayList<>());
				clientes.get(clientes.size()-1).add(c);*/
				clientes.add(c);
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
