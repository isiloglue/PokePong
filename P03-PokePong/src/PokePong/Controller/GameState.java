package PokePong.Controller;

/**
 * Ein enum für den Spiel, dass informationen geben über Spielzustand
 * @author ELif Bilge Isiloglu
 */

public enum GameState {
    /**
     * Der anfangzustand des Spiel
     */
    START,
    /**
     * Der Zustand wärend des Spiel
     */
    PLAYING,
    /**
     * Der Zustand, wenn das Spiel beendet ist
     */
    GAME_OVER;

}