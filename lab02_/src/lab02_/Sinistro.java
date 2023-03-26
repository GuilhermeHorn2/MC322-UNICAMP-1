package lab02_;

import java.util.Random;

public class Sinistro {
      private int _id;
      private String _data;
      private String _endereco;
      
      //é preciso de um contador para colocar os ids no vetor_id
      
      private static int contador = 0;
      
      //vou assumir 999 como o numero maximo de objetos e ids que podem ser gerados
      
      private static int[] vetor_id = new int[999];
      

      //construtor
      
      public Sinistro(String data,String endereco) {
    	  _data = data;
    	  _endereco = endereco;
    	  
    	  //para criar um objeto: Sinistro s = new Sinistro(data,endereco)
    	  
    	  _id = gerar_id();
    	  
    	  /*Como é preciso gerar um id único e aleatório,é preciso ter informações de quais ids
    	   * estão indexados em algum objeto,para assim gerar algum id válido
    	   */
    	  
    	  vetor_id[contador] = _id;
    	 contador += 1;
    	 
      }
      
      //get
      
      public String getData() {
    	  //
    	  return _data;
      }      
      public String getEndereco() {
    	  //
    	  return _endereco;
      }     
      public int getid() {
    	  //
    	  return _id;
      }
      
      
      //set
      public void setData(String data){
    	  _data = data;
      }
      public void setEndereco(String endereco){
    	  _endereco = endereco;
      }
      public void setid(int id){
    	  
    	  //não sei se precisa desse,pois o id é gerado automaticamente de forma aleatória
    	  _id = id;
      }
      
      //
      public int na_lista(int i,int[] v) {
    	  
    	  //retorna 1 se i estiver na lista,0 caso contrario
    	  for(int j = 0;j < v.length;j++) {
    		  if(i == v[j]) {
    			  return 1;
    		  }
    	  }
    	  return 0;
      }
      
      public int gerar_id(){
    	  
    	  //aqui eu procuro um id aleatório até chegar em algum que não esteja em vetor_id
    	  int id = -1;
    	  
    	  while(true) {
    		  if(vetor_id[998] != 0) {
    			  
    			  //proteção pra não cair em um loop infinito caso os ids estejam estejam preenchidos de 1-999
    			  System.out.println("Numero maximo de objetos instanciados atingido");
    			  break;
    		  }
    		  Random x = new Random();
    		  int r = x.nextInt(999);
    		  if(na_lista(r,vetor_id) == 0) {
    			  id = r;
    			  break;
    		  }
    	  }
    	  
    	  return id;
      }
}
