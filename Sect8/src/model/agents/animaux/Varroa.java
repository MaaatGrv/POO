package model.agents.animaux;

import java.awt.Point;

import model.agents.Sexe;
import model.agents.Agent;
import model.agents.Animal;
/**
 * 
 * @author bruno
 *
 */
public class Varroa extends Animal {
	
	public Varroa(Sexe s, Point p) {
		super(s, p);
	}
		
	@Override
	public void rencontrer(Agent a) {
		//code à trouver en section 6
		if(a instanceof Abeille) {
			Abeille candidateAuParasitage = (Abeille) a;
			this.sInstaller(candidateAuParasitage);
		}
	}

	@Override
	protected void maj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void seNourrir() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * si le Varroa a un hébergeur, il se déplace avec l'abeille
	 */
	public void seDeplacer() {
		//TODO
	}

	@Override
	public Object clone() {
		return new Varroa(getSexe(), new Point(getCoord()));
	}
}
