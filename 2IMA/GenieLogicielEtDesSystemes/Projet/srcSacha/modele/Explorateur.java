package modele;
import java.util.ArrayList;
import java.util.List;

public class Explorateur extends Composant {
	
	private int Poidsmax;
	private int poidcourant;
	private List<PossessionConditionnee> possessions;
	
	public Explorateur(String name){
		this.poidcourant=0;
		this.name=name;
		this.Poidsmax=1;
		this.possessions = new ArrayList<PossessionConditionnee>();
	}
	
	public void  addPossessionConditionnee(PossessionConditionnee p){
		this.possessions.add(p);
	}
	
	public void setPoidsMax(int p){
		this.Poidsmax=p;
	}

	public int getPoidsMax() {
		return Poidsmax;
	}
	public int getPoid() {
		return poidcourant;
	}
	public void modifPoid(int m){
		this.poidcourant+=m;
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
				System.out.println("JE PASSE DANS CONSOMME OBJET");
				this.poidcourant=this.poidcourant-o.getQuantite();
			}
		}
	}
	
}
