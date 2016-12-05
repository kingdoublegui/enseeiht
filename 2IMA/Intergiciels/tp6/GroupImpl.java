import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GroupImpl implements Group {
	
	private static final long serialVersionUID = 1L;
	
	private List<Target> targets = new ArrayList<Target>();
	private static DatagramSocket sock = null;
	private static final int buffsize = 4;

	public void init() {
		try {
			sock = new DatagramSocket(ClientImpl.localPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendUpdate(Update u) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(u);
			byte[] buff = baos.toByteArray();
			Iterator<Target> it = targets.iterator();
			while (it.hasNext()) {
				Target t = it.next();
				if (!(t.host.equals(ClientImpl.localHost)) || (t.port != ClientImpl.localPort)) {
					DatagramPacket p = new DatagramPacket(buff, buff.length, InetAddress.getByName(t.host), t.port);
					sock.send(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Update receiveUpdate() {
		try {
			byte buff[] = new byte[1000];
			DatagramPacket p = new DatagramPacket(buff, 1000);
			sock.receive(p);
			ByteArrayInputStream bais = new ByteArrayInputStream(buff);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Update u = (Update)ois.readObject();
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addClient(String host, int port) {
		targets.add(new Target(host,port));
	}

}


 