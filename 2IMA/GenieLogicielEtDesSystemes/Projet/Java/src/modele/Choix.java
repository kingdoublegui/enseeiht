package modele;
import java.util.ArrayList;
import java.util.List;

public class Choix extends Composant {
	
	private List<ActionConditionnee> actions;
	private Extremite extremite;
	private List<Condition> conditions;
	
	
	public Choix(String name, Extremite extre){
		this.name=name;
		this.extremite=extre;
		this.actions = new ArrayList<ActionConditionnee>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void addActionConditionnee(ActionConditionnee a){
		this.actions.add(a);
	}

}
