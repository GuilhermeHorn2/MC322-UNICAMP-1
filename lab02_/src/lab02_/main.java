package lab02_;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//testa isso:280.012.389−38
        Cliente c = new Cliente("","280.012.389 - 38","",20,"");
        System.out.println(c.validarCPF(c.getCpf()));
        /*System.out.println(c.getCpf());
        String r = c.getCpf().replaceAll("[\\s.-]+","");
        System.out.println(r);*/

	}

}
