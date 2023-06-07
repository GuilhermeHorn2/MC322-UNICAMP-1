package geral;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements ActionListener{
	JButton button1;
	JButton button2;
	MyFrame(){
		
		button1 = new JButton();
		button1.setBounds(200,100, 150, 50);
		button1.setBackground(Color.green);
		button1.setText("POLINOMIOS");
		button1.addActionListener(this);
		button1.setFocusable(false);
		
		button2 = new JButton();
		button2.setBounds(200,300, 150, 50);
		button2.setBackground(Color.green);
		button2.setText("MATRIZES");
		button2.addActionListener(this);
		button2.setFocusable(false);
		
		ImageIcon image = new ImageIcon("sapo1.PNG");
		this.setTitle("Matematica Miscelanea");
		this.setResizable(false);
		this.setSize(750,550);
		this.setIconImage(image.getImage());
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(button1);
		this.add(button2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1){
			Interface_Polinomios pol_int = new Interface_Polinomios();
		}
		if(e.getSource() == button2) {
			Interface_Matrizes matriz_int = new Interface_Matrizes();
		}
		
	}

}
