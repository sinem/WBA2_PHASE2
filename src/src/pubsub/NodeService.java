package pubsub;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.Node;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;


public class NodeService {

	private static String host = "localhost";
	private static int port = 5222;
	
	private XMPPConnection conn;
	private PubSubManager mgr;
	private ServiceDiscoveryManager sdMgr;
	

	
	public void connect(String user) throws XMPPException{
		connect(host, port, user);
	}
	

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
	

	public void createNode(String nodeId) throws XMPPException{
		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setAccessModel(AccessModel.open);
		form.setDeliverPayloads(false);
		form.setNotifyRetract(true);
		form.setPersistentItems(true);
		form.setPublishModel(PublishModel.open);
		
		mgr.createNode(nodeId, form);
	}
	

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
	

	public void deleteNode(String nodeId) throws XMPPException{
		mgr.deleteNode(nodeId);
	}

	
	
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
	


}

