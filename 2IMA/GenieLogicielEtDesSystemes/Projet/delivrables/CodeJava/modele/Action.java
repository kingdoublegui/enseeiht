package modele;

import java.util.ArrayList;
import java.util.List;

public class Action extends Composant{
	
	private List<PossessionConditionnee> possessions;
	private List<Objet> objetsconsommes;
	
	public Action(String name){
		this.name=name;
		this.possessions = new ArrayList<PossessionConditionnee>();
		this.objetsconsommes = new ArrayList<Objet>();
	}
	
	public void addPossessionConditionnee(Possession p){
		 PossessionConditionnee c = new PossessionConditionnee();
		 c.setPossession(p);
		 this.possessions.add(c);
				 
	}
	
	public void addPossessionConditionnee(PossessionConditionnee p){
		this.possessions.add(p);
	}

	public void addObjetConsomme(Objet o){
		this.objetsconsommes.add(o);
	}

	public List<PossessionConditionnee> getPossessions() {
		return possessions;
	}

	public List<Objet> getObjetsconsommes() {
		return objetsconsommes;
	}
	
}
