package pubsub;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.Affiliation;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.Node;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.Subscription;

/**
 * Diese Klasse dient zum Erzeugen, Verwalten, Ändern und Löschen der Knotenpunkte.
 *
 */
public class NodeService {

	private static String host = "localhost";
	private static int port = 5222;
	
	private XMPPConnection conn;
	private PubSubManager mgr;
	private ServiceDiscoveryManager sdMgr;
	
	/**
	 * Aufbauen einer Verbindung mit dem Openfire-Server der local verwendet wird.
	 * Zusätzlich wird direkt ein User eingeloggt. 
	 * @param user, Nutzername 
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
	 * @param pwd, Passwort zum Einloggen
	 * @throws XMPPException
	 */
	public void connect(String host, int port, String user) throws XMPPException{
		ConnectionConfiguration config = new ConnectionConfiguration(host, port);
		this.conn = new XMPPConnection(config);
		conn.connect();
		conn.login(user, host);
		this.mgr = new PubSubManager(conn, "pubsub." + host);
	}

	/**
	 * Trennen der Verbindung zum Server.
	 */
	public void disconnect(){
		conn.disconnect();
	}
	
	/**
	 * Diese Methode holt sich alle bekannten Knotenpunkte und gibt diese in einer Liste zurück.
	 * @return Liste mit allen bekannten Knotenpunkten.
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
	
	/**
	 * Diese Methode erzeugt einen neuen Knoten ohne Payload.
	 * @param nodeId, einzigartige KnotenID
	 * @throws XMPPException
	 */
	public void createNode(String nodeId) throws XMPPException{
		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setAccessModel(AccessModel.open);
		form.setDeliverPayloads(false);
		form.setNotifyRetract(true);
		form.setPersistentItems(true);
		form.setPublishModel(PublishModel.open);
		
		mgr.createNode(nodeId, form);
	}
	
	/**
	 * Diese Methode erzeugt einen neuen Knoten der Payload zurückliefert.
	 * @param nodeId, einzigartige KnotenID
	 * @throws XMPPException
	 */
	public void createPayloadNode(String nodeId) throws XMPPException{
		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setAccessModel(AccessModel.open);
		form.setDeliverPayloads(true);
		form.setNotifyRetract(true);
		form.setPersistentItems(true);
		form.setPublishModel(PublishModel.open);
		
		Node node = mgr.createNode(nodeId, form);
		node.addItemDeleteListener(new ItemDeleteCoordinator<Item>());
		node.addItemEventListener(new ItemEventCoordinator<Item>());
	}
	
	/**
	 * Diese Methode löscht den angegebenen Konten.
	 * @param nodeId, einzigartige KnotenID
	 * @throws XMPPException
	 */
	public void deleteNode(String nodeId) throws XMPPException{
		mgr.deleteNode(nodeId);
	}
	
	/**
	 * Diese Methode ändert die Konfiguration des genannten Knotens.
	 * @param nodeId, einzigartige KnotenID
	 * @throws XMPPException
	 */
	public void configureNode(String nodeId) throws XMPPException{
		LeafNode node = (LeafNode) mgr.getNode(nodeId);
		
		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setAccessModel(AccessModel.open);
		form.setDeliverPayloads(true);
		form.setNotifyRetract(true);
		form.setPersistentItems(false);
		form.setPublishModel(PublishModel.open);
		
		node.sendConfigurationForm(form);
	}

	/**
	 * 
	 * @param typ
	 * @throws XMPPException
	 */
	public void printNode(String typ) throws XMPPException{
		LeafNode node = (LeafNode) mgr.getNode(typ);
		System.out.println("Knoten-ID: " + node.getId());
		System.out.println("Item-Liste: " + node.getItems());
		System.out.println("Payload von Element 0: " + node.getItems().get(0).toXML());
		System.out.println("Knoten-Konfig: " + node.getNodeConfiguration());
	}
	
	/**
	 * Diese Methode gibt alle Subscriptions und Affiliations aus der Liste aus.
	 * @throws XMPPException
	 */
	public void checkSubscAndAff() throws XMPPException{
		List<Subscription> subscriptions = mgr.getSubscriptions();
		System.out.println(subscriptions.toString());
		
		List<Affiliation> affiliations = mgr.getAffiliations();
		System.out.println(affiliations.toString());
	}
	
	/**
	 * Diese Methode löscht alle Subsciptions und Affiliations aus den Listen.
	 * @throws XMPPException
	 */
	public void clearSubscAndAff() throws XMPPException{
		List<Subscription> subscriptions = mgr.getSubscriptions();
		subscriptions.clear();
		
		List<Affiliation> affiliations = mgr.getAffiliations();
		affiliations.clear();
	}
	
	/**
	 * Diese Methode gibt die vorhandenen Entitäten mit Konten und dem zugehörigem Namen aus.
	 * @throws XMPPException
	 */
	public void printItemsOfXMPPEntity() throws XMPPException{
		DiscoverItems discoItems = sdMgr.discoverItems(host);
		Iterator<DiscoverItems.Item> it = discoItems.getItems();
		while (it.hasNext()) {
			DiscoverItems.Item item = (DiscoverItems.Item) it.next();
			System.out.println(item.getEntityID() +";\t"+ item.getNode() +";\t"+ item.getName());
		}
	}
	
	/**
	 * Diese Methode gibt die Servicenamen mit Typ und Kategory aus.
	 * @throws XMPPException
	 */
	public void printIdentitiesOfXMPPEntity() throws XMPPException{
		DiscoverInfo discoInfo = sdMgr.discoverInfo(host);
		Iterator<DiscoverInfo.Identity> it = discoInfo.getIdentities();
		while (it.hasNext()) {
			DiscoverInfo.Identity identity = (DiscoverInfo.Identity) it.next();
			System.out.println(identity.getName() +";\t"+ identity.getType() +";\t"+ identity.getCategory());
		}
	}
}
