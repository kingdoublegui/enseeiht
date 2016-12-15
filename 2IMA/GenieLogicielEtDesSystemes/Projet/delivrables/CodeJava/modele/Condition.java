package modele;


import java.util.ArrayList;
import java.util.List;

public class Condition extends Composant {

	private List<Comparaison> comparaisons;
	private List<Condition> conditions;
	private Difficulte diff;
	private Operateur op;
	private int proba;
	
	public Condition(String name){
		this.op=Operateur.et;
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
	
	public void setProbabilite(int p){
		this.proba=p;
	}
	
	public void setDifficulte(Difficulte d){
		this.diff=d;
	}
	public void setOperateur(Operateur o){
		this.op=o;
	}

	public List<Comparaison> getComparaisons() {
		return comparaisons;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public Difficulte getDiff() {
		return diff;
	}

	public Operateur getOp() {
		return op;
	}

	public int getProba() {
		return proba;
	}
	
}
