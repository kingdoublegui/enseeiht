/** Afficheur infixe, complètement parenthésé, d'une expression arithmétique.
  *
  * @author	Xavier Crégut
  * @version	$Revision$
  */
public class CalculHauteur implements VisiteurExpression<Integer> {

	public Integer visiterAccesVariable(AccesVariable v) {
		return 1;
	}

	public Integer visiterConstante(Constante c) {
		return 1;
	}

	public Integer visiterExpressionBinaire(ExpressionBinaire e) {
		return e.getOperateur().accepter(this)
            + Math.max(e.getOperandeGauche().accepter(this),
               e.getOperandeDroite().accepter(this));
	}

	public Integer visiterAddition(Addition a) {
		return 1;
	}

	public Integer visiterMultiplication(Multiplication m) {
		return 1;
	}

	public Integer visiterSoustraction(Soustraction s) {
		return 1;
	}

	public Integer visiterExpressionUnaire(ExpressionUnaire e) {
		return e.getOperateur().accepter(this)
			+ e.getOperande().accepter(this);
	}

	public Integer visiterNegation(Negation n) {
		return 1;
	}

}
