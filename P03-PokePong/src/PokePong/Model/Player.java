package PokePong.Model;

import PokePong.Model.Ball;
import PokePong.Model.XYTupel;

import java.io.Serializable;

/**
 * Die Player-Klasse repräsentiert einen Spieler im Pong-Spiel.
 * @author  Elif Bilge Isiloglu
 */

public class Player implements Serializable {
    /**
     * Größe des Spielers
     */
    public float size;
    /**
     * Position des Spielers als XY-Tupel
     */
    public XYTupel position;

    /**
     * Konstruktor für die Player-Klasse.
     * @param x Die x-Koordinate der Startposition des Spielers.
     * @param y Die y-Koordinate der Startposition des Spielers.
     * @param size Size Die Größe des Spielers.
     */
    public Player(double x, double y, float size) {
        this.position = new XYTupel((float) x, (float) y);
        this.size = size;
    }
    /**
     *  Überprüft, ob der Spieler den Ball getroffen hat
     * @param ball ball Der Ball, der überprüft wird.
     * @return  True, wenn der Spieler den Ball getroffen hat, sonst false
     */
    public boolean hits(Ball ball) {//raketin topa carpip carpmadigi
        return ball.collidesX(position.x) &&
                position.y - size / 2 <= ball.getPosition().y + ball.getSize() / 2.0 &&
                position.y + size / 2 >= ball.getPosition().y + ball.getSize() / 2.0;
    }
    /**
     * Gibt da sGröße des Spielers zurück
     * @return Die Größe des Spielers zurück
     */
    public float getSize() {
        return size;
    }
    /**
     * Gibt die aktuelle Position des Spielers zurück.
     *
     * @return Die Position des Spielers als XY-Tupel.
     */
    public XYTupel getPosition() {
        return position;
    }
    public int getId() {
        return getId();
    }

}
