import java.util.Map;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import petrinet.*;
import simplepdl.*;
import simplepdl.Process;

public class Process2PetriNet {
	public static void main(String[] args) {
		// Charger le package SimplePDL afin de l'enregistrer dans le registre d'Eclipse.
		SimplepdlPackage simplepdlPackageInstance = SimplepdlPackage.eINSTANCE;
		// Charger le package SimplePDL afin de l'enregistrer dans le registre d'Eclipse.
		PetrinetPackage petrinetPackageInstance = PetrinetPackage.eINSTANCE;

		// Enregistrer l'extension ".xmi" comme devant etre ouverte
		// l'aide d'un objet "XMIResourceFactoryImpl"
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Creer un objet resourceSetImpl qui contiendra une ressource EMF (le modele)
		ResourceSet resSet = new ResourceSetImpl();

		// Definir la ressource (le modele)
		URI simplepdlModelURI = URI.createURI("models/SimplePDLCreator_Created_Process.xmi");
		Resource simplepdlResource = resSet.getResource(simplepdlModelURI, true);
		
		// Definir la ressource (le modele)
		URI petrinetModelURI = URI.createURI("models/PetriNetCreator_Created_Process.xmi");
		Resource petrinetResource = resSet.createResource(petrinetModelURI);
		
		// Recuperer le premier element du modele (element racine)
		Process process = (Process) simplepdlResource.getContents().get(0);

		// La fabrique pour fabriquer les elements de SimplePDL
		PetrinetFactory petrinetFactory = PetrinetFactory.eINSTANCE;
		
		/** 
		 * Manipulation de notre instance
		 */
	    // Creation du petrinet
		PetriNet petrinet = petrinetFactory.createPetriNet();
		
		petrinetResource.getContents().add(petrinet);

		
		Map<String, Node> nodes = new HashMap<>();
		// Ajout des activités (WorkSequence)
		for (Object o : process.getProcessElements()) {
			if (o instanceof WorkDefinition) {
			    WorkDefinition def = (WorkDefinition) o;
			    // Creer le petrinet correspondant
			    Place plReady = petrinetFactory.createPlace();
			    plReady.setName(def.getName() + "_ready");
			    plReady.setNbJetons(1);
			    petrinet.getNode().add(plReady);
			    nodes.put(plReady.getName(), plReady);
			    
			    Place plRunning = petrinetFactory.createPlace();
			    plRunning.setName(def.getName() + "_running");
			    plRunning.setNbJetons(0);
			    petrinet.getNode().add(plRunning);
			    nodes.put(plRunning.getName(), plRunning);
			    
			    Place plStarted = petrinetFactory.createPlace();
			    plStarted.setName(def.getName() + "_started");
			    plStarted.setNbJetons(0);
			    petrinet.getNode().add(plStarted);
			    nodes.put(plStarted.getName(), plStarted);
			    
			    Place plFinished = petrinetFactory.createPlace();
			    plFinished.setName(def.getName() + "_finished");
			    plFinished.setNbJetons(0);
			    petrinet.getNode().add(plFinished);
			    nodes.put(plFinished.getName(), plFinished);
			    
			    Transition trStart = petrinetFactory.createTransition();
			    trStart.setName(def.getName() + "_start");
			    petrinet.getNode().add(trStart);
			    nodes.put(trStart.getName(), trStart);
			    
			    Transition trFinish = petrinetFactory.createTransition();
			    trFinish.setName(def.getName() + "_finish");
			    petrinet.getNode().add(trFinish);
			    nodes.put(trFinish.getName(), trFinish);
			    
			    Arc arc1 = petrinetFactory.createArc();
			    arc1.setPoids(1);
			    arc1.setSource(plReady);
			    arc1.setTarget(trStart);
			    arc1.setReadArc(false);
			    petrinet.getArc().add(arc1);
			    
			    Arc arc2 = petrinetFactory.createArc();
			    arc2.setPoids(1);
			    arc2.setSource(trStart);
			    arc2.setTarget(plRunning);
			    arc2.setReadArc(false);
			    petrinet.getArc().add(arc2);
			    
			    Arc arc3 = petrinetFactory.createArc();
			    arc3.setPoids(1);
			    arc3.setSource(trStart);
			    arc3.setTarget(plStarted);
			    arc3.setReadArc(false);
			    petrinet.getArc().add(arc3);
			    
			    Arc arc4 = petrinetFactory.createArc();
			    arc4.setPoids(1);
			    arc4.setSource(plRunning);
			    arc4.setTarget(trFinish);
			    arc4.setReadArc(false);
			    petrinet.getArc().add(arc4);
			    			    
			    Arc arc5 = petrinetFactory.createArc();
			    arc5.setPoids(1);
			    arc5.setSource(trFinish);
			    arc5.setTarget(plFinished);
			    arc5.setReadArc(false);
			    petrinet.getArc().add(arc5);
			}
		}
		
		// Ajout des places ressources
		Map<String, Node> ressources = new HashMap<>();
		for (Object o : process.getProcessElements()) {
			if (o instanceof Ressource) {
				Ressource ress = (Ressource) o;
				
			    Place plRess = petrinetFactory.createPlace();
			    plRess.setName("ressource_" + ress.getName());
			    plRess.setNbJetons(ress.getQuantity());
			    petrinet.getNode().add(plRess);
			    ressources.put(plRess.getName(), plRess);
			}	
		}
		
		
		// Ajouts des liens entre activités (WorkDefinition)
		for (Object o : process.getProcessElements()) {
			if (o instanceof WorkSequence) {
			    WorkSequence seq = (WorkSequence) o;
			    Arc arc = petrinetFactory.createArc();
			    arc.setPoids(1);
			    arc.setReadArc(true);
			    
			    // Recherche predecessor et successor
			    String predecessorName = seq.getPredecessor().getName();
			    String successorName = seq.getSuccessor().getName();
			    
			    switch (seq.getLinkType()) {
			    case FINISH_TO_FINISH : 
				    arc.setSource(nodes.get(predecessorName + "_finished"));
				    arc.setTarget(nodes.get(successorName + "_finish"));
			    	break;
			    case FINISH_TO_START  : 
				    arc.setSource(nodes.get(predecessorName + "_finished"));
				    arc.setTarget(nodes.get(successorName + "_start"));
			    	break;
			    case START_TO_FINISH  :
				    arc.setSource(nodes.get(predecessorName + "_started"));
				    arc.setTarget(nodes.get(successorName + "_finish"));
			    	break;
			    case START_TO_START   :
				    arc.setSource(nodes.get(predecessorName + "_started"));
				    arc.setTarget(nodes.get(successorName + "_start"));
			    	break;
			    }
			    petrinet.getArc().add(arc);
			    

			}	
		}
		
	    // Ajout des consommations de ressources
		for (Object o : process.getProcessElements()) {
			if (o instanceof RessourceSequence) {
				RessourceSequence rseq = (RessourceSequence) o;
				
			    Arc consommation = petrinetFactory.createArc();
			    consommation.setPoids(rseq.getQuantity());
			    consommation.setReadArc(false);
			    consommation.setSource(ressources.get("ressource_" + rseq.getRessource().getName()));
			    consommation.setTarget(nodes.get(rseq.getWorkdefinition().getName() + "_start"));
			    petrinet.getArc().add(consommation);
			    
			    Arc restitution = petrinetFactory.createArc();
			    restitution.setPoids(rseq.getQuantity());
			    restitution.setReadArc(false);
			    restitution.setSource(nodes.get(rseq.getWorkdefinition().getName() + "_finish"));
			    restitution.setTarget(ressources.get("ressource_" + rseq.getRessource().getName()));
			    petrinet.getArc().add(restitution);

			}
		}
		

		
		// Sauver la ressource
	    try {
	    	petrinetResource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
