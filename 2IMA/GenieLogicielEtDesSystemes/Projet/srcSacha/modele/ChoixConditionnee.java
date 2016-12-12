package modele;

import java.util.ArrayList;
import java.util.List;

public class ChoixConditionnee {

	private Choix choix;
	private List<Choix> choixprecedents;
	private List<Condition> conditions;
	
	public ChoixConditionnee(Choix choix){
		this.choix=choix;
		this.choixprecedents = new ArrayList<Choix>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public void addContion(Condition c){
		this.conditions.add(c);
	}
	
	public void addChoixPrecedent(Choix c){
		this.choixprecedents.add(c);
	}
	
}
