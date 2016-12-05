package modele;

import java.util.ArrayList;
import java.util.List;

public class ConditionAutre extends Condition {

	private List<Comparaison> comparaisons;
	private List<Condition> conditions;
	private Difficulte diff;
	private int proba;
	
	public ConditionAutre(String name){
		this.name=name;
		this.proba=100;
		this.comparaisons = new ArrayList<Comparaison>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public void addComparaison(Comparaison c){
		this.comparaisons.add(c);
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void addProba(int p){
		this.proba=p;
	}
	
	public void addDifficulte(Difficulte d){
		this.diff=d;
	}
}
