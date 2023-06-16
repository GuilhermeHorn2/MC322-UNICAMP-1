package geral;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Interface_Matrizes extends JFrame implements ActionListener{
	
	
	/*
	 * As condicoes de existencia de cada operacao sao geridas nas funcoes internas.
	 */
	
	ArrayList<ArrayList<Double>> a = new ArrayList<>();
	ArrayList<ArrayList<Double>> b = new ArrayList<>();
	Matriz A = null;
	Matriz B = null;
	
	JTextField linha_a;
	JButton subm_a;
	JTextField linha_b;
	JButton subm_b;
	JButton fechar_a;
	JButton fechar_b;
	
	//realizar: AB
	JButton mult_AB;
	
	//inverter A e B,vai tentar inverter,mas pode ser que nao seja invertivel ou que alguma esteja vazia
	JButton inv;
	
	//determinate de A e B,se alguma estiver vazia so retorna o da que nao estiver vazia
	JButton det;
	
	//reset,A e B apontam para null novamente e a e b dao clear
	
	JButton reset;
	
	Interface_Matrizes(){
		
		ImageIcon image = new ImageIcon("sapo1.PNG");
		this.setTitle("Algumas operacoes com matrizes");
		this.setIconImage(image.getImage());
		//this.setLayout(new FlowLayout());
		this.setResizable(false);
		this.setSize(750,550);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		
		//Matriz A
		
		subm_a = new JButton("Linha da matriz A");
		subm_a.setBackground(Color.green);
		subm_a.addActionListener(this);
		subm_a.setBounds(0,0, 150, 50);
		subm_a.setFocusable(false);

		
		linha_a = new JTextField("Sintaxe: 1,2,3,4,...");
		//linha_a.setSize(200, 50);
		linha_a.setBounds(160, 0, 200, 50);
		this.add(subm_a);
		this.add(linha_a);
		
		fechar_a = new JButton("Completar Matriz A");
		fechar_a.setBackground(Color.green);
		fechar_a.addActionListener(this);
		fechar_a.setBounds(200,200, 150, 50);
		fechar_a.setFocusable(false);
		
		this.add(fechar_a);
		
		//Matriz B
		
		subm_b = new JButton("Linha da matriz B");
		subm_b.setBackground(Color.green);
		subm_b.addActionListener(this);
		subm_b.setBounds(0,100, 150, 50);
		subm_b.setFocusable(false);

		
		linha_b = new JTextField("Sintaxe: 1,2,3,4,...");
		linha_b.setPreferredSize(new Dimension(250,40));
		//linha_b.setSize(200, 50);
		linha_b.setBounds(160, 100, 200, 50);
		//linha_b.setFocusable(false);
		this.add(subm_b);
		this.add(linha_b);
		
		fechar_b = new JButton("Completar Matriz B");
		fechar_b.setBackground(Color.green);
		fechar_b.addActionListener(this);
		fechar_b.setBounds(0,200, 150, 50);
		fechar_b.setFocusable(false);
		this.add(fechar_b);
		
		//Multiplicacao
		
		mult_AB = new JButton("AxB");
		mult_AB.setBackground(Color.green);
		mult_AB.addActionListener(this);
		mult_AB.setBounds(50,300, 150, 50);
		mult_AB.setFocusable(false);
		this.add(mult_AB);
		
		//Inverter
		
		inv = new JButton("Inverter A e/ou B");
	    inv.setBackground(Color.green);
		inv.addActionListener(this);
		inv.setBounds(50,400, 150, 50);
	    inv.setFocusable(false);
		this.add(inv);
		
		//Determinante
		
		det = new JButton("Determinante");
		det.setBackground(Color.green);
		det.addActionListener(this);
		det.setBounds(250,300, 150, 50);
		det.setFocusable(false);
		this.add(det);
		
		//Reset
		
		reset = new JButton("Reset");
		reset.setBackground(Color.green);
		reset.addActionListener(this);
		reset.setBounds(250,400, 150, 50);
		reset.setFocusable(false);
		this.add(reset);
		
		//this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//1)Setando as matrizes:
		
		if(e.getSource() == subm_a) {
			a.add(new ArrayList<>());
			String linha = linha_a.getText();
			for(int i = 0;i < linha.length();i++) {
				String x = linha.substring(i, i+1);
				if(x.equals(",") || x.equals(" ")) {
					continue;
				}
				try {
					a.get(a.size()-1).add(Double.parseDouble(x));
				}
				catch(NumberFormatException nfe) {
					System.out.println("Sinatxe Invalida.");
				}
			}
			linha_a.setText("");
			
		}
		if(e.getSource() == subm_b) {
			
			String linha = linha_b.getText();
			b.add(new ArrayList<>());
			for(int i = 0;i < linha.length();i++) {
				String x = linha.substring(i, i+1);
				if(x.equals(",") || x.equals(" ")) {
					continue;
				}
				try {
					b.get(b.size()-1).add(Double.parseDouble(x));
				}
				catch(NumberFormatException nfe) {
					System.out.println("Sinatxe Invalida.");
				}
			}
			linha_b.setText("");
			
		}
		
		//1)
		
		//2)Fechando e instanciando as matrizes
		
		if(e.getSource() == fechar_a){
			//System.out.println(a);
			if(a.size() != 0){
				try {
					ArrayList<ArrayList<Double>> temp = new ArrayList<>();
					temp.addAll(a);
					A = new Matriz(temp);
					System.out.println("Matriz A:");
					A.print_matriz();
					Matriz_log log = new Matriz_log("src\\logMatrizes.csv",temp,b);
					log.Gravar_matriz_A();
				}
				catch(TamanhoDistintoException tde) {
					System.out.println("Entrada Invalida.");
				}
				
			}
			a.clear();
		}
		if(e.getSource() == fechar_b){
			
			if(b.size() != 0){
				try {
					ArrayList<ArrayList<Double>> temp = new ArrayList<>();
					temp.addAll(b);
					B = new Matriz(temp);
					System.out.println("Matriz B:");
					B.print_matriz();
					Matriz_log log = new Matriz_log("src\\logMatrizes.csv",a,temp);
					log.Gravar_matriz_B();
				}
				catch(TamanhoDistintoException tde) {
					System.out.println("Entrada Invalida.");
				}
				
			}
			b.clear();
		}
		
		//2)
		
		//3)AxB
		if(e.getSource() == mult_AB){
			
			if(A != null && B != null){
				Matriz C = A.multiplicar(B);
				if(C != null) {
					System.out.println("AxB:");
					ArrayList<ArrayList<Double>> mult = C.get_matriz();
					C.print_matriz();
					Matriz_log log = new Matriz_log("src\\logMatrizes.csv",a,b,mult);
					log.Gravar_mult();
				}
				else {
					System.out.println("Multiplicacao Invalida.");
				}
			}
			else {
				System.out.println("Multiplicacao Invalida");
			}
			
		}
		
		//4)Inverter
		
		if(e.getSource() == inv){
			
			if(A != null) {
				System.out.println("Inversa de A:");
				Matriz temp = A.calcular_inversa();
				if(temp != null) {
					ArrayList<ArrayList<Double>> inv = temp.get_matriz();
					temp.print_matriz();
					Matriz_log log = new Matriz_log("src\\logMatrizes.csv",a,inv);
					log.Gravar_inversa();
				}
				
			}
			if(B != null) {
				System.out.println("Inversa de B:");
				Matriz temp = B.calcular_inversa();
				if(temp != null) {
					ArrayList<ArrayList<Double>> inv = temp.get_matriz();
					temp.print_matriz();
					Matriz_log log = new Matriz_log("src\\logMatrizes.csv",a,inv);
					log.Gravar_inversa();
				}
			
			}
			
		}
		///
		//5)Determinante
		
		if(e.getSource() == det){
			if(A != null) {
				if(A.get_matriz().size() != 0 && A.get_matriz().get(0).size() != 0) {
					double det = A.calcular_determinante(A);
					Matriz_log log = new Matriz_log("src\\logMatrizes.csv",a,det);
					log.Gravar_det_A();
					System.out.println("det(A) = "+det);
				}
			
			}
			if(B != null) {
				if(B.get_matriz().size() != 0 && B.get_matriz().get(0).size() != 0) {
					double det = B.calcular_determinante(B);
					Matriz_log log = new Matriz_log("src\\logMatrizes.csv",b,det);
					log.Gravar_det_B();
					System.out.println("det(B) = "+det);
				}
				
			}
		}
		
		//6)
		
		if(e.getSource() == reset) {
			A = null;
			B = null;
			a.clear();
			b.clear();
		}
		
	}

}
