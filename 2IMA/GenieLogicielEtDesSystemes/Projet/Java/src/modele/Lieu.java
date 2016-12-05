package modele;
import java.util.ArrayList;
import java.util.List;

public class Lieu extends ComposantTerritoire {

	private Extremite type;
	private List<Personne> personnes;
	private List<Condition> conditions;
	private List<DescriptionConditionnee> descriptions;
	private List<PossessionConditionnee> possession;
	
	public Lieu(String nom) {
		this.name=nom;
		this.type= Extremite.Intermediaire;
		this.personnes = new ArrayList<Personne>();
		this.conditions = new ArrayList<Condition>();
		this.descriptions = new ArrayList<DescriptionConditionnee>();
		this.possession = new ArrayList<PossessionConditionnee>();
	}
	
	public Lieu(String nom,Extremite type) {
		this.name=nom;
		this.type= type;
		this.personnes = new ArrayList<Personne>();
		this.conditions = new ArrayList<Condition>();
		this.descriptions = new ArrayList<DescriptionConditionnee>();
		this.possession = new ArrayList<PossessionConditionnee>();
	}
	
	
	public void addPersonne(Personne p){
		this.personnes.add(p);
	}
	
	public void addCondition(Condition c){
		this.conditions.add(c);
	}
	
	public void addDescription(DescriptionConditionnee d){
		this.descriptions.add(d);
	}
	public void addPossessionConditionnee(PossessionConditionnee p){
		this.possession.add(p);
	}
}
