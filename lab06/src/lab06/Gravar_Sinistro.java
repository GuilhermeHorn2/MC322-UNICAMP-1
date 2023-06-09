package lab06;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Gravar_Sinistro implements I_Arquivo{

	//arquivo que sera gerado
		private String file;
		
		//seguros que vao ser gravados
		private ArrayList<Sinistro> sinistros;
		
		public Gravar_Sinistro(String arq,ArrayList<Sinistro> sin){
			file = arq;
			sinistros = sin;
		}

		@Override
		public boolean gravar_arquivo() {
			//BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			try {
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				StringBuilder b = new StringBuilder();
				writer.write("id,"+"data,"+"endereco,"+"condutor,"+"id_seguro");
				
				for(int i = 0;i < sinistros.size();i++) {
					Sinistro x = sinistros.get(i);
					writer.write("\n");
					b.append(x.get_id()+",");
					b.append(x.get_data()+",");
					b.append(x.get_endereco()+",");
					b.append(x.get_condutor().get_cpf()+",");
					b.append(x.get_seguro().get_id());
					
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
