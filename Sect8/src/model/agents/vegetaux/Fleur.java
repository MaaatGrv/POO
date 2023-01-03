package model.agents.vegetaux;

import java.awt.Point;

import model.agents.Agent;

public class Fleur extends Vegetal {

	public Fleur(Point point) {
		super(point);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void produire() {
		qteNectar+=1;
	}

	@Override
	public Object clone() {
		return new Fleur(new Point(getCoord()));
	}
}
