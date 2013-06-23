package GUI;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class gui_news {

	JFrame frmApp = new JFrame("Application");

	JTextField txt1 = new JTextField("App");
	JButton btn1 = new JButton();

	gui_news() {

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
		
		
		// Array für unsere JList
        String interessen[] = {"Am 28.Juni findet kein TI statt.", "Die AP-Praktikum beginnt am 01.09."};
 
        //JList mit Einträgen wird erstellt
        JList themenAuswahl = new JList(interessen);
 
        frmApp.add(themenAuswahl);
		
		
		
		btn1.setText("Zurück");
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

	// Hier wird eine interne Klasse erstellt, die als Event-Empfänger dient.
	// Interface "ActionListener" wird benötigt
	class clickBack implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			 new MeinZweitesFenster().setVisible(true);

		}

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}

