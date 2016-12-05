package modele;

import java.util.ArrayList;
import java.util.List;

public class ActionConditionnee {

	private Action action;
	private List<Condition> conditions;
	private List<Choix> choixprecedents;
	private List<ChoixConditionnee> choixofferts;
	
	public ActionConditionnee(Action a){
		this.action=a;
		this.conditions = new ArrayList<Condition>();
		this.choixofferts = new ArrayList<ChoixConditionnee>();
		this.choixprecedents = new ArrayList<Choix>();
	}
	
	
	public void addCOndition(Condition c){
		this.conditions.add(c);
	}
	
	public void addChoixoffert(ChoixConditionnee c){
		this.choixofferts.add(c);
	}
	
	public void addChoixprecedent(Choix c){
		this.choixprecedents.add(c);
	}
}
