package lab03_;
import java.util.ArrayList;

public class Cliente {
   private String _nome;
   private String _endereco;
   private Date _dataLicensa;
   private String _educacao;
   private String _genero;
   private String _classeEconomica;
   private static ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
   
   /*construtor*/
   
   /*a lista de veiculos não começará com nenhum valor,mas atravez do set ela pode ser modificada,pode ser consultado no get*/
   public Cliente(String nome,String endereco,Date dataLicensa,String educacao,String genero,String classeEconomica) {
	   _nome = nome;
	   _endereco = endereco;
	   _dataLicensa = dataLicensa;
	   _educacao = educacao;
	   _genero = genero;
	   _classeEconomica = classeEconomica;
   }
   
   /*gets*/
   
   
   /*sets*/
   
   
   /*toString*/
}
