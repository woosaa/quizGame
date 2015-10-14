package application.model.components;


/**
 * a Field of GameBoard.
 * @author conquestor
 *
 */
public class Field {

	private int id = 0;
	private String color = "";
	
	public Field(int id) {
		this.id = id;
	}

    @SuppressWarnings("unused")
	public String getColor() {
		return color;
	}

    @SuppressWarnings("unused")
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getId() {
		return id;
	}
}
