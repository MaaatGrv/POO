package model.decor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

import model.agents.Animal;
import model.agents.Etat;
import model.agents.Sexe;
import model.agents.animaux.Abeille;
import model.agents.animaux.AbeilleDomestique;
import model.comportements.Hebergeur;

public class Ruche extends Decor implements Hebergeur{
	
	/**
	 * Liste des abeilles de la ruche 
	 */
	//private TODO population;
	/**
	 * constante taille maximale de la ruche
	 */
	private static int populationMax = 1000;

	/**
	 *Collection des abeilles de la ruche
	 */
	protected HashSet<Abeille> population;
	
	public Ruche(Point p) {
		super(p);
		//population = new ... TODO;
		population = new HashSet<Abeille>();
	}

	@Override
	public boolean peutAccueillir(Animal a) {
		
		if (a instanceof AbeilleDomestique){
			// verify that population is not null
			if (population != null){
				if (populationMax >= population.size()){
					//verify that the bee is not already in the hive
					if (population.contains(a)){
						return false;
					}
					else{
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean accueillir(Animal a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			/* Ne pas faire ça ici: c'est à l'animal de mettre à jour ses attributs
			 * a.setHebergeur(this);
			 */
			ret=true;
			population.add((AbeilleDomestique) a);
		}
		return ret;
	}
	
	public String toString() {
		/*
		 * "\t" code une tabulation dans une chaine de caractères
		 * "\n" un saut de ligne 
		 */
		
		String BeeHive = "Ruche: (" + this.getCoord().x + ";" + this.getCoord().y + ")"+" population: "+ population.size() + " abeilles\n ";

		// Sort population by id and add it to a list
		ArrayList<Abeille> sortedPopulation = new ArrayList<Abeille>(population);
		sortedPopulation.sort((Abeille a1, Abeille a2) -> a1.getId() - a2.getId());
		// Add all the bees from the population HashSet to the BeeHive string
		for (Abeille abeille : sortedPopulation){
			BeeHive += "\t*"+ abeille.toString()+"\n";
		}
		
		// // Add all the bees from the population HashSet to the BeeHive string
		// for (Abeille abeille : population){
		// 	BeeHive += "\t*"+ abeille.toString()+"\n";
		// }

		return BeeHive;
	}
	
	public static void main(String[] a) {
		/*
		Ruche r = new Ruche(new Point(0,0));
		new AbeilleDomestique(Sexe.Assexue, new Point(10,20),r);
		System.out.println(r);
		new AbeilleDomestique(Sexe.Assexue, new Point(10,20),r);
		System.out.println(r);
		new AbeilleDomestique(Sexe.Femelle, new Point(5,10),r);
		System.out.println(r);
		*/
	}

	@Override
	public void supprimer(Animal a) {
		// On vérifie que l'état des abeilles est 'Mourant' avec getNiveauSante()
		if (a.getNiveauSante() == Etat.Mourant){
			// On supprime l'abeille de la ruche
			population.remove(a);
		}
	}
}
