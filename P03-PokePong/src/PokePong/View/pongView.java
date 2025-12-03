package PokePong.View;

import PokePong.Controller.Controller;
import PokePong.Controller.GameState;
import PokePong.Main;
import PokePong.Model.Ball;
import PokePong.Model.Player;
import PokePong.Model.PongData;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

/**
 *  @author  Elif Bilge Isiloglu
 * Die pongView-Klasse implementiert das View-Interface
 * */

public class pongView extends PApplet implements View {
    /**
     * Der zugehörige Controller für die Benutzeroberfläche.
     */
    public Controller controller;

    /**
     * Das Bild für den Startbildschirm.
     */
    public PImage startScreen;

    /**
     * Das Bild für den Game-Over-Bildschirm.
     */
    public   PImage gameOverScreen;
    /**
     * Das Bild für Spieler 1.
     */
    public PImage player1Image;
    /**
     * Das Bild für Spieler 2.
     */
    public PImage player2Image;
    /**
     * Das Bild für den Ball.
     */
    public PImage ballImage;
    /**
     * Die PongData, die den aktuellen Zustand des Pong-Spiels enthält.
     */
    public PongData data;
    /**
     * Die `PongView`-Klasse verwendet diese Konstanten für die Größenkonfiguration der Paddel.
     */
    final int PADDLE_WIDTH=5;
    /**
     * Die `PongView`-Klasse verwendet diese Konstante für die Größe des Balls.
     */

    final int BALL_SIZE=25;
    /**
     * Die `PongView`-Klasse verwendet diese Konstante für die Größe der Spieler.
     */
    final int PLAYER_SIZE=50;
    /**
     * Konstruktor für die pongView-Klasse.
     * @param width  Die Breite der Benutzeroberfläche.
     * @param height Die Höhe der Benutzeroberfläche.
     */
    public pongView(int width, int height){
        setSize(width,height);
    }
    /**
     * Setzt den Controller.
     *
     * @param controller Der zugehörige Controller.
     */
    public void setController(Controller controller){
        this.controller=controller;
    }

    /**
     * Hauptmethode, um die Anwendung zu starten.
     */
    public static void main(String[]args){
        PApplet.main(Main.class);
    }

    /**
     * Initialisiert die Benutzeroberfläche.
     * Lädt die Bilder für den Startbildschirm.
     * Lädt die Bilder für den Spieler 1, Spieler 2 und den Ball.
     */


    @Override
    public void setup() {
        data = new PongData();
        data.state =  GameState.START;

        startScreen = loadImage("images/StartScreen.jpg");
        gameOverScreen = loadImage("images/GameOverScreen.jpg");
        player1Image = loadImage("images/025.png");
        player2Image = loadImage("images/133.png");
        ballImage = loadImage("images/100.png");

    }
    /**
     * Einstellungen für die Größe der Benutzeroberfläche.
     */
    public void settings(){
        size(width,height);{
            pixelDensity(1);
        }
    }
    /**
     * Zeichnet die Benutzeroberfläche entsprechend dem aktuellen Spielzustand.
     */
    public void draw(){
        controller.nextFrame();

    }

    /**
     * Zeichnet den Startbildschirm.
     */

    public void drawStart(){
        background(0);
        imageMode(CORNER);
        image(startScreen,0,0,width,height);
    }
    /**
     * Zeichnet den Game-Over-Bildschirm.
     */

    public void drawGG(){
        imageMode(CORNER);
        image(gameOverScreen,0,0,width,height);

    }

    /**
     * Zeichnet das Spiel mit den aktuellen Positionen von Spieler 1, Spieler 2 und dem Ball.
     *
     * @param p1            Spieler 1.
     * @param p2            Spieler 2.
     * @param ball          Der Ball.
     * @param PADDLE_WIDTH  Die Breite der Schläger.
     */
    public void drawGame(Player p1, Player p2, Ball ball, int PADDLE_WIDTH){//

        background(255);
        imageMode(CENTER);
        image(player1Image, (float) (p1.getPosition().getX() - p1.getSize()/2.0 - PADDLE_WIDTH), p1.getPosition().getY(), p1.getSize(),p1.getSize() );
        fill(0);
        rect(p1.getPosition().getX() - PADDLE_WIDTH, (float) (p1.getPosition().getY() - p1.getSize()/2.0), PADDLE_WIDTH, p1.getSize());
        image(player2Image, (float) (p2.getPosition().getX() + p2.getSize()/2.0 + PADDLE_WIDTH),p2.getPosition().getY(), p2.getSize(), p2.getSize());
        rect(p2.getPosition().getX(), (float) (p2.getPosition().getY() -p2.getSize()/2.0), PADDLE_WIDTH, p2.getSize());

        // Draw ball
        translate(ball.getPosition().x, ball.getPosition().y);
        rotate(ball.getRotationAngle());
        image(ballImage, 0, 0, ball.getSize(), ball.getSize());
        updateBallPosition(ball);
    }
    /**
     * Behandelt Tastatureingaben und übergibt sie dem Controller.
     */


    public void keyPressed(KeyEvent event) {
        char lock = key;
        if (!controller.getThread().isServer() && key == CODED && keyCode == UP) {
            lock = 'u';
            controller.getThread().send(lock);
            System.out.println("Up");
        } else if (!controller.getThread().isServer() && key == CODED && keyCode == DOWN) {
            lock = 'd';
            controller.getThread().send(lock);
            System.out.println("down");
        } else if (controller.getThread().isServer && key != CODED && key != 'u' && key != 'd') {
            lock = key;
        } else lock = '1';
        controller.userInput(lock);
    }
        /**
         * Aktualisiert die Position des Balls.
         *
         * @param ball Der Ball.
         */
    public void updateBallPosition(Ball ball){
        ball.updateBallPosition(1/frameRate);}

    /**
     * Alternativer Konstruktor für die pongView-Klasse.
     *
     * @param controller      Der zugehörige Controller.
     * @param startScreen     Das Bild für den Startbildschirm.
     * @param gameOverScreen  Das Bild für den Game-Over-Bildschirm.
     * @param player1Image    Das Bild für Spieler 1.
     * @param player2Image    Das Bild für Spieler 2.
     * @param ballImage       Das Bild für den Ball.
     * @param data            Die PongData-Instanz für den aktuellen Spielzustand.
     */
    public pongView(Controller controller, PImage startScreen, PImage gameOverScreen, PImage player1Image, PImage player2Image, PImage ballImage, PongData data) {
        this.controller = controller;
        this.startScreen = startScreen;
        this.gameOverScreen = gameOverScreen;
        this.player1Image = player1Image;
        this.player2Image = player2Image;
        this.ballImage = ballImage;
        this.data = data;
    }
    /**
     * Startet ein neues Spiel.
     */
    private void startNewGame() {
    }

}
