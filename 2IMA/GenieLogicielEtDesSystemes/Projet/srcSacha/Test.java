import modele.*;
import afficheurs.*;
import controleurs.*;

public class Test {
	public static void main (String[] args){
		Jeu jeu = new Jeu("Test");
		Territoire jungle = new Territoire("Jungle");
		
		//LIEU DEPART
		Lieu desert = new Lieu("Desert");
		desert.setPosition(Emplacement.depart);
				
		
		//LIEU INTERMEDIAIRE
		Lieu cascade = new Lieu("Cascade");
		cascade.setPosition(Emplacement.intermediaire);
		
		
		//LIEU FIN
		Lieu grotte = new Lieu("Grotte");
		grotte.setPosition(Emplacement.fin);
		
		//CHEMIN DEPART -> INTERMEDIAIRE
		Chemin di = new Chemin("Vers la cascade");
		di.setDepart(desert);
		di.setFin(cascade);
		
		//CHEMIN INTERMEDIAIRE -> FIN
		Chemin iff = new Chemin("Vers la grotte");
		iff.setDepart(cascade);
		iff.setFin(grotte);
		
		jungle.addComposants(desert);
		jungle.addComposants(cascade);
		jungle.addComposants(grotte);
		jungle.addComposants(di);
		jungle.addComposants(iff);
	}

}
