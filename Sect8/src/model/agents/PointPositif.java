package model.agents;

import java.awt.Point;

public class PointPositif {
    /*
     * créez une classe PointPositif qui permet d'utiliser des coordonnées qui ne peuvent pas être négatives, et qui encapsule x et y. Réutilisez la classe Point, mais cherchez une manière intelligente de le faire
     */
    private int x;
    private int y;

    public PointPositif(int x, int y) {
        // Les coordonnées ne peuvent pas être négatives
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        this.x = x;
        this.y = y;
    }

    public PointPositif(Point point) {
        // Les coordonnées ne peuvent pas être négatives
        if (point.x < 0) {
            point.x = 0;
        }
        if (point.y < 0) {
            point.y = 0;
        }
        this.x = point.x;
        this.y = point.y;
    }

    public PointPositif(PointPositif coord) {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object o) {
        if (o instanceof PointPositif) {
            PointPositif p = (PointPositif) o;
            return (p.getX() == x && p.getY() == y);
        }
        return false;
    }

    public double distance(PointPositif p) {
        return Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
    }

    public PointPositif clone() {
        return new PointPositif(x, y);
    }
}
