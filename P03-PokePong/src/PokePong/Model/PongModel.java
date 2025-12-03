package PokePong.Model;

import PokePong.Controller.GameState;

import java.io.Serializable;

/**
 * Die PongModel-Klasse repräsentiert das Modell des Pong-Spiels und enthält Methoden zur Verwaltung des Spiels.
 * @author  Elif Bilge Isiloglu
 */
public class PongModel implements Serializable {
    /**
     * Die Breite und die Höhe des Pong Spielbereichs
     */
    public int width, height;
    /**
     * Die data die den aktuellen Zustand des Poke-Pong enthält
     */
    public PongData data;
    /**
     * Die größe für das Poke-Pong spiel
     */
     PongSize Size= new PongSize();
    /**
     * Der Spiel 1 im Poke-Pong spiel
     */
    public Player Player1;
    /**
     * Der Spiel 2 im Poke-Pong spiel
     */
     public Player Player2;

    /**
     * Konstanten zur Darstellung von Spielfeldelementen
     */
    public final char EMPTY = '_';
    private final char PADDLE_1 = '|';
    private final char PADDLE_2 = '|';
    private final char BALL = 'O';

    /**
     * Konstruktor für die PongModel-Klasse.
     * @param width  Die Breite des Spielfelds.
     * @param height Die Höhe des Spielfelds.
     */

    public PongModel(int width, int height) {
        this.width = width;
        this.height = height;
        data = PongData.getInstance();
        data.state = GameState.START;
        data.player1 = new Player(Size.PLAYER_SIZE() + Size.PADDLE_WIDTH(), (height - Size.PLAYER_SIZE()) / 2.0 - Size.PADDLE_WIDTH(), Size.PLAYER_SIZE());
        data.player2 = new Player(width - Size.PLAYER_SIZE() - Size.PADDLE_WIDTH(), (height - Size.PLAYER_SIZE()) / 2.0 + Size.PADDLE_WIDTH(), Size.PLAYER_SIZE());
        data.ball = new Ball(width / 2.0, height / 2.0, Size.BALL_SIZE());
    }



    /**
     * Startet ein neues Spiel. Initialziert spieler und ball
     */
    public void startNewGame() {
        data.state = GameState.PLAYING;
        data.player1 = new Player(Size.PLAYER_SIZE() + Size.PADDLE_WIDTH(), (height - Size.PLAYER_SIZE()) / 2.0 - Size.PADDLE_WIDTH(), Size.PLAYER_SIZE());
        data.player2 = new Player(width - Size.PLAYER_SIZE() - Size.PADDLE_WIDTH(), (height - Size.PLAYER_SIZE()) / 2.0 + Size.PADDLE_WIDTH(), Size.PLAYER_SIZE());
        data.ball = new Ball(width / 2.0, height / 2.0, Size.BALL_SIZE());
        data.ball.randomizeAcceleration();
    }

    /**
     * Kollisionen mit Wanden zu shlagen
     */
    public void handleCollisions() {
        if (data.ball.collidesY(0) || data.ball.collidesY(height)) {
            data.ball.acceleration.y *= -1;
        }

        if (data.player1.hits(data.ball) || data.player2.hits(data.ball)) {
            data.ball.acceleration.x *= -1;
        }

        if (data.ball.collidesX(0) || data.ball.collidesX(width)) {
            data.state = GameState.GAME_OVER;
        }
    }

    /**
     * Gibt das PongData-Objekt zurück.
     */
    public PongData getData() {
        return data;
    }

    /**
     * Gibt die Breite des Spielfelds zurück.
     */

    public int getWidth() {
        return width;
    }

    /**
     * Gibt die Höhe des Spielfelds zurück.
     */
    public int getHeight() {
        return height;
    }

    public String toString(){
        return"Ball x: "+getData().getBall().position.x+" "+
                "Ball y:"+getData().getBall().position.y+" "+
                "Player 1 x: "+getData().getPlayer1().position.x+" "+
                "Player 1 y:"+getData().getPlayer1(). position.y+" "+
                "Player 2 x: "+getData().getPlayer2().position.x+" "+
                "Player 2 y:"+getData().getPlayer2().position.y;
    }


    public void setData(PongData obj) {
    }
}