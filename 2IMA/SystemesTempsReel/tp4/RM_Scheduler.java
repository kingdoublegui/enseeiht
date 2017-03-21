import storm.Schedulers.Scheduler; 
import storm.*; 
import storm.Processors.*; 
import storm.Tasks.*;
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator;

public class RM_Scheduler extends Scheduler {
    class LReady extends ArrayList implements Comparator { 
	public int compare(Object arg0, Object arg1) {
	    Task T0 = (Task) arg0; 
	    Task T1 = (Task) arg1; 
	    int d0 = T0.getOwnFieldIntValue("next_period"); 
	    int d1 = T1.getOwnFieldIntValue("next_period"); 
	    if (d1 > d0) return -1; else if (d1 == d0) return 0;
	    else return 1;
	}
    } 
    
    private LReady list_ready; 
    private boolean todo = false;
    
    public void init() { 
	list_ready = new LReady();
    }

    public void onActivate(EvtContext c) { 
	Task T = (Task)c.getCible(); 
	T.setOwnFieldIntValue("next_period", T.getPeriod()); 
	list_ready.add(T); 
	todo = true;
    }

    public void onUnBlock(EvtContext c){ 
	Task T = (Task)c.getSource(); 
	if (T.isBegin()) {
        T.setOwnFieldIntValue("next_period", T.getPeriod()); 
	}
	list_ready.add(T); 
	todo = true;
    }

    public void onBlock(EvtContext c){ 
	list_ready.remove(c.getCible()); 
	todo = true;
    }


    public void onTerminated(EvtContext c){ 
	list_ready.remove(c.getCible()); 
	todo = true;
    }


    public void sched(){ 
	if (todo) {
	    select(); 
	    todo = false;
    
	}
    }


    public void select() { 
    Collections.sort(list_ready,list_ready);

    System.out.println("le mien");

    ArrayList CPUS =this.Kernel.getTasksListeManager().getProcessors(); 
    int m = CPUS.size();

    for (int i=m; i<list_ready.size(); i++){ 
        Task T = (Task) list_ready.get(i); 
        if (T.isIsrunning()) T.preempt();
    }

    int j = 0; 
    for (int i=0; (i<m) && (i<list_ready.size()); i++){
        Task T = (Task) list_ready.get(i); 
        if (!T.isIsrunning()) {
            Processor P = null; 
            for (; j<m; j++){
            P = (Processor) CPUS.get(j); 
            if (!P.isRunning()) {
                j++; 
                break;
            }
            }
            T.runningOn(P);
        }
    }
    }
}
