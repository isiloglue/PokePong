package PokePong;

import PokePong.Controller.GameState;
import PokePong.Controller.PongController;
import PokePong.Model.Player;
import PokePong.Model.Ball;
import PokePong.Model.PongData;
import PokePong.Model.PongModel;
import PokePong.View.pongView;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
/**
 * Die Main-Klasse startet das Pong-Spiel, indem sie die Model-, Controller- und View-Instanzen erstellt und miteinander verbindet.
 * @author  Elif Bilge Isiloglu
 */
public class Main extends PApplet {
    public static void main(String[]args){
        /**
         *Größe der Benutzeroberfläche
         */
        final int SizeWidth=500;
        final int SizeHeight=300;
        /**
         *PongModel, PongController und pongView Instanzen erstellen
         */
        var model=new PongModel(SizeWidth,SizeHeight);
        var controller=new PongController(SizeHeight);
        var view=new pongView(SizeWidth,SizeHeight);
        /**
         * Model und View mit dem Controller verbinden
         */

        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);
        /**
         *PApplet.runSketch() aufrufen, um die Anwendung zu starten java.
         */

        PApplet.runSketch(new String[]{"pongView"},view);

    }
}
