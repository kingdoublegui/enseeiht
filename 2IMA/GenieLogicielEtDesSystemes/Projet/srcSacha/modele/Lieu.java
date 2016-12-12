package modele;
import java.util.ArrayList;
import java.util.List;

public class Lieu extends ComposantTerritoire {

	private Emplacement type;
	private List<Personne> personnes;
	private List<Condition> conditions;
	private List<DescriptionConditionnee> descriptions;
	private List<PossessionConditionnee> possession;
	
	public Lieu(String nom) {
		this.name=nom;
		this.type= Emplacement.intermediaire;
		this.personnes = new ArrayList<Personne>();
		this.conditions = new ArrayList<Condition>();
		this.descriptions = new ArrayList<DescriptionConditionnee>();
		this.possession = new ArrayList<PossessionConditionnee>();
	}
	
	public void setPosition(Emplacement e){
		this.type=e;
	}
	
	public void addPersonnes(Personne p){
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
	public List<Objet> getObjetsVisibles(){
		ArrayList<Objet> res = new ArrayList<Objet>();
		for(PossessionConditionnee p : this.possession){
			if(p.getPossession()instanceof Objet){
				Objet o = (Objet) p.getPossession();
				if (o.getObjet().getVisi()==Visibilite.visible){
					res .add(o);
				}
			}
		}
		return res;
	}
	public List<Personne> getPersonnes(){
		return this.personnes;
	}
	
	public Emplacement getEmplacement(){
		return this.type;
	}
	public Personne getInteractionObligatoire(){
		Personne res =null;
		for(Personne p : this.personnes){
			if(p.getObligation()==Obligation.obligatoire){
				res=p;
			}
		}
		return res;
	}
	
	public List<Condition> getConditions(){
		return this.conditions;
	}
	
	public List<PossessionConditionnee> getPossessions(){
		return this.possession;
	}
}
