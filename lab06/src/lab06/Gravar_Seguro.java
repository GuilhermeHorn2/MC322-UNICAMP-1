package lab06;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Gravar_Seguro implements I_Arquivo{
	
	//arquivo que sera gerado
	private String file;
	
	//seguros que vao ser gravados
	private ArrayList<Seguro> seguros;
	
	public Gravar_Seguro(String arq,ArrayList<Seguro> segs){
		file = arq;
		seguros = segs;
	}

	@Override
	public boolean gravar_arquivo() {
		//BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			StringBuilder b = new StringBuilder();
			writer.write("id,"+"data_inicio,"+"data_fim,"+"seguradora,"+"lista_condutores,"+"valor_mensal");
			
			for(int i = 0;i < seguros.size();i++) {
				Seguro x = (Seguro)seguros.get(i);
				writer.write("\n");
				b.append(x.get_id()+",");
				b.append(x.get_datainicio()+",");
				b.append(x.get_datafim()+",");
				b.append(x.get_seguradora().get_nome()+",");
				
				//lista de condutores:
				ArrayList<Condutor> l_cond = x.get_listaCondutores();
				StringBuilder k = new StringBuilder();
				k.append("[");
				for(int j = 0;j < l_cond.size();j++){
					Condutor c = l_cond.get(j); 
					if(j != l_cond.size()-1) {
						k.append(c.get_cpf()+",");
						continue;
					}
					k.append(c.get_cpf()+"]");
				}
				b.append(k.toString()+",");
				b.append(x.get_valorMensal());
				writer.write(b.toString());
				b = new StringBuilder();
				
			}
			writer.close();
			return true;
			
			
		}
		catch(Exception e) {
			System.out.println("Arquivo Invalido.");
		}

		
		return false;
	}

	@Override
	public String ler_arquivo() {
		//nao ser usado neste caso.
		return null;
	}
	
	
	
	

}
