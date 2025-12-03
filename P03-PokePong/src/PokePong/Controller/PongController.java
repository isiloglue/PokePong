package PokePong.Controller;

import PokePong.ClientServerThread;
import PokePong.Model.PongModel;
import PokePong.Model.PongSize;
import PokePong.View.pongView;

import processing.core.PImage;
import PokePong.MainServer;
import PokePong.Model.*;
import PokePong.View.View;

import java.io.IOException;

import static java.lang.Math.max;
import static java.lang.Math.min;

import static PokePong.Controller.GameState.START;

/**
 * PongContoller Klasse , die das Controller iInterface implementiert
 * @author ELif Bilge Isiloglu
 */

public class PongController implements Controller {
    private PongController controller;
    /**
     * Instanz von PongSize die größe eingeben
     */
    PongSize big= new PongSize();
    /**
     * Instanz der pongView für das Zeichnen des nächsten Frames
     */
    public pongView nextFrame;
    /**
     * Instanz von PongModel für das Datenmodell des Spiels
     */

    public PongModel model;
    /**
     *Aktueller Spielzustand
     */
    public GameState state;
    /**
     * Instanz der pongView für die Darstellung des Spiels
     */
    public pongView view;
    /**
     * Höhe des Spielfelds
     */
    public int height;

    /**
     * Konstruktor für die PongContoller KLasse
     * Parameter von height
     * @param height height Die Höhe des Spielfelds.
     */
    /**
     * Objekt von PongSize für Breite der Paddle
     */
    PongSize origin=new PongSize();
    public ClientServerThread thread= new ClientServerThread(model,controller);

    public PongController(int height) {
        this.height = height;
        this.state = START;
        this.thread=new ClientServerThread(model,controller);

    }

    /**
     * Methode zum setzen des DatenModells
     * @param model  Das PongModel, das dem Controller zugeordnet werden soll.
     */


    public void setModel(PongModel model) {
        this.model = model;
    }

    /*@Override
    public ClientServerThread getThread() {
        return null;
    }*/

   /* @Override
    public ClientServerThread getThread() {
        return null;
    }*/

    /**
     * Methode zum setzen der Ansicht
     * @param view  view ist Repräsentationobjekt das vom Controller verwendet werden soll.
     */

    public void setView(pongView view) {
        this.view = view;
    }
    /**
     * Methode zum stezen des Frame des Spiels
     */
    public void nextFrame() {
        switch (model.getData().state) {
            case START -> {
                view.drawStart();
            }
            case PLAYING -> {
                view.drawGame(model.getData().getPlayer1(), model.getData().getPlayer2(), model.getData().getBall(),big.PADDLE_WIDTH());
                model.handleCollisions();
                if(thread.isServer){thread.send(model.data);}
            }
            case GAME_OVER -> {
                view.drawGG();
            }
            default -> throw new IllegalStateException("Unexpected Value: " + model.getData().state);
        }
    }
    public static PongController newServer(int port,int size,PongModel model) {
        var ps = new PongController(size);
        ps.thread = ClientServerThread.newServer(port, model, ps);
        ps.thread.isServer = true;
        //ps.thread.start();
        return ps;
    }

    public static PongController newClient(String ip, int port,int size, PongModel model) {
        var pc = new PongController(size);
        pc.thread = ClientServerThread.newClient(ip, port,model,pc);
        pc.thread.isServer=false;
        //pc.thread.start();
        return pc;
    }


    /**
     * Methode zur Verarbeitung der Benutzereingabe
     * @param c Benutzereingabe parametr
     */
    public void userInput(char c) {
        switch (model.getData().state) {
            case START, GAME_OVER -> {
                if (c == 'c') {
                    state = GameState.PLAYING;
                    model.startNewGame();
                    thread.send(model.data);
                }
            }
            case PLAYING -> {
                if(thread.isServer()){
                if (c == 'w') {
                    model.data.player1.position.setY(
                            Math.max((float) model.data.player1.size / 2,
                            model.data.player1.position.y-10));

                }
                if (c == 's') {
                    model.data.player1.position.setY(
                            Math.min((float)( height - model.getData().getPlayer1().getSize() / 2),model.data.player1.position.y+10));
                }}
                if(!thread.isServer()){

                    if (c == 'u') {
                    model.data.player2.position.setY(
                            Math.max((float) model.data.player2.size / 2,
                                    model.data.player2.position.y-10) );
                }
                if (c == 'd') {
                    model.data.player2.position.setY(
                            Math.min(((float)
                            height - model.getData().getPlayer2().getSize() / 2),model.data.player2.position.y+10));
                }}

            }
            default -> throw new IllegalStateException("Unexpected value: " + model.getData().state);

        }
    }
    public static PongController newPlayer(String ip, int port,int size, PongModel model) throws IOException {
        var pc = new PongController(size);
        pc.thread = ClientServerThread.newPlayer(ip, port,model,pc);
        pc.thread.isServer=false;
        //pc.thread.start();
        return pc;
    }
    public ClientServerThread getThread() {
        return thread;
    }
}
