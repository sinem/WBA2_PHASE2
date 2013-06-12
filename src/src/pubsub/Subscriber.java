package pubsub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PubSubManager;

/**
 * Diese Klasse stellt alle notwendigen Methoden für den Subscriber bei dem PubSub-Paradigma zur Verfügung, um den Publisher testen und prüfen zu können.
 *
 */
public class Subscriber {

	private static String host = "localhost";
	private static int port = 5222;

	private XMPPConnection conn;
	private PubSubManager mgr;
	private ServiceDiscoveryManager sdMgr;
	
	/**
	 * Aufbauen einer Verbindung mit dem Openfire-Server der local verwendet wird.
	 * Zusätzlich wird direkt ein User mit entsprechendem Passwort eingeloggt. 
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
		this.mgr = new PubSubManager(conn, "pubsub.localhost");
	}

	/**
	 * Trennen der Verbindung zum Server.
	 */
	public void disconnect(){
		conn.disconnect();
	}
	
	/**
	 * Diese Methode ist für das abonnieren von Nachrichten eines Knotens zuständing.
	 * @param nodeId, eindeutige KnotenID
	 * @param user, Nutzername des Abonnenten
	 * @throws XMPPException
	 */
	public void abonnieren(String nodeId, String user) throws XMPPException{
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		node.addItemEventListener(new ItemEventCoordinator<Item>());
		node.addItemDeleteListener(new ItemDeleteCoordinator<Item>());
		node.subscribe(user + "@" + host);
	}
	
	/**
	 * Diese Methode gibt das unter Position zu findende Item in Klartest aus. 
	 * @param nodeId, eindeutige KnotenID
	 * @param posId, Position des Items in der Knotenliste 
	 * @throws XMPPException
	 */
	public void printMessage(String nodeId, int posId) throws XMPPException{
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		node.getItems().get(posId);
	}
	
	/**
	 * Diese Methode gibt alle vorhandenen Knoten in einer Liste zurück, die unter der bestehenden Verbindung zu finden sind.
	 * @return Liste mit allen vorhandenen Knoten unter bestehender Verbindung
	 * @throws XMPPException
	 */
	public List<String> getNodes() throws XMPPException{
		this.sdMgr = ServiceDiscoveryManager.getInstanceFor(conn);
		List<String> list = new ArrayList<String>();
		for (Iterator<DiscoverItems.Item> iterator = sdMgr.discoverItems("pubsub." + host).getItems(); iterator.hasNext();) {
			DiscoverItems.Item item = (DiscoverItems.Item) iterator.next();
			list.add(item.getNode());
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		return list;
	}
	
}
