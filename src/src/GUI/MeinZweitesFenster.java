package GUI;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MeinZweitesFenster {

	JFrame frmApp = new JFrame("Application");

	JTextField txt1 = new JTextField("App");
	JButton btn1 = new JButton();
	JButton btn2 = new JButton();
	JButton btn3 = new JButton();

	MeinZweitesFenster() {
		
 
		

		/* ----------- Einstellungen des JFrames ----------- */
		// Ein JFrame muss mit .setVisible immer auf true gesetz werden!
		frmApp.setVisible(true);
		// Ein JFrame sollte immer eine größe haben --> setSize
		frmApp.setSize(600, 200);
		// mit .setTitle lässt sich der Titel des JFrames festlegen (alternativ
		// im Konstruktor des JFrames)
		frmApp.setTitle("App");
		// Wir setzen immer new FlowLayout() mit .setLayout
		frmApp.setLayout(new FlowLayout());

		/* ----------- Buttons, Labels, TextFelder ----------- */
		// alle Objekte die in das JFrame hineinkommen sollen werden mit .add
		// dem JFrame hinzugefügt

		// mit .setText lässt sich der Text eines TextFields, Buttons oder
		// Labels festlegen
		
		
		btn1.setText("Module");
		frmApp.add(btn1);
		
		btn2.setText("News");
		frmApp.add(btn2);
		
		btn3.setText("Professoren");
		frmApp.add(btn3);

		// Sonderdinger
		btn1.setToolTipText("Button1");
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("test");
		
		btn2.setToolTipText("Button2");
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("test");
		
		btn3.setToolTipText("Button3");
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("test");

	}

	public boolean setVisible(boolean b) {
		return true;
		
	}

	
}
