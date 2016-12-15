package modele;
import java.util.ArrayList;
import java.util.List;

public class Lieu extends ComposantTerritoire {

	private Emplacement type;
	private List<Personne> personnes;
	private List<Condition> conditionsDeposerObjets;
	private List<DescriptionConditionnee> descriptions;
	private List<PossessionConditionnee> possessions;
	
	public Lieu(String nom) {
		this.name=nom;
		this.type= Emplacement.intermediaire;
		this.personnes = new ArrayList<Personne>();
		this.conditionsDeposerObjets = new ArrayList<Condition>();
		this.descriptions = new ArrayList<DescriptionConditionnee>();
		this.possessions = new ArrayList<PossessionConditionnee>();
	}
	
	public void setPosition(Emplacement e){
		this.type=e;
	}
	
	public void addPersonnes(Personne p){
		this.personnes.add(p);
	}
	
	public void addCondition(Condition c){
		this.conditionsDeposerObjets.add(c);
	}
	
	public void addDescription(DescriptionConditionnee d){
		this.descriptions.add(d);
	}
	
	public void addPossessionConditionnee(PossessionConditionnee p){
		this.possession.add(p);
	}
	 /** Retire un objet de la liste des possessions conditionnées
	  * 
	  * @param o objet a retirer
	  * @return true si l'opération a réussie, false sinon
	  */
	public boolean rmObjet(Objet o) {
		for (PossessionConditionnee p : possession) {
			if (p.getPossession() instanceof Objet) {
				if (((Objet) p.getPossession()).getObjet().equals(o.getObjet())) {
					// Si un tel objet existe en qté suffisante, on retire la quantité prise
					if (((Objet) p.getPossession()).getQuantite() == o.getQuantite()) {
						possession.remove(p);
						return true;
					} else if (((Objet) p.getPossession()).getQuantite() > o.getQuantite()) {
						((Objet) p.getPossession()).addQuantite(-o.getQuantite());
						return true;
					} else {
						// quantité insuffisante
						return false;
					}
				}
			}
		}
		return false;
	}

	public List<Objet> getObjetsVisibles(){
		ArrayList<Objet> res = new ArrayList<Objet>();
		for(PossessionConditionnee p : this.possession){
			if(p.getPossession() instanceof Objet){
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

	public List<PossessionConditionnee> getObjetsConditionnnesVisibles() {
		ArrayList<PossessionConditionnee> res = new ArrayList<PossessionConditionnee>();
		for(PossessionConditionnee p : this.possession){
			if(p.getPossession() instanceof Objet){
				if (((Objet) p.getPossession()).getObjet().getVisi()==Visibilite.visible){
					res .add(p);
				}
			}
		}
		return res;
	}
}
