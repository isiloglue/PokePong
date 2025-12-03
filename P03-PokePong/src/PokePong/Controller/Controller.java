
package PokePong.Controller;
import PokePong.ClientServerThread;
import PokePong.Model.PongModel;
import PokePong.View.pongView;

/**
 * Definiert Pong-SpielContoller
 *  @author ELif Bilge Isiloglu
 */

public interface Controller {
    /**
     * Methode zum Fortschreiten des Spiels zum nächsten Bild
     */
    void nextFrame();

    /**
     *Methode zum Setzen der Ansicht, die mit dem Controller verbunden
     * @param view
     */
    void setView(pongView view);

    /**
     * Methode zum Verarbeitung von Benutzereingabe
     * @param c
     */
    void userInput(char c);

    /**
     * Methode zum Setzen des Models, das mit dem Contoller verbunden ist
     * @param model  model Das PongModel, das mit dem Controller verbunden werden soll
     */
    void setModel(PongModel model);

    ClientServerThread getThread();

    //ClientServerThread getThread();
}


