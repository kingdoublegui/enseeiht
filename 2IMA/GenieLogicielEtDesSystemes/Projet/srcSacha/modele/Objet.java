package modele;

public class Objet extends Possession{

	
	private int quantite;
	private ObjetElementaire objet;
	
	public void setObjet(ObjetElementaire o){
		this.name=o.getName();
		this.objet=o;
	}
	
	public void setQuantite(int q){
		this.quantite=q;
	}

	public int getQuantite() {
		return quantite;
	}

	public ObjetElementaire getObjet() {
		return objet;
	}
}
