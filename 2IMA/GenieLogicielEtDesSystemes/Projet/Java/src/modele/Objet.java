package modele;

public class Objet extends Possession{

	
	private int quantite;
	private ObjetElementaire objet;
	
	public Objet(String name,int q,ObjetElementaire o){
		this.name=name;
		this.quantite=q;
		this.objet=o;
	}
}
