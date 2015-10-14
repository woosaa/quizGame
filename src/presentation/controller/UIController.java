package presentation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import application.model.Fassade;

public class UIController {

	private final Fassade fassade;
	private String input = "";
	
	public UIController( Fassade fassade ){
        this.fassade = fassade;
	}

    public void startGame() {
        boolean again = true;
        do {
           String input = this.readConsole();
           if (Pattern.matches("[2-4]", input)) {
               int inputInt = Integer.parseInt(input);
               this.fassade.setPlayerQuantity(inputInt);
               this.fassade.startGame();
               again = false;
           }
         } while ( again );
    }

	public void throwDice() {
		boolean again = true;
		do {
			input = this.readConsole();
			if (Pattern.matches("w", input)) {
				again = false;
			this.fassade.throwDice();
			}
		} while ( again );
	}

    public void move() {
            // Input is the KnowledgeFighter
            boolean available;
            boolean again = true;
            do {
                input = this.readConsole();
                if ( Pattern.matches("[1-3]", input ) ) {
                    available = isKFighterAvailable(Integer.parseInt(input)-1);
                    if ( available ) {
                    again = false;
                    this.fassade.move(input);
                    }
                }
            } while ( again );
        }

    private boolean isKFighterAvailable(int kNo) {
               return this.fassade.getKFighterField(kNo) != 0;
        }

    private String readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader( System.in ));
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Failure, again please.");
        }
        return input;

    }

}
