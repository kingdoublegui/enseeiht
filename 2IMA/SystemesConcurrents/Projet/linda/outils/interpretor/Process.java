package outils.interpretor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import linda.Callback;
import linda.Linda;
import linda.Linda.eventMode;
import linda.Linda.eventTiming;
import linda.Tuple;

public class Process extends Thread implements Callback {

	private String[] action;
	private String name;
	private Linda linda;
	private boolean waiting;

	public Process(String name, Linda linda) {
		super();
		this.name = name;
		this.linda = linda;
	}

	private volatile Boolean running;

    public void run() {
        running = true;
        while(running) {
            running = executeAction();
            if (Thread.interrupted()) {
                return;
            }
        }
    }

    public boolean executeAction() {
    	if (action == null)
    		return true;
    	
    	List<Tuple> tuples = new ArrayList<>();
		Tuple tuple;
    	switch (action[0]) {
    	case "write":
    		tuple = Tuple.valueOf(action[1].trim());
    		linda.write(tuple);
    		System.out.println(name + ": Ecriture de " + tuple + "effectuee");
    		break;
    	case "take":
    		tuple = Tuple.valueOf(action[1].trim());
    		tuples.add(linda.take(tuple));
    		break;
    	case "read":
    		tuple = Tuple.valueOf(action[1].trim());
    		tuples.add(linda.read(tuple));
    	case "tryRead":
    		tuple = Tuple.valueOf(action[1].trim());
    		tuples.add(linda.tryRead(tuple));
    		break;
    	case "takeAll":
    		tuple = Tuple.valueOf(action[1].trim());
    		tuples.addAll(linda.takeAll(tuple));
    		break;
    	case "readAll":
    		tuple = Tuple.valueOf(action[1].trim());
    		tuples.addAll(linda.readAll(tuple));
    		break;
    	case "eventRegister":
    		String[] tokens = action[1].split("\\s");
    		eventMode mode = tokens[0].toLowerCase().equals("read") ? eventMode.READ : eventMode.TAKE;
    		eventTiming timing = tokens[1].toLowerCase().equals("future") ? eventTiming.FUTURE : eventTiming.IMMEDIATE;
    		String tupleString = String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length));
    		tuple = Tuple.valueOf(String.join(" ", tupleString));
    		linda.eventRegister(mode, timing, tuple, this);
    		System.out.println(name + ": Demande de " + tuple + "effectuee");
    		break;
    	case "terminate":
    		System.out.println("Processus termine");
    		return false;
    	};
    	
    	waiting = false;
    	
    	for (Tuple curTuple : tuples) {
    		System.out.println(name + ": " + curTuple);
    	}
    	action = null;
    	return true;
    	
    }
    
    public void wakeUp() {
    	synchronized (running) {
    		running.notify();
    	}
    }
    
    public void setAction(String[] action) {
    	if (waiting) {
    		System.out.println(name + " is waiting for " + action[0]);
    	} else {
			waiting = true;
    		switch (action[0]) {
        	case "write":
        	case "take":
        	case "read":
        	case "tryTake":
        	case "tryRead":
        	case "takeAll":
        	case "readAll":
        		try {
        			Tuple.valueOf(action[1].trim());
        		} catch (linda.TupleFormatException e) {
        			System.out.println("Tuple \"" + action[1].trim() + "\" is not valid");
        			action = null;
        		}
        		break;
        	case "eventRegister":
        		String[] tokens = action[1].split("\\s");
        		if (!(tokens[0].toLowerCase().equals("read") || tokens[0].toLowerCase().equals("take"))) {
        			System.out.println("Event Mode \"" + tokens[0] + "\" is not valid");
        			action = null;
        			break;
        		}
        		if (!(tokens[1].toLowerCase().equals("future") || tokens[1].toLowerCase().equals("immediate"))) {
        			System.out.println("Event Timing \"" + tokens[1] + "\" is not valid");
					action = null;
					break;
        		}
        		String tupleString = String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length));
        		try {
        			Tuple.valueOf(String.join(" ", tupleString));
        		} catch (linda.TupleFormatException e) {
        			System.out.println("Tuple \"" + tupleString + "\" is not valid");
        		}
        		break;
        	};
    		this.action = action;
    		if (action == null) {
				waiting = false;
			}
    	}
    }

    public boolean isWaiting() {
    	return waiting;
    }
    
	@Override
	public void call(Tuple tuple) {
		System.out.println("<callback> " + name + ": " + tuple);
	}
	
}
