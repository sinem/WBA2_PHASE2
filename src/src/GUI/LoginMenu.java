package GUI;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class LoginMenu {

	JFrame frmApp = new JFrame("Application");

	JTextField txt1 = new JTextField("App");
	JButton btn1 = new JButton();
	JLabel lbl1 = new JLabel();
	JLabel lbl2 = new JLabel();
	JComboBox cmbx = new JComboBox();
	JComboBox cmbx2 = new JComboBox();

	LoginMenu() {

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
		

		lbl1.setText("Hochschule");
		frmApp.add(lbl1);

		// Bei der ComboBox werden Items mit .addItem einzeln hinzugefügt

		cmbx.addItemListener(new classcombobox());

		frmApp.add(cmbx);
		cmbx.addItem("FH Köln");
		cmbx.addItem("RFH Köln");
		
		lbl2.setText("Studiengang");
		frmApp.add(lbl2);

		cmbx2.addItemListener(new classcombobox());

		frmApp.add(cmbx2);
		cmbx2.addItem("Medieninformatik");
		cmbx2.addItem("Allg. Informatik");
		
	
		btn1.setText("Fortsetzen");
		// Registrieren eines ActionListeners an den Button
		btn1.addActionListener(new click());
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

	// Hier wird eine interne Klasse erstellt, die als Event-Empfänger dient.
	// Interface "ActionListener" wird benötigt
	class click implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			 new MeinZweitesFenster().setVisible(true);

		}

	}

	class classcombobox implements ItemListener {

		public void itemStateChanged(ItemEvent e) {

			txt1.setText(cmbx.getSelectedItem().toString());

		}

	}
	
	

	public static void main(String[] args) {

		LoginMenu bla = new LoginMenu();

	}

}