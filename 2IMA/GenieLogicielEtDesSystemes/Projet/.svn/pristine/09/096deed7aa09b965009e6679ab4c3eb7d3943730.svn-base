package modele;
import java.util.ArrayList;
import java.util.List;

public class Choix extends Composant {
	
	private List<ActionConditionnee> actions;
	private List<Condition> conditions;
	private Extremite extre;
	
	
	public Choix(String name){
		this.extre=Extremite.Intermediaire;
		this.name=name;
		this.actions = new ArrayList<ActionConditionnee>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void addActionConditionnee(ActionConditionnee a){
		this.actions.add(a);
	}
	public void addExtremite(Extremite e){
		this.extre=e;
	}

}
