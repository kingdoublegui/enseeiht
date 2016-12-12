package modele;
import java.util.List;

public class ActionConditionee {
	
	private Action action;
	private List<Condition> conditions;
	private List<Choix> choix;
	public void setAction(Action action) {
		this.action = action;
	}
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	public void setChoix(List<Choix> choix) {
		this.choix = choix;
	}
	public Action getAction() {
		return action;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public List<Choix> getChoix() {
		return choix;
	}

	
	
}
