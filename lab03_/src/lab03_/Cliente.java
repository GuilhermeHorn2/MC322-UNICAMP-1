package lab03_;
import java.util.ArrayList;

public class Cliente {
   private String _nome;
   private String _endereco;
   private Date _dataLicensa;
   private String _educacao;
   private String _genero;
   private String _classeEconomica;
   private static ArrayList<Veiculo> ListaVeiculos = new ArrayList<>();
   
   /*construtor*/
   
   /*a lista de veiculos não começará com nenhum valor,mas atravez do set ela pode ser modificada,pode ser consultado no get*/
   public Cliente(String nome,String endereco,Date dataLicensa,String educacao,String genero,String classeEconomica,Veiculo v) {
	   _nome = nome;
	   _endereco = endereco;
	   _dataLicensa = dataLicensa;
	   _educacao = educacao;
	   _genero = genero;
	   _classeEconomica = classeEconomica;
	   ListaVeiculos.add(v);
   }
   
   /*gets*/
   public ArrayList getListaVeiculos() {
	   return ListaVeiculos;
   }
   public String getNome() {
	   return _nome;
   }
   public String getEndereco() {
	   return _endereco;
   }
   public String getDataLicensa() {
	   //quero retornar algo do tipo:xx/xx/xxxx
	   return _dataLicensa.getDate();
   }
   public String getEducacao() {
	   return _educacao;
   }   
   public String getGenero() {
	   return _endereco;
   }   
   public String getClasseEconomica() {
	   return _endereco;
   }  
   
   /*sets*/
   
   public void setVeiculo(Veiculo v) {
	   ListaVeiculos.add(v);	
   }
   public void setNome(String nome) {
	   _nome = nome;
   }
   public void setEndereco(String endereco) {
	   _endereco = endereco;
   }   
   public void setDataLicensa(String data) {
	   _dataLicensa.setDate(data);
   }   
   public void setEducacao(String educacao) {
	   _educacao = educacao;
   }   
   public void setClasseEconomica(String classeEconomica) {
	   _classeEconomica = classeEconomica;
   }
   
   /*toString*/
   
   public String toString() {
	    //printar os carros do cliente:
	   for(int i = 0;i < ListaVeiculos.size();i++) {
		   System.out.print("Veículo de número" + i+1 + ": ");
		   System.out.print(ListaVeiculos.get(i));
	   }
		return "{Nome: " + _nome + "/ Endereco: " + _endereco + "/ dataLicensa: " + _dataLicensa + "/ Educação: " + _educacao + "/Classe Ecnonômica: " + _classeEconomica;
	}   
}
