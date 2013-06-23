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



public class Subscriber {

	private static String host = "localhost";
	private static int port = 5222;

	private XMPPConnection conn;
	private PubSubManager mgr;
	private ServiceDiscoveryManager sdMgr;
	
	
	public void connect(String user) throws XMPPException{
		connect(host, port, user);
	}
	
	/**
	 * Aufbauen einer Verbindung zum Server mit entsprechender Adresse.
	 * @param host, IP-Adresse des Servers zu dem Verbunden werden soll
	 * @param port, Portnummer des Servers zu der Verbunden werden soll
	 * @param user, Nutzername
	 * @throws XMPPException
	 */
	public void connect(String host, int port, String user) throws XMPPException{
		ConnectionConfiguration config = new ConnectionConfiguration(host, port);
		this.conn = new XMPPConnection(config);
		conn.connect();
		this.mgr = new PubSubManager(conn, "pubsub.localhost");
	}

	public void disconnect(){
		conn.disconnect();
	}
	

	public void abonnieren(String nodeId, String user) throws XMPPException{
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		node.addItemEventListener(new ItemEventCoordinator<Item>());
		node.addItemDeleteListener(new ItemDeleteCoordinator<Item>());
		node.subscribe(user + "@" + host);
	}
	
	
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
