package modele;
import java.util.ArrayList;
import java.util.List;

public class Condition extends Composant {

	private List<Comparaison> comparaisons;
	private List<Condition> conditions;
	private Difficulte diff;
	private int proba;
	
	public Condition(String name,int proba,Difficulte diff){
		this.name=name;
		this.proba=proba;
		this.diff=diff;
		this.comparaisons = new ArrayList<Comparaison>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public void addComparaison(Comparaison c){
		this.comparaisons.add(c);
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
}
