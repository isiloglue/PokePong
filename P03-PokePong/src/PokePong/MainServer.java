package PokePong;


import PokePong.Controller.PongController;
import PokePong.Model.*;
import PokePong.View.pongView;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;


public class MainServer extends PApplet {
    public static void main(String[] args) {
        final int PORT = 8080;
        /**
         * Breite des Spielfensters
         */
        final int GAME_SIZE_W = 700;
        /**
         * Grösse des Spielfensters
         */
        final int GAME_SIZE_H = 400;

        PongModel model = new PongModel(GAME_SIZE_W, GAME_SIZE_H);
        /**
         * ein neues controller Objekt
         */
        var controller = PongController.newServer(8080,600,model);
        /**
         * ein neues view Objekt
         */
        var serverview = new pongView(GAME_SIZE_W, GAME_SIZE_H);




        controller.setModel(model);
        controller.setView(serverview);
        serverview.setController(controller);
        System.out.println(model);


        /**
         * Für Starten Processing Application
         */
        PApplet.runSketch(new String[]{"PongView"}, serverview);
    }

    private static void processInput(PongController controller, char input) {

        controller.userInput(input);
    }


}
