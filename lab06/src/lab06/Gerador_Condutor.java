package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gerador_Condutor implements I_Arquivo{
	
	private ArrayList<Condutor> condutores = new ArrayList<>();
	
	private String file;
	
	public Gerador_Condutor(String arq){
		file = arq;
	}
	
	public ArrayList<Condutor> get_condutores(){
		return condutores;
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
				Date nasc = new Date(infos[5]);
				Condutor c = new Condutor(infos[0],"","","","",nasc);
				for(int i = 1;i < infos.length;i++){
					if(i == 1){
						c.set_nome(infos[i]);
					}
					if(i == 2) {
						c.set_telefone(infos[i]);
					}
					if(i == 3){
						c.set_endereco(infos[i]);
					}
					if(i == 4){
						c.set_email(infos[i]);
					}
					
					
					//System.out.printf("%-10s",infos[i]);
				}
				//System.out.println();
				/*condutores.add(new ArrayList<>());
				condutores.get(condutores.size()-1).add(c);*/
				condutores.add(c);

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
