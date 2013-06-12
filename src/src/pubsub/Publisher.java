package pubsub;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.SimplePayload;

/**
 * Diese Klasse stellt alle wesentlichen Methoden für den Publisher bei dem PubSub-Paradigma zur Verfügung.
 *
 */
public class Publisher {

	private static String host = "localhost";
	private static int port = 5222;

	private XMPPConnection conn;
	private PubSubManager mgr;
	
	/**
	 * Aufbauen einer Verbindung mit dem Openfire-Server der local verwendet wird.
	 * Zusätzlich wird direkt ein User eingeloggt. 
	 * @param user, Nutzername zum Einloggen
	 * @throws XMPPException
	 */
	public void connect(String user) throws XMPPException{
		connect(host, port, user);
	}
	
	/**
	 * Aufbauen einer Verbindung zum Server mit entsprechender Adresse.
	 * Zusätzlich wird direkt ein User mit entsprechendem Passwort eingeloggt.
	 * @param host, IP-Adresse des Servers zu dem Verbunden werden soll
	 * @param port, Portnummer des Servers zu der Verbunden werden soll
	 * @param user, Nutzername zum Einloggen
	 * @throws XMPPException
	 */
	public void connect(String host, int port, String user) throws XMPPException{
		ConnectionConfiguration config = new ConnectionConfiguration(host, port);
		this.conn = new XMPPConnection(config);
		conn.connect();
		this.mgr = new PubSubManager(conn, "pubsub." + host);
		
	}

	 /* Trennen der Verbindung zum Server.
	 */
	public void disconnect(){
		conn.disconnect();
	}
	
	/**
	 * Diese Methode fügt eine neue Nachricht ohne Payload zum entsprechenden Knoten hinzu.
	 * @param messageId, eindeutige NachrichtenID  
	 * @param nodeId, eindeutige KnotenID
	 * @throws XMPPException
	 */
	public void addMessage(String messageId, String nodeId) throws XMPPException {
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		node.publish(new Item(messageId));
		System.out.println("Item wurde erzeugt.");
	}
	
	/**
	 * Diese Methode fügt eine neue Nachricht mit xmlPayload zum entsprechenden Knoten hinzu.
	 * @param messageId, eindeutige NachrichtenID (sollte die drumItemID sein)
	 * @param nodeId, eindeutige KnotenID (muss der Typ des drumItem sein)
	 * @param hersteller, Info für Payload
	 * @param modelNr, Info für Payload
	 * @param preis, Info für Payload
	 * @throws XMPPException
	 */
	public void addPayloadMessage(String messageId, String nodeId, String hersteller, Integer modelNr, Float preis) throws XMPPException {
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		SimplePayload payload = new SimplePayload("collection", null, "<collection><items><drumItem typ=\"" + nodeId + "\" hersteller=\"" + hersteller + "\" modelNr=\"" + modelNr.toString() + "\" preis=\"" + preis.toString() + "\"></drumItem></items></collection>");
		PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>(messageId, payload);
		node.publish(item);
		System.out.println("Item wurde erzeugt.");
	}
	
	/**
	 * Diese Methode löscht die vorhandene Nachricht.
	 * @param messageId, eindeutige NachrichtenID
	 * @param nodeId, eindeutige KnotenID
	 * @throws XMPPException
	 */
	public void deleteMessage(String messageId, String nodeId) throws XMPPException{
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		node.deleteItem(messageId);
	}
}
