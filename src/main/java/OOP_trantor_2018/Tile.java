class Tile
{
  protected int x;
  protected int y;
  protected List<Ressource> ressources;
  protected List<Player> players;
  protected List<Egg> egg;

	public Tile(int x, int y, List<Ressource> ressources)
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
	public List<Ressource> getRessources()
	{
		return this.ressources;
	}

	/**
	* Sets new value of ressources
	* @param
	*/
	public void setRessources(List<Ressource> ressources)
	{
		this.ressources = ressources;
	}

	/**
	* Returns value of players
	* @return
	/**
	* Returns value of e
	* @return
	*/
	public Til getE()
	{
		return this.e;
	}

	/**
	* Sets new value of e
	* @param
	*/
	public void setE(Til e)
	{
		this.e = e;
	}
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

}
