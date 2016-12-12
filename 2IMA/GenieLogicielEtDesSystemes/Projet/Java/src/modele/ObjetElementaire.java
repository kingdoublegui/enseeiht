package modele;
import java.util.ArrayList;
import java.util.List;

public class ObjetElementaire extends Composant{

	private int poids;
	private Visibilite visi;
	private Transformabilite trans;
	private List<Transformation> conditiontrans;
	private List<Condition> conditionvis;
	

	public ObjetElementaire(String name){
		this.name=name;
		this.visi=Visibilite.visible;
		this.conditionvis = new ArrayList<Condition>();
		this.conditiontrans = new ArrayList<Transformation>();
	}
	
	public void addConditionVisibilite(Condition c){
		this.conditionvis.add(c);
	}
	
	public void setVisibilite(Visibilite v){
		this.visi=v;
	}
	
	public void addConditionTransformabilite(Transformation t){
		this.conditiontrans.add(t);
	}
	public String getName(){
		return this.name;
	}
	
	public void setPoids(int p ){
		this.poids=p;
	}
	
	public void setTransformabilite(Transformabilite t){
		this.trans=t;
	}
	
	public int getPoids() {
		return poids;
	}

	public Visibilite getVisi() {
		return visi;
	}

	public Transformabilite getTrans() {
		return trans;
	}

	public List<Transformation> getConditiontrans() {
		return conditiontrans;
	}

	public List<Condition> getConditionvis() {
		return conditionvis;
	}
	
	public boolean equals(ObjetElementaire o) {
		return (this.name.equals(o.getName()) && this.poids==o.getPoids());
	}
}
