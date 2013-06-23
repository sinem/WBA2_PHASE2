package GUI;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class guiModulbeschreibung {

	JFrame frmApp = new JFrame("Application");

	JTextField txt1 = new JTextField("App");
	JButton btn1 = new JButton();
	JLabel lbl1 = new JLabel();
	
	JLabel lbl2 = new JLabel();
	JLabel lbl3 = new JLabel();
	JLabel lbl4 = new JLabel();
	JLabel lbl5 = new JLabel();
	JLabel lbl6 = new JLabel();

	guiModulbeschreibung() {
		

		/* ----------- Einstellungen des JFrames ----------- */
		// Ein JFrame muss mit .setVisible immer auf true gesetz werden!
		frmApp.setVisible(true);
		// Ein JFrame sollte immer eine gr��e haben --> setSize
		frmApp.setSize(600, 200);
		// mit .setTitle l�sst sich der Titel des JFrames festlegen (alternativ
		// im Konstruktor des JFrames)
		frmApp.setTitle("App");
		// Wir setzen immer new FlowLayout() mit .setLayout
		frmApp.setLayout(new FlowLayout());

		/* ----------- Buttons, Labels, TextFelder ----------- */
		// alle Objekte die in das JFrame hineinkommen sollen werden mit .add
		// dem JFrame hinzugef�gt

		// mit .setText l�sst sich der Text eines TextFields, Buttons oder
		// Labels festlegen
		

		lbl1.setText("Datenbanken");
		frmApp.add(lbl1);
		
		lbl2.setText("Semester: 6");
		frmApp.add(lbl2);
		
		lbl3.setText("Prof. B. Bertelsmeier");
		frmApp.add(lbl3);
		
		lbl4.setText("Pr�fung: schriftlich");
		frmApp.add(lbl4);
		
		lbl5.setText("Voraussetzung: erfolgreich absolviertes Praktikum");
		frmApp.add(lbl5);
		
		lbl6.setText("");
		frmApp.add(lbl6);

	
		btn1.setText("Zur�ck");
		// Registrieren eines ActionListeners an den Button
		btn1.addActionListener(new clickBack());
		frmApp.add(btn1);

		// Sonderdinger
		btn1.setToolTipText("Button1");
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("test");

	}

	/* ----------- Wie der Action Listener funktioniert ----------- */
	/*
	 * 1. Klasse mit Action Listener erstellen 2. den ActionListener am Objekt
	 * registrieren [btn1.addActionListener(new click());] 3. Vorgang im
	 * ActionListener schreibne
	 */

	// Hier wird eine interne Klasse erstellt, die als Event-Empf�nger dient.
	// Interface "ActionListener" wird ben�tigt
	class clickBack implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			 new MeinZweitesFenster().setVisible(true);

		}

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}