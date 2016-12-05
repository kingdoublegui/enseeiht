

public class IncrConc {

    static private long cpt = 0L;
    static private int N;
    static String lock;

    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);

        for (int i = 0; i < N; i++) {
            Thread iThread = new Thread(new IncrThread());
            iThread.setName(Integer.toString(i));
            iThread.start();
        }
    }

    private static class IncrThread implements Runnable {
        public void run() {
            System.out.println("Debut Thread " + Thread.currentThread().getName() + ": " + cpt);
            for (int i = 0; i < Math.round(1000L/N); i++) {
                synchronized (lock) {
                    for (int j = 1; j <= 1000; j++) {
                        cpt = cpt + j/j;
                    }
                }
            }
            System.out.println("Fin Thread " + Thread.currentThread().getName() + ": " + cpt);
        }
    }

}
