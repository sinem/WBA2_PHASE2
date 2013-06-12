package pubsub;


import org.jivesoftware.smack.XMPPException;

/**
 * Diese Klasse wird für die Tests verwendet. Man erzeugt einen NodeService und beliebig viele Publisher und Subscriber.
 * Danach kann man notwendige Operationen per Methoden durchführen und ein Publish-Subscribe-Paradigma realisieren. 
 *
 */
public class main {

	/**
	 * User
	 */
	private static String ups = "pubsubservice";
	private static String pps = "pubsub";
	private static String u1 = "user1";
	private static String u3 = "user3";

	
	public static void main(String[] args) throws XMPPException {

		/**
		 * Erzeugen von Instanzen
		 */
		NodeService service = new NodeService();
		Publisher pub = new Publisher();
		Subscriber sub = new Subscriber();

		/**
		 * NodeService erstellt/löscht/verändert Knoten und lässt sich Informationen ausgeben
		 */
		service.connect(ups);
		service.getNodes();
		service.printNode("Bruno");
		System.out.println("");
		service.printItemsOfXMPPEntity();
		System.out.println("");
		service.printIdentitiesOfXMPPEntity();
		service.disconnect();
		
		System.out.println("");
		
		
		/**
		 * Subscriber prüft nach neuen Nachrichten
		 *
		 * Publisher löscht/erstellt neue Nachricht
		 */
		pub.connect(u1);
		pub.deleteMessage("1", "Bruno");
		pub.addPayloadMessage("1", "Bruno", "Hi", 123123, 5.0f);
		pub.disconnect();
		
		System.out.println("");
		
		/*
		 * Subscriber lässt sich alle Knoten ausgeben und abonniert einen Knoten
		 */
		sub.connect(u3);
		sub.getNodes();
		sub.abonnieren("Bruno", u3);
		sub.disconnect();
		
		System.out.println("");
		
		/**
		 * NodeService lässt sich Infomationen ausgeben
		 */
		service.connect(ups);
		service.printNode("Bruno");
		service.disconnect();
		
	}
}
