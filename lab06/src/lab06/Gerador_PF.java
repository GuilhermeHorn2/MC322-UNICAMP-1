package lab06;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gerador_PF implements I_Arquivo{
	
	//private ArrayList<ArrayList<Cliente_PF>> clientes = new ArrayList<>();
	private ArrayList<Cliente_PF> clientes = new ArrayList<>();
	//os clientes serao instanciados sem veiculo,mas na main sera procurado quais veiculos correspondem a cada placa
	private ArrayList<String> placa_veiculo = new ArrayList<>();
	private String file;
	
	
	
	public Gerador_PF(String arq){
		file = arq;
	}
	
	public Gerador_PF(){
	}
	
	public ArrayList<Cliente_PF> get_clientes(){
		return clientes;
	}
	
	public ArrayList<String> get_placas(){
		return placa_veiculo;
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
				Date nasc = new Date(infos[7]);
				Cliente_PF c = new Cliente_PF(infos[0],"","",nasc,"","","","");
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
					if(i == 5){
						c.set_genero(infos[i]);
					}
					if(i == 6){
						c.set_educacao(infos[i]);
					}
					if(i == 8) {
						
						//guadar a placa
						placa_veiculo.add(infos[i]);
						
					}
					//System.out.printf("%-10s",infos[i]);
				}
				//System.out.println();
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
