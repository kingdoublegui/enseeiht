package egg;
import mg.egg.eggc.runtime.libjava.ISourceUnit;
import mg.egg.eggc.runtime.libjava.SourceUnit;
import mg.egg.eggc.runtime.libjava.problem.IProblem;
import mg.egg.eggc.runtime.libjava.problem.ProblemReporter;
import mg.egg.eggc.runtime.libjava.problem.ProblemRequestor;
import java.io.*;
public class BlocC implements Serializable {
 	private static final long serialVersionUID = 1L;
  public static void main(String[] args){
    System.err.println("version " + "0.0.1");
    try {
      ISourceUnit cu = new SourceUnit(args[0]);
      ProblemReporter prp = new ProblemReporter(cu);
      ProblemRequestor prq = new ProblemRequestor(false);
      //new EGGOptionsAnalyzer(cu).analyse();
      Bloc compilo = new Bloc(prp);
      prq.beginReporting();
      compilo.set_eval(true);
      compilo.compile(cu);
      for(IProblem problem : prp.getAllProblems())
      	prq.acceptProblem(problem );
      prq.endReporting();
      System.exit(0);
      }
    catch(Exception e){
      e.printStackTrace();
      System.exit(1);
      }
    }
  }
