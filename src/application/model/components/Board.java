package application.model.components;

import java.util.ArrayList;

/**
 * PlayBoard of "I KNOW SOMETHING YOU DON'T KNOW"
 * @author conquestor
 */
public class Board {
	
	private final ArrayList<Field> fields = new ArrayList<Field>();
	private static final int START_POINT_RED = 1;
	private static final int START_POINT_BLUE = 4;
	private static final int START_POINT_GREEN = 7;
	private static final int START_POINT_YELLOW = 10;
	
	public Board(int newFields){
		this.initBoard(newFields);
	}
	
	/**
	 * Just called once on init.
	 * @param newFields Number new Fields
	 */
	private void initBoard(int newFields) {
		for(int i=0; i<newFields ;i++){
			this.fields.add(new Field(i));
		}
	}
	
	/**
	 * @param index Position on Board
	 * @return the Field-Object on "index" Position
	 */
	public Field getField(int index) {
		return this.fields.get(index);
	}

	public int getStartPoint(int playerID) {
		assert 0 <= playerID & playerID <=3: "FAIL - No valid ID";
		switch(playerID){
		case 0: return START_POINT_RED;
		case 1: return START_POINT_BLUE;
		case 2: return START_POINT_GREEN;
		case 3: return START_POINT_YELLOW;
		default: return -1;
		}
	}
	
	public int getStartPoint(String pColor) {
        if (pColor.equals("RED")) {
            return START_POINT_RED;
        } else if (pColor.equals("BLUE")) {
            return START_POINT_BLUE;
        } else if (pColor.equals("GREEN")) {
            return START_POINT_GREEN;
        } else if (pColor.equals("YELLOW")) {
            return START_POINT_YELLOW;
        } else {
            return -1;
        }
	}
}
