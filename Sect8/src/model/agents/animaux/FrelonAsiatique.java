package model.agents.animaux;

import java.awt.Point;

import model.agents.Agent;
import model.agents.Sexe;

public class FrelonAsiatique extends Frelon{

	public FrelonAsiatique(Sexe s, Point p) {
		super(s, p);
	}

	@Override
	public Object clone() {
		return new FrelonAsiatique(getSexe(), new Point(getCoord()));
	}
}
