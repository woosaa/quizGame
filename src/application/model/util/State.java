package application.model.util;

public class State {

	public static final int START = 0;
	public static final int THROW_DICE = 1;
	public static final int MOVE = 2;
	public static final int EXIT = 3;
	
	private int state = START;


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
