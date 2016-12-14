package modele;
import java.util.ArrayList;
import java.util.List;

public class Explorateur extends Composant {

	private int Poidsmax;
	private List<Objet> objets;
	private List<Connaissance> connaissances;

	public Explorateur(String name){
		this.name=name;
		this.Poidsmax=1;
		this.objets = new ArrayList<Objet>();
		this.connaissances = new ArrayList<Connaissance>();
	}

	public void addPossessionConditionnee(PossessionConditionnee nvPoss) {
		// Si c'est un objet, on l'ajoute aux objets d
		if (nvPoss.getPossession() instanceof Objet) {
			Objet nvObj = (Objet) nvPoss.getPossession();
			
			// On cherche une instance similaire de cet objet
			for (Objet obj : objets) {
				if (nvObj.sameAs(obj)) {
					// Si un tel objet existe, on somme les quantités
					obj.addQuantite(nvObj.getQuantite());
					return;
				}
			}
			// L'explorateur ne possède pas cet objet, on l'ajoute
			objets.add(nvObj);
			return;

		} else if (nvPoss.getPossession() instanceof Connaissance) {
			Connaissance nvConn = (Connaissance) nvPoss.getPossession();

			// On cherche une instance de cette connaissance
			for (Connaissance c : connaissances) {
				if (c.getName().equals(nvConn.getName())) {
					// Si on possède déjà la connaissance, on ne fait rien
					return;
				}
			}
			// L'explorateur ne possède pas cette connaissance, on l'ajoute
			connaissances.add(nvConn);
			return;
		} else {
			throw new RuntimeException();
		}
	}

	public void addConnaissances(Connaissance nvConn) {
		// On cherche une instance de cette connaissance
		for (Connaissance c : connaissances) {
			if (c.getName().equals(nvConn.getName())) {
				// Si on possède déjà la connaissance, on ne fait rien
				return;
			}
		}
		// L'explorateur ne possède pas cette connaissance, on l'ajoute
		connaissances.add(nvConn);	
	}
	
	public void setPoidsMax(int p){
		this.Poidsmax=p;
	}

	public int getPoidsMax() {
		return Poidsmax;
	}

	public int getPoids() {
		int poidsCourant = 0;
		for(Objet obj : this.objets){
			poidsCourant += obj.getPoids();
		}
		return poidsCourant;
	}

	public List<Objet> getObjets(){
		return objets;
	}

	public List<Connaissance> getConnaissances(){
		return connaissances;
	}

	public int getQuantiteObjet(Objet o){
		for(Objet obj : this.objets){
			if (obj.sameAs(o)) {
				return obj.getQuantite();
			}
		}
		return 0;
	}

	public boolean connaissancePossedee(Connaissance c){
		for (Connaissance conn : this.connaissances){

			if(conn.getName().equals(c.getName())){
				return true;
			}
		}
		return false;
	}

	public void consommeObjet(Objet objConsomme){
		for(Objet obj : this.objets){
			if(obj.sameAs(objConsomme)) {
				if(obj.getQuantite() <= objConsomme.getQuantite()){ //enlever l'objet
					this.objets.remove(obj);
				} else { // Diminuer Qte
					obj.addQuantite(-objConsomme.getQuantite());
				}
				return;
			}

		}
	}

}
