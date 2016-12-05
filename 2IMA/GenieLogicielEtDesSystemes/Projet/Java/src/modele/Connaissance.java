package modele;
import java.util.ArrayList;
import java.util.List;

public class Connaissance extends Possession{

	
	private Visibilite visi;
	private List<Condition> conditionsvisi;
	
	public Connaissance(String name){
		this.name=name;
		this.visi=Visibilite.Visible;
		this.conditionsvisi = new ArrayList<Condition>();
	}
	
	public void addConditionVisibilite(Condition c){
		this.conditionsvisi.add(c);
	}
	
	public void addVisibilite(Visibilite v){
		this.visi=v;
	}
}
