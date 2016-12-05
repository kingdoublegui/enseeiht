package modele;
import java.util.ArrayList;
import java.util.List;

public  class Personne extends Composant {
	
	private Obligation obligation;
	private List<Choix> choix;
	private List<PossessionConditionnee> possessions;
	
	public Personne(String name, Obligation obligation){
		this.name=name;
		this.obligation=obligation;
		this.choix = new ArrayList<Choix>();
		this.possessions= new ArrayList<PossessionConditionnee>();
	}
	
	public void addChoix(Choix c){
		this.choix.add(c);
	}
	
	public void addPossession(PossessionConditionnee p){
		this.possessions.add(p);
	}

}
