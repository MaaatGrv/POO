package tp.model.agents;

import tp.model.comportements.Hebergeur;

public class Arbre extends Vegetal implements Hebergeur{
    public Arbre() {
    }
    
    @Override
    public boolean peutAccueillir(Agent a) {
        // Renvoie true si la classe de l'agent est AbeilleSolitaire ou Frelon (FrelonAsiatique et FrelonEuropeen)
        if (a.getClass().getSimpleName().equals("AbeilleSolitaire") || a instanceof Frelons) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean accueillir(Agent a) {
        // TODO Auto-generated method stub
        return false;
    }
}
