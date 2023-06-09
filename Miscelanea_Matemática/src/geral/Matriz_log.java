package geral;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

//log de operacoes com matrizes

public class Matriz_log {
	private String file;
	
	private ArrayList<ArrayList<Double>> a;
	private ArrayList<ArrayList<Double>> b;
	private ArrayList<ArrayList<Double>> c;
	double det = 0;
	
	public Matriz_log(String arq,ArrayList<ArrayList<Double>> a,ArrayList<ArrayList<Double>> b,ArrayList<ArrayList<Double>> c){
		this.a = a;
		this.b = b;
		this.c = c;
		file = arq;
	}
	public Matriz_log(String arq,ArrayList<ArrayList<Double>> a,ArrayList<ArrayList<Double>> b){
		this.a = a;
		this.b = b;
		file = arq;
	}
	public Matriz_log(String arq,ArrayList<ArrayList<Double>> a,double det){
		this.a = a;
		file = arq;
		this.det = det;
	}
	
	public int max(int a,int b) {
		if(a > b) {
			return a;
		}
		return b;
	}
	
	public boolean Gravar_matriz_A(){
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write("\n");
			writer.write("Matriz A");
			writer.write("\n");
			
			for(int i = 0;i < a.size();i++){
				writer.write(a.get(i).toString());
				writer.write("\n");
			}
			
			writer.close();
			return true;
			
			
		}
		catch(Exception e) {
			System.out.println("Falha em guardar A");
		}
		
		return false;
	}
	public boolean Gravar_matriz_B(){
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write("\n");
			writer.write("Matriz B");
			writer.write("\n");
			
			for(int i = 0;i < b.size();i++){
				writer.write(b.get(i).toString());
				writer.write("\n");
			}
			
			writer.close();
			return true;
			
			
		}
		catch(Exception e) {
			System.out.println("Falha em guardar B");
		}
		
		return false;
	}
	
	public boolean Gravar_mult() {
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write("\n");
			writer.write("Matriz AXB");
			writer.write("\n");
			
			for(int i = 0;i < c.size();i++){
				writer.write(c.get(i).toString());
				writer.write("\n");
			}
			
			writer.close();
			return true;
			
			
		}
		catch(Exception e) {
			System.out.println("Falha em guardar AB");
		}
		
		return false;
	}
	public boolean Gravar_inversa(){
			try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write("\n");
			writer.write("Matriz A^-1");
			writer.write("\n");
			
			for(int i = 0;i < b.size();i++){
				writer.write(b.get(i).toString());
				writer.write("\n");
			}
			
			writer.close();
			return true;
			
			
		}
		catch(Exception e) {
			System.out.println("Falha em guardar A^-1");
		}
		
		return false;
	}
	public boolean Gravar_det_A(){
			try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write("\n");
			writer.write("det(A): ");
			
			writer.write(""+det);
			
			writer.close();
			return true;
			
			
		}
		catch(Exception e) {
			System.out.println("Falha em guardar det");
		}
		
		return false;
	}
	public boolean Gravar_det_B(){
		try {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		writer.write("\n");
		writer.write("det(B): ");
		
		writer.write(""+det);
		
		writer.close();
		return true;
		
		
		}
		catch(Exception e) {
			System.out.println("Falha em guardar det");
		}
	
		return false;
	}
}
