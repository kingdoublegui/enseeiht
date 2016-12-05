package modele;
import java.util.ArrayList;
import java.util.List;

public class ObjetElementaire extends Composant{

	private int poid;
	private Visibilite visi;
	private Transformabilite trans;
	private List<Transformation> conditiontrans;
	private List<ConditionVisibilite> conditionvis;
	

	public ObjetElementaire(String name, int poid, Visibilite visi){
		this.poid=poid;
		this.name=name;
		this.visi=visi;
		this.conditionvis = new ArrayList<ConditionVisibilite>();
		this.conditiontrans = new ArrayList<Transformation>();
	}
	
	public void addConditionVisibilite(ConditionVisibilite c){
		this.conditionvis.add(c);
	}
	
	public void addTransformation(Transformation t){
		this.conditiontrans.add(t);
	}
}
