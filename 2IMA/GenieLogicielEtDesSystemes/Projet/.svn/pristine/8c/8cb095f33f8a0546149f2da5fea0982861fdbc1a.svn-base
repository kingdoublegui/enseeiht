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
	
	public Chemin (String name,Lieu depart,Lieu fin){
		this.name=name;
		this.obligation=Obligation.Facultatif;
		this.visibilite=Visibilite.Visible;
		this.ouverture=Ouverture.Ouvert;
		this.depart=depart;
		this.fin=fin;
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

	public void addObligation(Obligation o ){
		this.obligation=o;
	}
	public void addVisibilite(Visibilite v){
		this.visibilite=v;
	}
	public void addOuverture(Ouverture ouv){
		this.ouverture=ouv;
	}
	
}
