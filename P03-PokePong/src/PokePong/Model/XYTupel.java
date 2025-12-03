package PokePong.Model;

import java.io.Serializable;

/**
 * Die XYTupel-Klasse repräsentiert eine Koordinaten-Tupel mit X- und Y-Werten.
 * @author  Elif Bilge Isiloglu
 */

public class XYTupel implements Serializable  {
    /**
     * Die x-Koordinaten des Tupels.
     */
    public float x = 0;
    /**
     * Die y-Koordinaten des Tupels.
     */
    public float y = 0;
    //X ve Y koordinatlari
    /**
     * Konstruktor für die XYTupel-Klasse.
     * @param x Die X-Koordinate.
     * @param y Die Y-Koordinate.
     */
    public XYTupel(float x, float y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Gibt den X-Wert des Tupels zurück.
     */
    public float getX() {
        return x;
    }
    /**
     * Gibt den Y-Wert des Tupels zurück.
     */

    public float getY() {
        return y;
    }

    /**
     * Setzt den X-Wert des Tupels.
     *
     * @param x Der neue X-Wert.
     */
    public void setX(float x) {
        this.x = x;
    }
    /**
     * Setzt den Y-Wert des Tupels.
     *
     * @param y Der neue Y-Wert.
     */

    public void setY(float y) {
        this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        XYTupel xyTupel = (XYTupel) obj;
        return Float.compare(xyTupel.x, x) == 0 && Float.compare(xyTupel.y, y) == 0;
    }
}
