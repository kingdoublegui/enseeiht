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
	
	public void addPossession(PossessionConditionnee p){
		this.possessions.add(p);
	}

	public void addObjetConsommes(Objet o){
		this.objetsconsommes.add(o);
	}
}
