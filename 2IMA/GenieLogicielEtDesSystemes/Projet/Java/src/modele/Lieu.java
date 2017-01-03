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
	
	public void addPossessionConditionnee(PossessionConditionnee nvposs){
		// Si c'est un objet, on l'ajoute aux objets d
		if (nvposs.getPossession() instanceof Objet) {
			// On cherche une instance similaire de cet objet
			for (PossessionConditionnee p : possessions) {
				if (p.getPossession() instanceof Objet) {
					if (((Objet) p.getPossession()).getObjet().equals(((Objet)nvposs.getPossession()).getObjet())) {
						// Si un tel objet existe, on somme les quantités
						((Objet) p.getPossession()).addQuantite(((Objet)nvposs.getPossession()).getQuantite());
						return;
					}
				}
			}
			// L'explorateur ne possède pas cet objet, on l'ajoute
			possessions.add(nvposs);
			return;
			
		} else if (nvposs.getPossession() instanceof Connaissance){
			// On cherche une instance de cette connaissance
			for (PossessionConditionnee p : possessions) {
				if (p.getPossession() instanceof Connaissance) {
					if (((Connaissance) p.getPossession()).getName().equals(((Connaissance) nvposs.getPossession()).getName())) {
						// Si on possède déjà la connaissance, on ne fait rien
						return;
					}
				}
			}
			// L'explorateur ne possède pas cette connaissance, on l'ajoute
			possessions.add(nvposs);
			return;
		} else {
			this.possessions.add(nvposs);
		}
	}
	
	 /** Retire un objet de la liste des possessions conditionnées
	  * 
	  * @param o objet a retirer
	  */
	public void rmObjet(Objet o) {
		for (PossessionConditionnee p : possessions) {
			if (p.getPossession() instanceof Objet) {
				if (((Objet) p.getPossession()).getObjet().equals(o.getObjet())) {
					// Si un tel objet existe en qté suffisante, on retire la quantité prise
					if (((Objet) p.getPossession()).getQuantite() == o.getQuantite()) {
						possessions.remove(p);
						return;
					} else if (((Objet) p.getPossession()).getQuantite() > o.getQuantite()) {
						((Objet) p.getPossession()).addQuantite(-o.getQuantite());
						return;
					} else {
						// quantité insuffisante
						return;
					}
				}
			}
		}
	}

	public List<Objet> getObjetsVisibles(){
		ArrayList<Objet> res = new ArrayList<Objet>();
		for(PossessionConditionnee p : this.possessions){
			if(p.getPossession() instanceof Objet){
				Objet o = (Objet) p.getPossession();
				System.out.println(" OBJET VISI "+o.getName());
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
	
	public List<Condition> getConditionsDepotObjet(){
		return this.conditionsDeposerObjets;
	}
	
	public List<PossessionConditionnee> getPossessions(){
		return this.possessions;
	}

	public List<PossessionConditionnee> getObjetsConditionnnesVisibles() {
		ArrayList<PossessionConditionnee> res = new ArrayList<PossessionConditionnee>();
		for(PossessionConditionnee p : this.possessions){
			if(p.getPossession() instanceof Objet){
				if (((Objet) p.getPossession()).getObjet().getVisi()==Visibilite.visible){
					res .add(p);
				}
			}
		}
		return res;
	}

	public List<PossessionConditionnee> getConnaissancesCond() {
		ArrayList<PossessionConditionnee> res = new ArrayList<PossessionConditionnee>();
		for(PossessionConditionnee p : this.possessions){
			if(p.getPossession() instanceof Connaissance){
				res.add(p);
			}
		}
		return res;
	}
}
