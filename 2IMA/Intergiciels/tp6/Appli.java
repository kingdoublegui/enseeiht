import java.util.Random;

public class Appli extends Thread {

	private Random rand = new Random();
    private int ticket = -1;

	public void run() {
        try {
		for (int i = 0;i<200;i++) {
            int nextTicket = LauncherImpl.getTicket();
			int index = rand.nextInt(ClientImpl.nbData);
			int op = rand.nextInt(2);
			int val = rand.nextInt(3)+1;
			synchronized (ClientImpl.data) { 
                if (ticket != -1 && nextTicket != ticket+1) {
                    ClientImpl.data.wait();
                }
                ticket = nextTicket;
				if (op == Update.ADD) ClientImpl.data[index] += val;
				if (op == Update.MUL) ClientImpl.data[index] *= val;
			}
			ClientImpl.group.sendUpdate(new Update(index, op, val, nextTicket));
		}
		System.out.println("Application completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
