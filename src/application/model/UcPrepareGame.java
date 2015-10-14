package application.model;

import application.model.components.KnowledgeFighter;
import application.model.player.Player;
import application.model.util.State;

/**Use Case Prepare Game
 * Created by Simon Roeskamp on 21.01.14.
 */
public class UcPrepareGame {


    private final CoreData data;

    /**
     * Constructor
     * @param data Core Data
     */
    public UcPrepareGame(CoreData data){
        this.data = data;
    }

    /**
     * Start the game
     */
    public void startGame() {
        this.generateGameVar();
    }

    /**
     * Set the player headcount
     * @param quantity quantity of players
     */
    public void setPlayerQuantity(int quantity) {
        this.data.setPlayerQuantity(quantity);
    }

    private void generateGameVar() {
        for (int i = 0; i < this.data.getPlayerQuantity(); i++) {
            Player newPlayer = new Player(i);
            this.data.getPlayers().add(newPlayer);

            switch (i) {
                case 0:
                    this.data.getPlayers().get(i).setColor("RED");
                    this.createNewFighter("RED", i);
                    break;
                case 1:
                    this.data.getPlayers().get(i).setColor("BLUE");
                    this.createNewFighter("BLUE", i);
                    break;
                case 2:
                    this.data.getPlayers().get(i).setColor("GREEN");
                    this.createNewFighter("GREEN", i);
                    break;
                case 3:
                    this.data.getPlayers().get(i).setColor("YELLOW");
                    this.createNewFighter("YELLOW", i);
                    break;
            }
        }
        this.data.setCurrentPlayer(this.data.getPlayers().get(0));
        this.data.setCurrentState(State.THROW_DICE);
    }

    /**
     * Create a new KnowledgeFighter
     * @param color of player
     */
    private void createNewFighter(String color, int pNo) {
        for (int i = 0; i < 3; i++) {
            KnowledgeFighter newFighter = new KnowledgeFighter(color);
            this.data.getPlayers().get(pNo).getKFighter().add(newFighter);
        }
    }

}
