/** Afficheur infixe, complètement parenthésé, d'une expression arithmétique.
  *
  * @author	Xavier Crégut
  * @version	$Revision$
  */

import java.util.Map;

public class EvaluateurExpression implements VisiteurExpression<Integer> {

    private Map<String, Integer> var;

    public EvaluateurExpression(Map<String, Integer> var) {
        this.var = var;
    }

	public Integer visiterAccesVariable(AccesVariable v) {
		if (var.containsKey(v.getNom()))
            return var.get(v.getNom());
        throw new RuntimeException("Variable inexistante: " + v.getNom());
	}

	public Integer visiterConstante(Constante c) {
		return c.getValeur();
	}

	public Integer visiterExpressionBinaire(ExpressionBinaire e) {
		switch (e.getOperateur().accepter(this)) {
            case 0:
                return e.getOperandeGauche().accepter(this) + e.getOperandeDroite().accepter(this);
            case 1:
                return e.getOperandeGauche().accepter(this) * e.getOperandeDroite().accepter(this);
            case 2:
                return e.getOperandeGauche().accepter(this) - e.getOperandeDroite().accepter(this);
            default:
                throw new RuntimeException("Operation non permise");
        }
	}

	public Integer visiterAddition(Addition a) {
		return 0;
	}

	public Integer visiterMultiplication(Multiplication m) {
		return 1;
	}

	public Integer visiterSoustraction(Soustraction s) {
		return 2;
	}

	public Integer visiterExpressionUnaire(ExpressionUnaire e) {
		switch (e.getOperateur().accepter(this)) {
            case 0:
                return -1 * e.getOperande().accepter(this);
            default:
                throw new RuntimeException("Operation non permise");
        }
	}

	public Integer visiterNegation(Negation n) {
		return 0;
	}

}
