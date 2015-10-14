package application.model;

import application.model.api.IFassade;
import application.model.util.Subject;

public class Fassade extends Subject implements IFassade{

    private final UcPrepareGame prepareGame;
    private final UcWalkWithMeeple ucWalkWithMeeple;
    private final CoreData data;

    public Fassade() {
        data = new CoreData();
        this.prepareGame = new UcPrepareGame(data);
        this.ucWalkWithMeeple = new UcWalkWithMeeple(data);
	}

	// Use Case Prepare Game
    public void startGame() {
        this.prepareGame.startGame();
    }

    public void setPlayerQuantity(int headcount){
        this.prepareGame.setPlayerQuantity(headcount);
    }

    // Use Case Walk with Meeple
    public void move(String fNo) {
        this.ucWalkWithMeeple.move(fNo);
        notifyObservers();
    }

    public void throwDice() {
        this.ucWalkWithMeeple.throwDice();
        notifyObservers();
    }

    // Core Data Access
    public int getState(){
        return data.getCurrentState();
    }

    public int getDiceEyes(){
        return data.getDice().getEyes();
    }

    public String getPlayerColor(){
        return data.getCurrentPlayer().getColor();
    }

    public int getPlayerQuantity(){
        return this.data.getPlayerQuantity();
    }

    public String getKFighterColor(int pNo, int fNo){
        return this.data.getPlayers().get(pNo).getKFighter(fNo).getColor();
    }

    public String getPlayerColor(int pNo) {
        return this.data.getPlayers().get(pNo).getColor();
    }

    public int getKFighterField(int pNo, int fNo){
        return this.data.getPlayers().get(pNo).getKFighter(fNo).getCurrentField();
    }

    public int getKFighterField(int kNo){
        return this.data.getCurrentPlayer().getKFighter(kNo).getCurrentField();
    }
}
