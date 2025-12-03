package PokePong.Model;

import java.io.Serializable;

/**
 * Das PongSize-Record definiert die Size für verschiedene Elemente im Pong-Spiel.
 * @author  Elif Bilge Isiloglu
 */

    public record PongSize(int PADDLE_WIDTH,int BALL_SIZE,int PLAYER_SIZE) implements Serializable {
    /**
     * Konstruktor für PongSize, der Size initialisiert.
     */
        public PongSize() {
            this (10, 20, 60);
        }
    }
