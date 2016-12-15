package modele;

public class ComparaisonConnaissance extends Comparaison{

	private Connaissance connaissance;
	
	public ComparaisonConnaissance(Connaissance c ){
		this.connaissance=c;
	}

	public Connaissance getConnaissance() {
		return connaissance;
	}
	
}
