package model.world;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Timer;

import model.agents.Agent;
import model.agents.Animal;
//partie 2
import model.agents.PointPositif;
import model.agents.Sexe;
import model.agents.animaux.AbeilleDomestique;
import model.agents.animaux.AbeilleSolitaire;
import model.agents.animaux.FrelonAsiatique;
import model.agents.animaux.FrelonEuropeen;
import model.agents.animaux.Varroa;
import model.agents.vegetaux.Arbre;
import model.agents.vegetaux.Fleur;
import model.comportements.Dessinable;
import model.decor.Ruche;
import model.world.CoordComparator;
import model.decor.Decor;
import model.world.MondeAnimable;

public class Monde implements ActionListener {
	/**
	 * population d'agents dans le monde
	 */
	private Set<Agent>agents;
	/**
	 * Decor
	 */
	private Set<Decor>decors;
	/**
	 * map de probabilité pour trouver un agent
	 */
	/*
	 * par commodité: la map n'est plus statique pour permettre le paramétrage
	 * par l'interface graphique des probabilités d'apparition d'agents.
	 */
	private static Map<Integer,Agent> proba;
	/**
	 * constante: largeur du monde
	 */
	private static int LARGEUR = 200;

	/**
	 * constante: longueur du monde
	 */
	private static int LONGUEUR = 300;
	/**
	 * Timer pour déclencher un évènement toutes les x ms
	 */
	private Timer timer;

	private static boolean NightMode = false;
	
	/**
	 * 
	 * @param nbAgents
	 */
	
	public Monde(int nbAgents) {
		this(nbAgents,10);
	}
	
	public Monde(int nbAgents, int delai) {
		proba = probaAgent();
		agents=generateAgents(nbAgents);
		timer=new Timer(delai, this);
	}
	
	/**
	 * @return the lARGEUR
	 */
	public static int getLARGEUR() {
		return LARGEUR;
	}

	/**
	 * @return the lONGUEUR
	 */
	public static int getLONGUEUR() {
		return LONGUEUR;
	}
	
	/**
	 * Méthode utilitaire statistique pour produire la table de proba
	 * d'apparition d'un agent
	 * @return
	 */
	private Map<Integer, Agent> probaAgent() {
		/*
		 * par commodité: la map n'est plus statique pour permettre le paramétrage
		 * par l'interface graphique des probabilités d'apparition d'agents.
		 */
		decors = new HashSet<Decor>();
		Ruche r1 =new Ruche(new Point(10,50));
		Ruche r2 =new Ruche(new Point(100,20));
		decors.add(r1);
		decors.add(r2);		
		Map<Integer,Agent> myMap = new LinkedHashMap<Integer,Agent>();
	    myMap.put(20,new AbeilleDomestique(Sexe.Assexue,new Point(0,0),r1));
	    myMap.put(40,new AbeilleDomestique(Sexe.Assexue,new Point(0,0),r2));
	    myMap.put(50,new AbeilleSolitaire(Sexe.Assexue,new Point(0,0)));
	    myMap.put(55,new FrelonEuropeen(Sexe.Assexue,new Point(0,0)));
	    myMap.put(60,new FrelonAsiatique(Sexe.Assexue,new Point(0,0)));
	    myMap.put(70,new Varroa(Sexe.Assexue,new Point(0,0)));
	    myMap.put(80,new Arbre(new Point(0,0),1.0));
	    myMap.put(85,new Arbre(new Point(0,0),2.0));
	    myMap.put(100,new Fleur(new Point(0,0)));
	    return myMap;
	}
	
	/**
	 * fabrication aléatoire d'un Agent par tirage dans la Map
	 * et positionnement aléatoire
	 * @param alea
	 * @return
	 */
	private static Agent tirage(int alea) {
		/*
		 * NE PAS TOUCHER!
		 */
		Agent agent=null;
		if(alea<100 && alea>=0) {
			boolean trouve = false;
			Iterator<Integer> it = proba.keySet().iterator();
			while(!trouve && it.hasNext()) {
				Integer clef = it.next();
				if(clef>=alea) {
					agent = proba.get(clef);
					trouve=true;
				}
			}
		}
		else {
			agent = new Fleur(new Point(0,0));
		}
		//positionnement aléatoire entre Longueur et Largeur
		int aleaX = (int)(Math.random()*LONGUEUR);
		int aleaY = (int)(Math.random()*LONGUEUR);
		agent.setCoord(aleaX, aleaY);
		return agent;
	}

	private TreeSet<Agent> generateAgents(int nbAgents) {
			/*
			 * NE PAS TOUCHER!
			 */
		TreeSet<Agent> ts = new TreeSet<Agent>();
		for(int i=0;i<nbAgents;i++) {
			int alea = (int)(Math.random()*100);
			//partie 2
			ts.add((Agent)tirage(alea).clone());
			//partie 1
			// ts.add(copieAgent(tirage(alea)));
		}
		return ts;
	}

	private Set<Agent> getAgentByCoord(){
		Set<Agent> coordSet = new TreeSet<Agent>(new CoordComparator());
		coordSet.addAll(agents);
		
		return coordSet;
	}

	public String toString() {
		String ret="";
		ret+="******************************\n";
		ret+="Le monde contient "+agents.size()+" agents:\n";
		Set<Agent> coordSet = new TreeSet<Agent>(new CoordComparator());
		coordSet.addAll(agents);
		for(Agent a:coordSet) {
			ret+="\t"+a+"\n";
		}
		return ret;
	}

	public static String remplir(Integer indice, PointPositif coord) {
		int cible = (coord.getX()*LARGEUR + coord.getY()-indice);
		indice=cible+1;
		return String.format("%" + cible+"s","");		
	}

	/**
	 * génère un cycle de vie dans le monde
	 */
	public void cycle() {
		for(Agent a:agents) {
			if (!NightMode) {
				a.cycle();
				gererRencontre(a);
			}
			else {
				//TODO
			}
		}
	}

	/**
	 * @return renvoie la liste des voisins de l'agent a
	 * @param a l'agent dont on cherche les voisins
	 * @param radius le rayon de recherche
	 * Il y a rencontre si la distance entre deux agents est inférieure à 10
	 */
	public Set<Agent> gererRencontre(Agent a) {
		Set<Agent> voisins = new TreeSet<Agent>(new CoordComparator());
		int radius = 10;
		for(Agent b:agents) {
			if(a!=b) {
				if(a.getCoord().distance(b.getCoord())<radius) {
					voisins.add(b);
					// si l'agent b esr un animal :
					if (b instanceof Animal && a instanceof Animal)
						a.rencontrer(b);
				}
			}
		}
		return voisins;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cycle();		
		System.out.println("cycle");
	}

	public void lancerAnimation() {
		timer.start();
	}

	public void stopperAnimation() {
		timer.stop();	
	}

	public List<Dessinable> getElementsMonde() {
		List<Dessinable> ret = new ArrayList<Dessinable>();
		ret.addAll(decors);
		ret.addAll(agents);
		return ret;
	}
}
