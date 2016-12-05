import java.io.Serializable;


public class Target implements Serializable {
	private static final long serialVersionUID = 1L;
	public Target(String host, int port) {
		this.host = host;
		this.port = port;
	}
	public String host;
	public int port;
}
