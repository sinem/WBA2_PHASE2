package GUI;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		btn1.addActionListener(new clickMod());
		frmApp.add(btn1);
		
		btn2.setText("News");
		btn2.addActionListener(new clickNews());
		frmApp.add(btn2);
		
		btn3.setText("Professoren");
		btn3.addActionListener(new clickProf());
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
	
	class clickMod implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			 new gui_module().setVisible(true);

		}
	}
		
		class clickNews implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
				 new gui_news().setVisible(true);

			}
		}
			
			class clickProf implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					
					 new gui_prof().setVisible(true);

				}
			}

			public void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}

}
