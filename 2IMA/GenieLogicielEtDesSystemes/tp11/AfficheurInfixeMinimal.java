/** Afficheur infixe, complètement parenthésé, d'une expression arithmétique.
  *
  * @author	Xavier Crégut
  * @version	$Revision$
  */
public class AfficheurInfixeMinimal extends AfficheurInfixe {

    OperateurBinaire op2 = null;
    OperateurUnaire op1 = null;

	public String visiterAccesVariable(AccesVariable v) {
		return v.getNom();
	}

	public String visiterConstante(Constante c) {
		return String.valueOf(c.getValeur());
	}

	public String visiterExpressionBinaire(ExpressionBinaire e) {
        String res = "";
        boolean parentheses = (op2 != null
            && op2 instanceof Multiplication
            && e.getOperateur() instanceof Addition)
            || (op1 != null
            && op1 instanceof Negation
            && e.getOperateur() instanceof Addition);
        op1 = null;
	    op2 = e.getOperateur();
        if (parentheses)
            res += "(";
        res += e.getOperandeGauche().accepter(this)
			+ " " + e.getOperateur().accepter(this)
			+ " " + e.getOperandeDroite().accepter(this);
        if (parentheses)
            res += ")";
        return res;

	}

	public String visiterExpressionUnaire(ExpressionUnaire e) {
        op1 = e.getOperateur();
        op2 = null;
		return e.getOperateur().accepter(this)
			+ " " + e.getOperande().accepter(this);
	}

}
