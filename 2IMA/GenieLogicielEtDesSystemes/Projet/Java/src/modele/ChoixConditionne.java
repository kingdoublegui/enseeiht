package modele;

import java.util.ArrayList;
import java.util.List;

public class ChoixConditionne {

	private Choix choix;
	private List<Choix> choixprecedents;
	private List<Condition> conditions;
	
	public ChoixConditionne(){
		this.choixprecedents = new ArrayList<Choix>();
		this.conditions = new ArrayList<Condition>();
	}
	
	public void addContion(Condition c){
		this.conditions.add(c);
	}
	
	public void addChoixPrecedent(Choix c){
		this.choixprecedents.add(c);
	}
	
	public void setChoix(Choix c){
		this.choix=c;
	}

	public Choix getChoix() {
		return choix;
	}

	public List<Choix> getChoixprecedents() {
		return choixprecedents;
	}

	public List<Condition> getConditions() {
		return conditions;
	}
	
	
}
