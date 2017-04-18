import egg.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.tam.ast.impl.TAMFactoryImpl;
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
            System.out.println("Fichier analysé : "+  args[0]);
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

                try{
                    String filename = args.length == 1 ?
                            args[0].substring(0, args[0].lastIndexOf('.')).concat(".tam") :
                            args[1];
                    PrintWriter writer = new PrintWriter(filename, "UTF-8");
                    bloc.get_ast().allocateMemory(Register.SB, 0);
                    TAMFactory factory = new TAMFactoryImpl();
                    Fragment code = bloc.get_ast().getCode(factory);
                    code.add(factory.createHalt());
                    writer.println(code);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
