package modele;
import java.util.ArrayList;
import java.util.List;

public class Choix extends Composant {
	
	private List<ActionConditionnee> actions;
	private List<Condition> conditions;
	private Emplacement extre;
	
	
	public Choix(String name){
		this.extre=Emplacement.intermediaire;
		this.name=name;
		this.actions = new ArrayList<ActionConditionnee>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void addAction(ActionConditionnee a){
		this.actions.add(a);
	}
	public void setPosition(Emplacement e){
		this.extre=e;
	}

	public List<ActionConditionnee> getActions() {
		return actions;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public Emplacement getExtre() {
		return extre;
	}

	
}
