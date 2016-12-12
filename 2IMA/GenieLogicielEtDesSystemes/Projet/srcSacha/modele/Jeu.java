package modele;
import java.util.ArrayList;
import java.util.List;

public class Jeu extends Composant {

	private Territoire territoire;
	private Explorateur explorateur;
	private List<Composant> composants;
	private Difficulte diff;
	
	
	public Jeu(String name) {
		this.composants=new ArrayList<Composant>();
		this.name=name;
		this.diff=diff;
	}
	
	public void setTerritoire(Territoire t){
		this.territoire=t;
	}
	
	public void setExplorateur(Explorateur e){
		this.explorateur=e;
	}
	public void setDifficulte(Difficulte d){
		this.diff=d;
	}
	public void addComposants(Composant c){
		this.composants.add(c);
	}

	public Territoire getTerritoire() {
		return territoire;
	}

	public Explorateur getExplorateur() {
		return explorateur;
	}

	public List<Composant> getComposants() {
		return composants;
	}

	public Difficulte getDifficulte() {
		return diff;
	}
	
	
	
	

	
	
	
}
