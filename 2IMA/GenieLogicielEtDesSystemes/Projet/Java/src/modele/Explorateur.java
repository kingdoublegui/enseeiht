package modele;
import java.util.ArrayList;
import java.util.List;

public class Explorateur extends Composant {
	
	private int Poidsmax;
	private List<PossessionConditionnee> possessions;
	
	public Explorateur(String name){
		this.name=name;
		this.Poidsmax=1;
		this.possessions = new ArrayList<PossessionConditionnee>();
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
	
	public void setPoidsMax(int p){
		this.Poidsmax=p;
	}

	public int getPoidsMax() {
		return Poidsmax;
	}
	public int getPoids() {
		int poidsCourant = 0;
		for(PossessionConditionnee p : this.possessions){
			if(p.getPossession()instanceof Objet){
				Objet o = (Objet) p.getPossession();
				poidsCourant += o.getPoids();
			}
		}
		return poidsCourant;
	}

	public List<PossessionConditionnee> getPossessions() {
		return possessions;
	}
	
	public List<Objet> getObjets(){
		ArrayList<Objet> res = new ArrayList<Objet>();
		for(PossessionConditionnee p : this.possessions){
			if(p.getPossession()instanceof Objet){
				Objet o = (Objet) p.getPossession();
				res.add(o);
			}
		}
		return res;
	}
	
	public List<Connaissance> getConnaissances(){
		ArrayList<Connaissance> res = new ArrayList<Connaissance>();
		for(PossessionConditionnee p : this.possessions){
			if(p.getPossession()instanceof Connaissance){
				Connaissance c = (Connaissance) p.getPossession();
				res.add(c);
			}
		}
		return res;
	}
	
	public int getQuantiteObjet(Objet o){
		int res =0;
		for(Objet obj : this.getObjets()){
			if(o.getObjet().getName().equals(obj.getObjet().getName())){
				res = res + obj.getQuantite();
			}
		}
		return res;
	}
	
	public boolean connaissancePossedee(Connaissance c){
		boolean res = false;
		for (Possession p : this.possessions){
			if(p instanceof Connaissance){
				Connaissance conn = (Connaissance) p;
				if(conn.getName().equals(c.getName())){
					res = true;
				}
			}
		}
		return res;
	}
	
	public void consommeObjet(Objet o){
		for(Objet obj : this.getObjets()){
			if(o.getObjet().getName().equals(obj.getObjet().getName())){
				int quantite = obj.getQuantite();
				quantite = quantite-o.getQuantite();
				if(quantite <= 0){ //enlever l'objet
					this.getObjets().remove(obj);
				}
			}
		}
	}
	
}
