import java.io.Serializable;


public interface Group extends Serializable {
	public void init();
	public void sendUpdate(Update u);
	public Update receiveUpdate();
}
