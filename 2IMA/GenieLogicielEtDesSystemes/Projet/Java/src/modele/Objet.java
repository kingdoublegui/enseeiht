package modele;

public class Objet extends Possession{

	
	private int quantite;
	private ObjetElementaire objet;
	
	public Objet() {
		quantite = 0;
		objet = null;
	}
	
	public Objet(int qte, ObjetElementaire objet) {
		this.quantite = qte;
		this.objet = objet;
	}
	
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

	public int getPoids() {
		return (quantite*objet.getPoids());
	}

	public void addQuantite(int qte) {
		this.quantite += qte;
	}
}
