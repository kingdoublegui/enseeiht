import egg.*;
import mg.egg.eggc.runtime.libjava.ISourceUnit;
import mg.egg.eggc.runtime.libjava.SourceUnit;
import mg.egg.eggc.runtime.libjava.problem.IProblem;
import mg.egg.eggc.runtime.libjava.problem.ProblemReporter;
import mg.egg.eggc.runtime.libjava.problem.ProblemRequestor;
import java.io.*;

public class Main{

  public static void main (String[] args){
  try {
      ISourceUnit cu = new SourceUnit(args[0]);
      System.out.println("Fichier analyé : "+  args[0]);
      ProblemReporter prp = new ProblemReporter(cu);
      ProblemRequestor prq = new ProblemRequestor(true);
      Bloc bloc = new Bloc(prp);
      prq.beginReporting();
      bloc.set_eval(true);
      bloc.compile(cu);
      for(IProblem problem : prp.getAllProblems())
      	prq.acceptProblem(problem );
      prq.endReporting();
      System.out.println("AST :"+bloc.get_ast());
      if (bloc.get_ast().checkType()) {
	  System.out.println( "Correctement typé." );
      } else {
	  System.out.println( "Mal typé." );
      }
      System.exit(0);
      }
    catch(Exception e){
      e.printStackTrace();
      System.exit(1);
      }
    }
}
