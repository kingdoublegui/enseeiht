package modele;

import java.util.ArrayList;
import java.util.List;

public class ActionConditionnee {

	private Action action;
	private List<Condition> conditions;
	private List<Decision> decisionPrecedentes;
	private List<ChoixConditionnee> choixofferts;
	
	public ActionConditionnee(Action a){
		this.action=a;
		this.conditions = new ArrayList<Condition>();
		this.choixofferts = new ArrayList<ChoixConditionnee>();
		this.decisionPrecedentes = new ArrayList<Decision>();
	}
	
	
	public void addCOndition(Condition c){
		this.conditions.add(c);
	}
	
	public void addChoixoffert(ChoixConditionnee c){
		this.choixofferts.add(c);
	}
	
	public void addChoixprecedent(Decision d){
		this.decisionPrecedentes.add(d);
	}
}
