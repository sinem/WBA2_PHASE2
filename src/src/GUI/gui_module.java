package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gui_module {

	JFrame frmApp = new JFrame("Application");

	JTextField txt1 = new JTextField("App");
	JButton btn1 = new JButton();
	JButton btn2 = new JButton();
	JButton btn3 = new JButton();
	JLabel lbl1 = new JLabel();
	JComboBox cmbx = new JComboBox();

	gui_module() {

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
		

		lbl1.setText("1.Semester");
		frmApp.add(lbl1);
		

		// Bei der ComboBox werden Items mit .addItem einzeln hinzugefügt

		cmbx.addItemListener(new classcombobox());

		frmApp.add(cmbx);
		cmbx.addItem("Alghoritmen und Programmierung I");
		cmbx.addItem("Einführung in die Medieninformatik");
		cmbx.addItem("Einführung in Betriebssysteme und Rechnerarchitektur");
		cmbx.addItem("Mathematik I");
		cmbx.addItem("Theoretische Informatik I");
		cmbx.addItem("Alghoritmen und Programmierung II");
		cmbx.addItem("Grunglagen der visuellen Kommunikation");
		cmbx.addItem("Medientechnik und -produktion");
		cmbx.addItem("Mathematik II");
		cmbx.addItem("Theoretische Informatik II");
		cmbx.addItem("Audiovisuelles Medienprojekt");

		
		btn2.setText("Abonnieren");
		// Registrieren eines ActionListeners an den Button
		btn2.addActionListener(new clickAbo());
		frmApp.add(btn2);

		// Sonderdinger
		btn2.setToolTipText("Button2");
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("test");
		
	
		btn1.setText("Fortsetzen");
		// Registrieren eines ActionListeners an den Button
		btn1.addActionListener(new clickForward());
		frmApp.add(btn1);

		// Sonderdinger
		btn1.setToolTipText("Button1");
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("test");
		
		btn3.setText("Zurück");
		// Registrieren eines ActionListeners an den Button
		btn3.addActionListener(new clickBack());
		frmApp.add(btn3);

		// Sonderdinger
		btn3.setToolTipText("Button3");
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
	
	class clickForward implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			 new guiModulbeschreibung().setVisible(true);
		}
	}
	
	class clickAbo implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			try {
				throw new Exception("Die Abonnierung war erfolgreich!");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class classcombobox implements ItemListener {

		public void itemStateChanged(ItemEvent e) {

			txt1.setText(cmbx.getSelectedItem().toString());

		}

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}