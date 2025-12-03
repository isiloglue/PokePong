package PokePong.Model;

import java.io.Serializable;

/**
 * Die Ball-Klasse repräsentiert einen Spielball im Pong-Spiel
 * @author ELif Bilge Isiloglu
 */

public class Ball implements Serializable {
    /**
     * Größe des Balls
     */
    public float size;
    /**
     * Faktor, der die Geschwindigkeit des Balls beeinflusst
     */
    public int speedFactor = 100;
    /**
     * Winkel, der die Roattion des Balls beeinflusst
     */
    public float angle = 0;
    /**
     * Position des Balls
     */

    public XYTupel position;
    /**
     * Beschleunigung des Balls als XY-Tupel
     */
    public XYTupel acceleration;

    /**
     * Konstruktor für die Ball-Klasse
     * @param x Die x koordination der StartPosition des Balls
     * @param y Die y koordination der StartPosition des Balls
     * @param size Size die Größe des Balls
     */
    public Ball(double x, double y, float size) {
        this.position = new XYTupel((float)x, (float)y);
        this.acceleration = new XYTupel(0, 0);//ivme
        this.size = size;
    }

    /**
     * Methode zum position des balls und beschleunigung
     * @param timeFactor Es gibt die Zeit Faktor
     */

    public void updateBallPosition(double timeFactor) {
        position.x += (float) (acceleration.x * speedFactor * timeFactor);
        position.y += (float) (acceleration.y * speedFactor * timeFactor);
        angle += 2;
    }

    /**
     * Methode zur Zufallsauswahl einer Beschleunigung für den Ball
     */

    public void randomizeAcceleration() {
        acceleration.x = (Math.random() >= 0.5) ? 1 : -1;
        acceleration.y = (float) (2 * Math.random() - 1);
    }

    /**
     *Überpruft ob der Ball mit vertikalen Wand kollidiert ist
      */

    public boolean collidesY(float wall) {
        return (wall > position.y - size / 2.0) && (wall < position.y + size / 2.0);
    }

    /**
     * Überpruft ob der Ball mit horizontalen Wand kollidiert ist
      */

    public boolean collidesX(float wall) {
        return (wall > position.x - size / 2.0) && (wall < position.x + size / 2.0);
    }

    /**
     * Getter-Methode für die Größe des Balls
     * @return size
     */

    public float getSize() {
        return size;
    }

    /**
     * Getter-Methode für die aktuelle Position des Balls
     * @return position
     */
    public XYTupel getPosition(){
        return position;
    }

    public void setPosition(XYTupel position) {
        this.position = position;
    }

    /**
     *Getter-Methode für den Rotationswinkel des Balls
     * @return Rotation
     */


    public float getRotationAngle() {
        return (float)Math.toRadians(angle);
    }

    /**
     * Getter-Methode für die aktuelle Beschleunigung des Balls
     * @return acceleration
     */


    public XYTupel getAcceleration() {
        return acceleration;

    }

    /**
     *     // Alternativer Konstruktor für die Ball-Klasse
     * @param size gibt size  des Balls
     * @param speedFactor gibt speedFaktor des Balls
     * @param angle gibt angle des Balls
     * @param position gibt position des Balls
     * @param acceleration gibt acceleration des Balls
     */
    }

