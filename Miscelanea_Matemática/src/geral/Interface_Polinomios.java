package geral;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Interface_Polinomios extends JFrame implements ActionListener{
	
	JTextField pol;
	JButton button;
	Interface_Polinomios(){
		
		
		
		ImageIcon image = new ImageIcon("sapo1.PNG");
		this.setTitle("Raizes reais de um polinomio em x");
		this.setIconImage(image.getImage());
		this.setLayout(new FlowLayout());
		this.getContentPane().setBackground(Color.black);
		
		button = new JButton("Raizes reais");
		button.setBackground(Color.green);
		button.addActionListener(this);

		
		pol = new JTextField("Sintaxe: ax^n + bx^k + ... + c");
		pol.setPreferredSize(new Dimension(250,40));
		pol.setSize(200, 50);
		this.add(pol);
		this.add(button);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			String s = this.pol.getText();
			Polinomio pol1 = new Polinomio(s);
			Aproximar_polinomio aprox1 = new Aproximar_polinomio(pol1);
			System.out.println("Raizes Reais de: (" + s +") -->"+aprox1.metodo_generalizado());
		}
		
	}

}
