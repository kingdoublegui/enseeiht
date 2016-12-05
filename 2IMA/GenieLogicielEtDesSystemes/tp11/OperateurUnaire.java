/**
  * Opérateur unaire.
  *
  * @author	Xavier Crégut
  * @version	$Revision$
  */
public interface OperateurUnaire {

	<R> R accepter(VisiteurExpression<R> visiteur);

}
