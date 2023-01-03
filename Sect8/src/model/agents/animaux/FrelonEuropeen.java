package model.agents.animaux;

import java.awt.Point;

import model.agents.Agent;
import model.agents.Sexe;

public class FrelonEuropeen extends Frelon {
	
	public FrelonEuropeen(Sexe s, Point p) {
		super(s, p);
		proies.add(FrelonAsiatique.class);
	}

	@Override
	public Object clone() {
		return new FrelonEuropeen(getSexe(), new Point(getCoord()));
	}

	/* inutile avec la liste de proies à partir des collections
	@Override
	public void rencontrer(Agent a) {
		//penser à réutiliser l'existant
		super.rencontrer(a);
		
		if(a instanceof FrelonAsiatique) {
			Animal b = (Animal)a;
			gestionProie(b);
		}

	}
	*/

}
