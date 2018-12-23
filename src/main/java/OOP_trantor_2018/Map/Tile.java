import java.util.*;

class Tile
{
    protected int x;
    protected int y;
    protected Map<String, Ressource> ressources = new HashMap<String, Ressource>();
    protected List<Player> players = new ArrayList<Player>();
    protected List<Egg> egg = new ArrayList<Egg>();

    public Tile()
    {
    }

    public Tile(int x, int y, Map<String, Ressource> ressources)
    {
        this.x = x;
        this.y = y;
        this.ressources = ressources;
    }

    /**
    * Returns value of x
    * @return
    */
    public int getX()
    {
        return this.x;
    }

    /**
    * Sets new value of x
    * @param
    */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
    * Returns value of y
    * @return
    */
    public int getY()
    {
        return this.y;
    }

    /**
    * Sets new value of y
    * @param
    */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
    * Returns value of ressources
    * @return
    */
    public Map<String, Ressource> getRessources()
    {
        return this.ressources;
    }

    /**
    * Sets new value of ressources
    * @param
    */
    public void setRessources(Map<String, Ressource> ressources)
    {
        this.ressources = ressources;
    }

    /**
    * Returns value of players
    * @return
    */
    public List<Player> getPlayers()
    {
        return this.players;
    }

    /**
    * Sets new value of players
    * @param
    */
    public void setPlayers(List<Player> players)
    {
        this.players = players;
    }

    /**
    * Returns value of egg
    * @return
    */
    public List<Egg> getEgg()
    {
        return this.egg;
    }

    /**
    * Sets new value of egg
    * @param
    */
    public void setEgg(List<Egg> egg)
    {
        this.egg = egg;
    }

    public String displayRessources()
    {
        boolean firstElement = true;
        String display = "";

        for (Map.Entry<String, Ressource> entry : this.ressources.entrySet())
        {
            if (entry.getValue().getNb() > 0)
            {
                display += entry.getKey() + " ";
            }
        }
        display = display.trim();
        display += ",";
        return display;
    }
}
