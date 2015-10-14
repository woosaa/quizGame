package application.model;

import application.model.components.KnowledgeFighter;
import application.model.player.Player;
import application.model.util.State;
import java.util.Iterator;
import java.util.regex.Pattern;

/**Use Case Walk with Meeple
 * Created by Simon Roeskamp on 21.01.14.
 */
public class UcWalkWithMeeple {

    private final CoreData data;
    private static final int INDEX_CORRECTION = 1;
    private int counter = 0;
    private boolean walkEnable = true;

    /**
     * Constructor of Use Case "Walk with Meeple"
     * @param data Core Data
     */
    public UcWalkWithMeeple(CoreData data) {
        this.data = data;
    }

     /**
     * If called, the Dice is thrown and depending on the result
     * this Method is able to make some further decisions.
     */
	public void throwDice() {

        int diceEyes = this.data.getDice().throwDice();

		if( diceEyes == 6  ) {
            this.counter = 0;
            this.newKFighterOnSP();
            this.data.setCurrentState(State.THROW_DICE);
			this.setNextPlayer();

        } else if ( this.isAOwnFigureOnPoK() ) {
            this.counter = 0;
            this.data.setCurrentState(State.MOVE);

        } else {
			if ( walkEnable && ++counter >= 3 ) {
				counter = 0;
				this.setNextPlayer();
			}
            this.data.setCurrentState(State.THROW_DICE);
		}
	}

     /**
     * Go further or not
     * @param cFighter Number of moving KnowledgeFighter
     */
	public void move(String cFighter) {
		if (Pattern.matches("[1-3]", cFighter)) {

			int CFighter = Integer.parseInt(cFighter) - INDEX_CORRECTION;
			int newField = this.calcNewField(CFighter);
			int diceEyes = this.data.getDice().getEyes();
            boolean engaded = this.getFigureOnField(newField) != null;
			boolean isOwnFighter = this.isOwnFigureOnField(newField);

            this.walkEnable = !isOwnFighter;

			if ( walkEnable && (diceEyes != 6) && !engaded && this.isAOwnFigureOnPoK() ) {
				this.data.getCurrentPlayer().getKFighter(CFighter).setCurrentField(newField);
				this.setNextPlayer();

            } else if ( !walkEnable && (diceEyes == 6) && this.engadedSP() ) {
                this.setNextPlayer();

            } else if ( walkEnable && engaded ) {
                this.captureFighter(newField, CFighter);
                this.setNextPlayer();
            }
            if ( walkEnable ) this.data.setCurrentState(State.THROW_DICE);

            if ( isOwnFighter && (diceEyes != 6) ) {
                this.data.setCurrentState(State.MOVE);
            }
		}
	}

    /**
     * Change the current Player to the next Player
     */
    private void setNextPlayer() {
		if (this.data.getPlayerQuantity() != 0) {
			this.data.setCurrentPlayer(this.data.getPlayers()
                    .get((this.data.getCurrentPlayer()
                            .getId() + INDEX_CORRECTION) % this.data.getPlayerQuantity()));
		}
	}

    /**
     * @param CFighter Number of Fighter
     * @return Index of the new Field. In case without Fighter return 0
     */
    private int calcNewField(int CFighter) {

        if (this.isAOwnFigureOnPoK()) {

            int currentFieldID = getFieldOfFighter(CFighter);
            int newFieldID = (currentFieldID + this.data.getDice().getEyes()) % 12;

            if ((currentFieldID + this.data.getDice().getEyes()) == 12) {
                return 12;
            } else {
                return newFieldID;
            }
        }
        return 0;
    }

    /**
     * Get a KFighter from pool and set on current Players start point.
     * If any other KFighter (different color) is there -> Shit happens ;)
     */
    private void newKFighterOnSP() {
        int currentField = this.data.getGameBoard().getStartPoint(this.data.getCurrentPlayer().getId());

        if (this.getFigureOnField(currentField) != null) {
            this.ifEngagedKillFighterAndSetOwnFighterOnSP(currentField);
        } else {
            this.getFreeFigure().setCurrentField(currentField);
        }
    }

