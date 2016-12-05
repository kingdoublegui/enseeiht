package modele;
import java.util.ArrayList;
import java.util.List;

public class Chemin extends ComposantTerritoire {

	
	private Lieu depart;
	private Lieu fin;
	private Ouverture ouverture;
	private List<ConditionOuverture> conditionsopen;
	private Obligation obligation;
	private Visibilite visibilite;
	private List<ConditionVisibilite> conditionsvis;
	private List<PossessionConditionnee> possessions;
	
	public Chemin (Lieu depart,Lieu fin,Ouverture ouv,Obligation obli, Visibilite visi){
		this.depart=depart;
		this.fin=fin;
		this.ouverture=ouv;
		this.obligation=obli;
		this.visibilite=visi;
		this.conditionsopen = new ArrayList<ConditionOuverture>();
		this.conditionsvis = new ArrayList<ConditionVisibilite>();
		this.possessions = new ArrayList<PossessionConditionnee>();		
	}
	
	public void addConditionOuverture(ConditionOuverture c){
		this.conditionsopen.add(c);
	}
	
	public void addConditionVisibilite(ConditionVisibilite c){
		this.conditionsvis.add(c);
	}
	public void addPossessionConditionnee(PossessionConditionnee p){
		this.possessions.add(p);
	}

}
