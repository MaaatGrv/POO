package model.agents.vegetaux;

import java.awt.Point;
import java.util.HashSet;

import model.agents.Agent;
import model.agents.Animal;
import model.agents.animaux.AbeilleSolitaire;
import model.agents.animaux.Frelon;
import model.comportements.Hebergeur;

public class Arbre extends Vegetal implements Hebergeur{

	/**
	 * Liste des hébergés de l'arbre
	 */
	protected HashSet<Animal> population;

	public Arbre(Point point, double taille) {
		super(point);
		this.taille=taille;
		population = new HashSet<Animal>();
	}

	private double taille = 1.0;
	private int nbHeberges = 0;
	
	@Override
	public boolean peutAccueillir(Animal a) {
		// return (a instanceof AbeilleSolitaire || a instanceof Frelon)&&nbHeberges<getMaxHeberges();
		if (a instanceof AbeilleSolitaire || a instanceof Frelon){
			if (population != null){
				if (getMaxHeberges() >= population.size()){
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

	private int getMaxHeberges() {
		return (int)(Math.pow(taille,2));
	}

	@Override
	public boolean accueillir(Animal a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			nbHeberges++;
			ret=true;
			population.add(a);
		}
		return ret;
	}

	@Override
	public void produire() {
		qteNectar += Math.pow(2, taille);		
	}

	@Override
	public void supprimer(Animal a) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object clone() {
		return new Arbre(new Point(getCoord()), this.taille);
	}
}