    /**
     * check KFighter on Field -> if (current player.color equals kFighter.color) do nothing
     * else delete KFighter.
     * @param currentField start point of current Player
     */
    private void ifEngagedKillFighterAndSetOwnFighterOnSP(int currentField) {
        boolean sameColor = this.getFigureOnField(currentField).getColor().equals(this.data.getCurrentPlayer().getColor());

        if (!sameColor) {
            for (int i=0; i < this.data.getPlayerQuantity(); i++) {
                for (int j=0; j < 3; j++ ) {
                    if (this.data.getPlayers().get(i).getKFighter(j).getCurrentField() == currentField) {
                        this.deleteKFighterOnField(currentField);
                    }
                }
            }
            this.getFreeFigure().setCurrentField(currentField);
        }
    }

    /**
     * @return true if start point of current Player is engaded.
     */
    private boolean engadedSP() {
        return this.getFigureOnField( this.getStartPoint() ) != null ;
    }

    /**
     * Get next free KnowledgeFighter
     * @return the next free KnowledgeFighter in Pool
     */
    private KnowledgeFighter getFreeFigure() {

        Iterator<KnowledgeFighter> fighterIterator = this.data.getCurrentPlayer().getKFighter().iterator();
        return this.isKFighterOnField(fighterIterator, 0);

    }

    /**
     * Check the Field for own KFighters
     * @param field is the Field No. to check
     * @return true if a KFighter of current Player stays on the Field
     */
    private boolean isOwnFigureOnField(int field) {

        Iterator<KnowledgeFighter> fighterIterator = this.data.getCurrentPlayer().getKFighter().iterator();
        return this.isKFighterOnField(fighterIterator, field) != null;

    }

    /**
     * @param fighterIterator Iterator of KnowledgeFighter
     * @param field Field to check
     * @return true if a KnowledgeFighter on Field
     */
    private KnowledgeFighter isKFighterOnField(Iterator fighterIterator, int field) {
        KnowledgeFighter kFighter;

        while ( fighterIterator.hasNext() ) {
            kFighter = (KnowledgeFighter)fighterIterator.next();
            if (kFighter.getCurrentField() == field) return kFighter;
        }
        return null;
    }

    /**
     *
     * @return true if any KFighter is out of the pool
     */
    private boolean isAOwnFigureOnPoK() {

        Iterator<KnowledgeFighter> fighterIterator = this.data.getCurrentPlayer().getKFighter().iterator();
        KnowledgeFighter kFighter;

        while (fighterIterator.hasNext()) {
            kFighter = fighterIterator.next();
            if (kFighter.getCurrentField() != 0) return true;
        }
        return false;
    }

    /**
     * Get the KFighter on the Field
     * @param field Field of Figure
     * @return KnowledgeFighter on this Field
     */
    private KnowledgeFighter getFigureOnField(int field) {

        Iterator<Player> playerIterator = this.data.getPlayers().iterator();
        Player currentP;
        while (playerIterator.hasNext()) {
            currentP = playerIterator.next();

            Iterator<KnowledgeFighter> it = currentP.getKFighter().iterator();
            KnowledgeFighter kFighter;

            while (it.hasNext()) {
                kFighter = it.next();
                if (kFighter.getCurrentField() == field) {
                    return kFighter;
                }
            }
        }
        return null;
    }

    /**
     * Get Field for a specific KnowledgeFighter.
     * @param CFighter Number of Fighter
     * @return the Field of given KnowledgeFighterID of current Player.
     */
    private int getFieldOfFighter(int CFighter) {
        return this.data.getCurrentPlayer().getKFighter(CFighter).getCurrentField();
    }

    /**
     * @return start point of current Player
     */
    private int getStartPoint() {
        return this.data.getGameBoard().getField(this.data.getGameBoard()
                .getStartPoint(this.data.getCurrentPlayer().getId())).getId();
    }

    /**
     * Kills Figure on @param newField and set the Figure of current Player.
     * @param CFighter  Number of Fighter
     * @param newField  Field to check
     */
    private void captureFighter(int newField, int CFighter) {
        deleteKFighterOnField(newField);
        this.data.getCurrentPlayer().getKFighter(CFighter).setCurrentField(newField);
    }

    /**
     * Kills Figure on @param newField. Set it on start point if available or pool if not.
     * @param newField  Field to delete KnowledgeFighter
     */
    private void deleteKFighterOnField(int newField) {
        KnowledgeFighter kFighter = this.getFigureOnField(newField);
        if(kFighter != null) {

            String figureColor = kFighter.getColor();
            int startPoint = this.data.getGameBoard().getStartPoint(figureColor);

            if (this.getFigureOnField(startPoint) != null ) {
                kFighter.setCurrentField(0);
            } else {
                kFighter.setCurrentField(startPoint);
            }
        }
    }
}
