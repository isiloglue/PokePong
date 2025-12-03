package PokePong.View;

import PokePong.Controller.Controller;
import PokePong.Controller.PongController;
import PokePong.Model.Ball;
import PokePong.Model.Player;
/**
 *  @author  Elif Bilge Isiloglu
 * Das View-Interface definiert die Methoden, die von einer Klasse implementiert werden müssen.
 */

public interface View  {


    /**
     * Zeichnet den Startbildschirm.
     */

    void drawStart();
   // void drawGame(Player p1, Player p2, Ball ball, int PADDLE_WIDTH);
    /**
     * Zeichnet das laufende Pong-Spiel mit den Positionen von Spieler 1, Spieler 2 und dem Ball.
     *
     * @param p1            Spieler 1.
     * @param p2            Spieler 2.
     * @param ball          Der Ball.
     * @param PADDLE_WIDTH  Die Breite der Schläger.
     */
    void drawGame(Player p1, Player p2, Ball ball, int PADDLE_WIDTH);
    /**
     * Aktualisiert die Position des Balls.
     *
     * @param ball Der Ball.
     */
    void updateBallPosition(Ball ball);

    /**
     * Setzt den Controller für die Benutzeroberfläche.
     *
     * @param controller Der zugehörige Controller.
     */
    void setController(Controller controller);

    /**
     * Zeichnet den Game-Over-Bildschirm.
     */
    void drawGG();



}
