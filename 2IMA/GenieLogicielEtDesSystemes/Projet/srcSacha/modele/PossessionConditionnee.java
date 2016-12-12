package modele;

import java.util.ArrayList;
import java.util.List;

public class PossessionConditionnee extends Possession {

	private Possession possession;
	private List<Condition> conditions;
	
	public PossessionConditionnee(){
		this.conditions=new ArrayList<Condition>();
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void setPossession(Possession p){
		this.possession=p;
	}

	public Possession getPossession() {
		return possession;
	}

	public List<Condition> getConditions() {
		return conditions;
	}
}
