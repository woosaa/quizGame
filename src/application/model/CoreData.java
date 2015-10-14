package application.model;

import application.model.components.Board;
import application.model.components.Dice;
import application.model.player.Player;
import application.model.util.State;

import java.util.ArrayList;

/**Core Data Class
 * Created by Simon Roeskamp on 21.01.14.
 */
public class CoreData {
    private static final int MAX_FIELDS = 12;
    private final ArrayList<Player> players;
    private final Board gameBoard;
    private final Dice dice;
    private int currentState;
    private Player currentPlayer = null;
    private int playerQuantity = 0;

    /**
     * Constructor
     */
    public CoreData(){
        this.gameBoard = new Board(MAX_FIELDS);
        this.players = new ArrayList<Player>(4);
        this.currentState = State.START;
        this.dice = new Dice();
    }

    /**
     * Get all Players
     * @return ArrayList with all players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Get GameBoard
     * @return GameBord
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Get Dice
     * @return the one and only Dice of Game
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Get the current game State
     * @return the current game State
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * Get the current Player
     * @return the current Player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Set the current Player
     * @param currentPlayer Player object
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Get Player quantity
     * @return quantity  of players
     */
    public int getPlayerQuantity() {
        return playerQuantity;
    }

    /**
     * Set player quantity
     * @param playerQuantity player quantity to set
     */
    public void setPlayerQuantity(int playerQuantity) {
        this.playerQuantity = playerQuantity;
    }

    /**
     * Set current State
     * @param currentState current State
     */
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

}
