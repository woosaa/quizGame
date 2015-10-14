package application.model.util;


public abstract class Figure
{

    private String id = "";
    private String color = "";
    private int currentField = 0;
    
    /**
     * Constructor of Figure
     * @param color of player
     * @param field new Field of Figure
     */
    protected Figure(String color, int field)
    {
        this.color = color;
        this.currentField = field;
        MD5 newMD5 = new MD5();
        this.id = newMD5.make();
      
    }

    /**
     * Get the ID of this Figure
     * @return id of Figure
     */
    public String getId()
    {
        return id;
    }

    /**
     * Get the color of this Figure
     * @return color of Figure
     */
    public String getColor()
    {
        return color;
    }

    /**
     * Get Field of this Figure
     * @return current Field
     */
	public int getCurrentField() {
		return currentField;
	}

	/**
     * Set current Field of this Figure
     * @param currentField new Field of this Figure
     */
	public void setCurrentField(int currentField) {
		this.currentField = currentField;
	}

    /**
     * Check if both Objects are equal
     * @param o Object to check
     * @return true if Object o is equal to this
     */
    public boolean equals( Object o )
    {
        if ( o == null )
            return false;

        if ( o.getClass() != getClass() )
            return false;

        if ( o == this )
            return true;

        return this.id.equals(((Figure) o).getId());

    }

}
