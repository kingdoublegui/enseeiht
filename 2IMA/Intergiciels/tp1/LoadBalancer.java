import java.net.Socket;
import java.net.ServerSocket;
import java.util.Random;

public class LoadBalancer extends Thread {
    static String hosts[] = {"localhost", "localhost"};
    static int ports[] = {8081,8082};
    static int nbHosts = 2;
    static Random rand = new Random();

    private Socket sin;
    private int iHost;

    public LoadBalancer(Socket socket, int iHost) {
        this.sin = socket;
        this.iHost = iHost;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket socket = serverSocket.accept();
                new LoadBalancer(socket, rand.nextInt(nbHosts)).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        try {
            Socket sout = new Socket(hosts[iHost], ports[iHost]);
            byte[] buffer = new byte[1024];
            int size = sin.getInputStream().read(buffer);
            sout.getOutputStream().write(buffer, 0, size);
            size = sout.getInputStream().read(buffer);
            sin.getOutputStream().write(buffer, 0, size);
            sin.close();
            sout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
