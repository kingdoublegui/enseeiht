import java.util.PriorityQueue;
import java.util.Comparator;

public class Daemon extends Thread {

    private int ticket = -1;
    private PriorityQueue<Update> att = new PriorityQueue<Update>(1, new CompUpdate());

	public void run() {
		while (true) {
			Update update = ClientImpl.group.receiveUpdate();
			synchronized (ClientImpl.data) {
                att.add(update);
                if (ticket == -1) {
                    ticket = update.ticket;
                }
                try {
                if (update.ticket != ticket+1) {
                    ClientImpl.data.wait();
                } else {
                    ClientImpl.data.notifyAll();
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
               while (!att.isEmpty()) {
                    Update u = att.poll();
				    if (u.op == Update.ADD) ClientImpl.data[u.index] += u.val;
				    if (u.op == Update.MUL) ClientImpl.data[u.index] *= u.val;
                }
			}
		}
	}

    private class CompUpdate implements Comparator<Update> {
        public int compare(Update a, Update b) {
            return (a.ticket < b.ticket) ? -1 : ((a.ticket == b.ticket) ? 0 : 1);
        }
    }
}
