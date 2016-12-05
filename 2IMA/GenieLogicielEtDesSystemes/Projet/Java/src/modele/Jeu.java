package modele;
import java.util.ArrayList;
import java.util.List;

public class Jeu extends Composant {

	private Territoire territoire;
	private Explorateur explorateur;
	private List<Composant> composants;
	private Difficulte diff;
	
	
	public Jeu(String name,Difficulte diff) {
		this.composants=new ArrayList<Composant>();
		this.name=name;
		this.diff=diff;
	}
	
	public void addTerritoire(Territoire t){
		this.territoire=t;
	}
	
	public void addExplorateur(Explorateur e){
		this.explorateur=e;
	}
	
	public void addComposant(Composant c){
		this.composants.add(c);
	}
	
	
	
	

	
	
	
}
