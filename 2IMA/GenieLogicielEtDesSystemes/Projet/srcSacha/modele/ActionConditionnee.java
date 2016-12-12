package modele;

import java.util.ArrayList;
import java.util.List;

public class ActionConditionnee {

	private Action action;
	private List<Condition> conditions;
	private List<Decision> decisionPrecedentes;
	private List<ChoixConditionne> choixofferts;
	
	public ActionConditionnee(){
		this.conditions = new ArrayList<Condition>();
		this.choixofferts = new ArrayList<ChoixConditionne>();
		this.decisionPrecedentes = new ArrayList<Decision>();
	}
	
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void addChoixOffert(ChoixConditionne c){
		this.choixofferts.add(c);
	}
	
	public void addDecision(Decision d){
		this.decisionPrecedentes.add(d);
	}
	public void setAction(Action a){
		this.action=a;
	}


	public Action getAction() {
		return action;
	}


	public List<Condition> getConditions() {
		return conditions;
	}


	public List<Decision> getDecisionPrecedentes() {
		return decisionPrecedentes;
	}


	public List<ChoixConditionne> getChoixofferts() {
		return choixofferts;
	}
	
}
