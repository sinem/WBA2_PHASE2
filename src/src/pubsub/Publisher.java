package pubsub;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.SimplePayload;


public class Publisher {

	private static String host = "localhost";
	private static int port = 5222;

	private XMPPConnection conn;
	private PubSubManager mgr;
	

	
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

	
	public void addMessage(String messageId, String nodeId) throws XMPPException {
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		node.publish(new Item(messageId));
		System.out.println("Item wurde erzeugt.");
	}
	
	
	public void addPayloadMessage(String messageId, String nodeId) throws XMPPException {
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		SimplePayload payload = new SimplePayload("collection", null, "<collection><items><drumItem typ=\"" + nodeId);
		PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>(messageId, payload);
		node.publish(item);
		System.out.println("Item wurde erzeugt.");
	}
	
	
	public void deleteMessage(String messageId, String nodeId) throws XMPPException{
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		node.deleteItem(messageId);
	}
}
