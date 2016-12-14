package modele;
import java.util.ArrayList;
import java.util.List;

public class Chemin extends ComposantTerritoire {

	
	private Lieu depart;
	private Lieu fin;
	private Ouverture ouverture;
	private List<Condition> conditionsopen;
	private Obligation obligation;
	private Visibilite visibilite;
	private List<Condition> conditionsvis;
	private List<PossessionConditionnee> possessions;
	
	public Chemin (String name){
		this.name=name;
		this.obligation=Obligation.facultatif;
		this.visibilite=Visibilite.visible;
		this.ouverture=Ouverture.ouvert;
		this.conditionsopen = new ArrayList<Condition>();
		this.conditionsvis = new ArrayList<Condition>();
		this.possessions = new ArrayList<PossessionConditionnee>();		
	}
	
	public void addConditionOuverture(Condition c){
		this.conditionsopen.add(c);
	}
	
	public void addConditionVisibilite(Condition c){
		this.conditionsvis.add(c);
	}
	public void addPossessionConditionnee(PossessionConditionnee p){
		this.possessions.add(p);
	}

	public void setObligation(Obligation o ){
		this.obligation=o;
	}
	public void setVisibilite(Visibilite v){
		this.visibilite=v;
	}
	public void setOuverture(Ouverture ouv){
		this.ouverture=ouv;
	}
	
	public void setDepart(Lieu depart){
		this.depart=depart;
	}
	
	public void setFin(Lieu fin){
		this.fin=fin;
	}

	public Lieu getDepart() {
		return depart;
	}

	public Lieu getFin() {
		return fin;
	}

	public Ouverture getOuverture() {
		return ouverture;
	}

	public List<Condition> getConditionsopen() {
		return conditionsopen;
	}

	public Obligation getObligation() {
		return obligation;
	}

	public Visibilite getVisibilite() {
		return visibilite;
	}

	public List<Condition> getConditionsvis() {
		return conditionsvis;
	}

	public List<PossessionConditionnee> getPossessions() {
		return possessions;
	}
	public boolean isAccessible(){
		return(this.ouverture==Ouverture.ouvert);
	}
	public String getDescription(){
		return("le chemin "+this.name+" qui va de "+this.depart.getName()+" à "+this.fin.getName());
	}
}
