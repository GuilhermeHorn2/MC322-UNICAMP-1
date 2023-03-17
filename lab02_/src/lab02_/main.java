package lab02_;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Cliente c = new Cliente("a","b","c",20,"b");
		System.out.println(c.toString());*/
		String s = "123.456.789-12";
		String r = s.replaceAll("[.-]+","");
		System.out.println(r.length());

	}

}
