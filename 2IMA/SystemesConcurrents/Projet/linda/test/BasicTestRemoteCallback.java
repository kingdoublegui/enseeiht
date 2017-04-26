
package test;

import linda.AsynchronousCallback;
import linda.Callback;
import linda.Linda;
import linda.Linda.eventMode;
import linda.Linda.eventTiming;
import linda.Tuple;

public class BasicTestRemoteCallback {

    private static class MyCallback implements Callback {
        public void call(Tuple t) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("Got "+t);
        }
    }

    public static void main(String[] a) {
        //Linda linda = new shm.CentralizedLinda();
        Linda linda = new shm.server.LindaClient("//localhost:4000/MonServeur");

        Tuple motif = new Tuple(Integer.class, String.class);
        MyCallback callback = new MyCallback();
        linda.eventRegister(eventMode.TAKE, eventTiming.IMMEDIATE, motif, callback);

        Tuple t1 = new Tuple(4, 5);
        System.out.println("(2) write: " + t1);
        linda.write(t1);

        Tuple t2 = new Tuple("hello", 15);
        System.out.println("(2) write: " + t2);
        linda.write(t2);
        linda.debug("(2)");

        Tuple t3 = new Tuple(4, "foo");
        System.out.println("(2) write: " + t3);
        linda.write(t3);

        linda.debug("(2)");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- Fin ---");
    }

}
