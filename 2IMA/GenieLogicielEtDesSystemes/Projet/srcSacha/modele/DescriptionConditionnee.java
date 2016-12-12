package modele;
import java.util.ArrayList;
import java.util.List;

public class DescriptionConditionnee {

	private String texte;
	private List<Condition> conditions;
	
	public DescriptionConditionnee(String texte){
		this.texte=texte;
		this.conditions = new ArrayList<Condition>();
	}
	
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
		
}
