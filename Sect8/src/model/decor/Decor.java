package model.decor;

import java.awt.Point;

import model.agents.PointPositif;
import model.comportements.Dessinable;

//partie 2
//import model.agents.PointPositif;

public abstract class Decor implements Dessinable{
	/**
	 * coordonnées de l'élément de décor
	 */
	//partie 2
	//private PointPositif coord;
	//partie 1
	private Point coord;


	public Decor(Point p) {
		coord = p;
		//partie 2
		//coord = new PointPositif(p);
	}
	
	/* partie 2*/
	public PointPositif getCoord() {
		return (PointPositif)coord.clone();
	}
	
	//partie 1
	// public Point getCoord() {
	// 	return new Point(coord);
	// }


	public String getImage() {
		return "images/" + getClass().getSimpleName() + ".png";
	}

}
