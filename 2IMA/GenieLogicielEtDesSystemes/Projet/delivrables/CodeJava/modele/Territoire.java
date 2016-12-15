package modele;

import java.util.ArrayList;
import java.util.List;

public class Territoire extends Composant{

	private List<ComposantTerritoire> composants;
	
	public Territoire(String nom){
		this.name=nom;
		this.composants= new ArrayList<ComposantTerritoire>();
	}
	
	public void addComposants(ComposantTerritoire c){
		this.composants.add(c);
	}
	// A améliorer si tmeps
	public Lieu getLieuDepart(){
		Lieu res = new Lieu("");
		for(ComposantTerritoire c : this.composants){
			if(c instanceof Lieu){
				Lieu l = (Lieu) c;
				if(l.getEmplacement()==Emplacement.depart){
					res=l;
				}
			}
		}
		return res;
	}
	
	public List<ComposantTerritoire> getComposantTerritoire(){
		return this.composants;
	}
}
