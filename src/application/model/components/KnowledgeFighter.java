package application.model.components;

import application.model.util.Figure;

public class KnowledgeFighter extends Figure
{


    public KnowledgeFighter(String color)
    {
        super( color, 0);
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o == null ) return false;

        if (this.getClass() == o.getClass()) return true;

        if (this == o) return true;

        return this.getId().equals(((Figure) o).getId());

    }

}
