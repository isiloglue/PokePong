package PokePong;

import PokePong.Controller.PongController;
import PokePong.Model.PongModel;
import PokePong.View.pongView;  // Adjusted import statement
import PokePong.Model.PongSize;

import processing.core.PApplet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainClient extends PApplet {
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
        var controller = PongController.newClient("localhost", 8080, 600, model);

        /**
         * ein neues view Objekt
         */
        var clientView = new pongView(GAME_SIZE_W, GAME_SIZE_H);

        controller.setModel(model);
        controller.setView(clientView);
        clientView.setController(controller);
        System.out.println(model);

        /**
         * Für Starten Processing Application
         */
        PApplet.runSketch(new String[]{"PongView"}, clientView);
    }
}
