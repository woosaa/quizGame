package application.model.player;

import java.util.ArrayList;

import application.model.components.KnowledgeFighter;

public class Player {

	private int id = 0;
	private String color;
	private final ArrayList<KnowledgeFighter> KFighter;

	public Player(int id){
		this.id = id;
        color = "";
        KFighter = new ArrayList<KnowledgeFighter>();
    }

	public int getId() {
		return this.id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<KnowledgeFighter> getKFighter() {
		return this.KFighter;
	}

	public KnowledgeFighter getKFighter(int index) {
		assert this.KFighter.get(index) != null: "non Available";
		return this.KFighter.get(index);
	}
}
