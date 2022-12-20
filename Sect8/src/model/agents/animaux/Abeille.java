package model.agents.animaux;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import model.agents.Agent;
import model.agents.Animal;
import model.agents.Etat;
import model.agents.Sexe;
import model.agents.vegetaux.Vegetal;
import model.comportements.Hebergeur;
import model.decor.Ruche;
/**
 * Abeille est un hébergeur pour ses parasites (Varroa par exemple)
 * @author bruno
 *
 */
public abstract class Abeille extends Animal implements Hebergeur{
	/**
	 * parasite éventuel de l'abeille
	 * si l'abeille est parasitée, passe à true
	 */
	private boolean parasite=false;
	/**
	 * quantité de miel transporté par l'abeille
	 */
	private int qteMiel = 0;
	/**
	 * constante donnant la quantité maximal de miel que l'abeille peut transporter
	 */
	private static final int qteMax = 10;

	private int LapRemaining = 10;

	private static final Set<Varroa> parasites = new HashSet<Varroa>();
	
	public Abeille(Sexe s, Point p, Ruche r) {
	}
	
	public Abeille(Sexe s, Point p) {
		super(s,p);
	}
    @Override
	public void rencontrer(Agent a) {
		/* rencontres */
		/* Accueil: ne pas coder ici
		 * c'est à l'animal rencontré d'appeler cette méthode
		 * 
		 * if(a instanceof Animal) {accueillir((Animal)a);}
		 */
		if(a instanceof Vegetal && qteMiel<Abeille.qteMax) {
			Vegetal v = (Vegetal)a;
			if (v.getPortionNectar() != 0) {
				qteMiel = qteMiel + v.getPortionNectar();
				v.setPortionNectar(v.getPortionNectar()-1);
			}
		}
		/* rencontre avec un prédateur */
		else if(a instanceof Frelon && getNiveauSante()!=Etat.Mourant) {
			setNiveauSante(Etat.EnDetresse);
			if (a.aFaim()) {setNiveauSante(Etat.Mourant);}
		}
	}

	@Override
	public boolean peutAccueillir(Animal a) {
		/*
		 * l'abeille n'a pas de parasite et l'animal est un Varroa
		 */
		return a instanceof Varroa && !parasite;
	}

	@Override
	public boolean accueillir(Animal a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			parasite = true;
			aggraverEtat();
			ret = true;
			// add the Animal to the HashSet 'Parasite' : code bellow
		}
		return ret;
	}

	@Override
	protected void maj() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void seNourrir() {
		if (qteMiel == 0) {
			LapRemaining--;
		}
		if (LapRemaining == 0) {
			aggraverEtat();
		}
		if (qteMiel > 0) {
			qteMiel--;
			ameliorerEtat();
			LapRemaining = 10;
		}
	}

	@Override
	public void supprimer(Animal a) {
		if (a.getNiveauSante() == Etat.Mourant){
			parasite = false;
		}
	}
}
