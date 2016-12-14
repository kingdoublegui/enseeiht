package modele;
import java.util.ArrayList;
import java.util.List;

public class Transformation {

	private List<Condition> conditions;
	private List<Objet> objets;
	
	public Transformation(){
		this.conditions = new ArrayList<Condition>();
		this.objets = new ArrayList<Objet>();
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void addObjet(Objet o){
		this.objets.add(o);
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public List<Objet> getObjets() {
		return objets;
	}
	
	public String getObjetString(){
		String res ="";
		for(Objet obj : this.objets){
			res=res+" "+obj.getQuantite()+" "+obj.getName()+" ";
		}
		return res;
	}
	
}
