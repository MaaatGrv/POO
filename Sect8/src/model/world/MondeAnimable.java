package model.world;

import java.awt.event.ActionListener;
import java.util.List;

import model.comportements.Dessinable;

public interface MondeAnimable extends ActionListener{
	
	public void lancerAnimation();
	public void stopperAnimation();
	public List<Dessinable> getElementsMonde();

}
