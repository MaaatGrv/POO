package model.agents.animaux;

import java.awt.Point;

import model.agents.Agent;
import model.agents.Sexe;

public class AbeilleSolitaire extends Abeille{

	public AbeilleSolitaire(Sexe s, Point p) {
		super(s, p);
	}

	@Override
	public Object clone() {
		return new AbeilleSolitaire(getSexe(), new Point(getCoord()));
	}
}
