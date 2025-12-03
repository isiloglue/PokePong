package PokePong.Model;

import PokePong.Controller.GameState;

import java.io.Serializable;

/**
 * Die PongData-Klasse hält den aktuellen Zustand des Pong-Spiels und die zugehörigen Daten
 * @author  Elif Bilge Isiloglu
 */

public class PongData implements Serializable {
    /**
     * Statische Instanz der PongData-Klasse, um sicherzustellen, dass nur eine Instanz existiert
     */

        public static PongData instance = null;
    /**
     * Der Spielzusatand des Poke-Pong spiel
     */
    public GameState state;
    /**
     * Der Spieler 1 im Poke-Pong spiel
     */
        public Player player1;
    /**
     *Der Spieler 2 im Poke-Pong spiel
     */
    public Player player2;
    /**
     * Der Ball im Poke-Pong spiel
     */
        public Ball ball;

    /**
     * Methode, um eine Instanz von PongData zu erhalten. Falls keine Instanz vorhanden ist, wird eine neue erstellt.
     * @return Die Instanz von PongData.
     */

    public static PongData getInstance() {//Yalnizca pongdata olusturur varsa olan pong datayi döndürür
            if (instance == null) {
                instance = new PongData();
            }
            return instance;
        }
    /**
     * Gibt den aktuellen Spielzustand zurück.
     *
     * @return Der Spielzustand.
     */


    /**
     * Gibt Spieler 1 zurück.
     *
     * @return Spieler 1.
     */

        public Player getPlayer1() {
            return player1;
        }

    /**
     * Gibt Spieler 2 zurück.
     *
     * @return Spieler 2.
     */

        public Player getPlayer2() {
            return player2;
        }
    /**
     * Gibt den Ball zurück.
     *
     * @return Der Ball.
     */

        public Ball getBall() {
            return ball;
        }
    }



/**
 * Diese teil ist nicht aktiv
 */

     /*public String toString() {
        char[] jshell = new char[42];

        if (data.state == GameState.START) {

        } else if (data.state == GameState.PLAYING) {

        } else if (data.state == GameState.GAME_OVER) {

        }

        return String.format(
                "%c %c %c %c %c %c\n%c %c %c %c %c %c\n%c %c %c %c %c %c",
                jshell[0], jshell[1], jshell[2], jshell[3], jshell[4], jshell[5],
                jshell[6], jshell[7], jshell[8], jshell[9], jshell[10], jshell[12],
                jshell[13], jshell[14], jshell[15], jshell[16], jshell[17], jshell[18],
                jshell[19], jshell[20], jshell[21], jshell[22], jshell[23], jshell[24],
                jshell[25], jshell[26], jshell[27], jshell[28], jshell[29], jshell[30],
                jshell[31], jshell[32], jshell[33], jshell[34], jshell[35], jshell[36]
        );
    }*/

