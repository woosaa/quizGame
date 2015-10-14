package presentation.view;

import application.model.Fassade;
import application.model.api.IObserver;
import application.model.util.State;
import presentation.controller.UIController;

public class UIConsole implements IObserver {

	private static final int MAX_FIGHTERS = 3;

    private final UIController uiController;
	private final Fassade fassade;
    private boolean showDice = false;

	public UIConsole(UIController uiC, Fassade f) {
		this.uiController = uiC;
		this.fassade = f;
		this.fassade.attach(this);
		this.display();
	}

    /**
     * Observer update
     */
	public void update() {
		this.display();
	}

    /**
     * Prints the game on console
     */
	private void display() {
        while (true) {
			switch ( fassade.getState() ) {
			case State.START:
                System.out.println("            initiate Big Bang               ");
                System.out.println("    planetary boundary layer chill down     ");
				System.out.println("            earth's mantle crust            ");
                System.out.println("            ...foreshorten...               ");
                System.out.println("       hand over the reins of power       \n");

                System.out.println("      I KNOW SOMETHING YOU DON'T KNOW     \n");
                System.out.println("             Category fixed                 ");
				System.out.println("  technology  | sport | economy | nature  \n");
				  System.out.print("  Please, enter headcount: ");
                this.uiController.startGame();
				break;
				
			case State.THROW_DICE:
				this.printHeadLine();

                if ( showDice ) {
					System.out.println("    currently cubed: " + this.fassade.getDiceEyes());
					System.out.print("\n");
				} else {
					showDice = true;
				}
				System.out.println("    " + this.fassade.getPlayerColor() + " on the move");
				  System.out.print("    throw Dice with w: ");
				this.uiController.throwDice();
				break;
				
			case State.MOVE:
				this.printHeadLine();
				System.out.println("    currently cubed: " + this.fassade.getDiceEyes());
				  System.out.print("    choose meeple:");
				this.uiController.move();
				break;

            case State.EXIT:
                this.fassade.detach(this);
                System.exit(0);
            }
		}
	}

    /**
     * Prints the headline
     */
	private void printHeadLine() {
		
		for (int i = 0; i < this.fassade.getPlayerQuantity(); i++) {
			
			System.out.print("      Meeple for " + this.fassade.getPlayerColor(i) + ":");
			
			for (int j = 0; j < MAX_FIGHTERS; j++) {
                try {

                    int currentFigureID = this.fassade.getKFighterField(i, j);
                    if (currentFigureID != 0) {
                        System.out.print("\t\t"
                                + this.fassade.getKFighterColor(i, j) + " " + (j + 1));

                        System.out.print("  F" + currentFigureID);
                    }

                } catch (NullPointerException ex) {
                    System.out.println("Failure " + ex);
                }
		    }
            System.out.print("\n");
	    }
    }
}
