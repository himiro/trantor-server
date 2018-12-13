import java.util.*;

class Command
{
  protected String name;
  protected Date end;
  protected Player player;

  Command(String name, Date end, Player player)
  {
    this.name = name;
    this.end = end;
    this.player = player;
  }

	Command(String name, Player player)
	{
		this.name = name;
		this.end = null;
		this.player = player;
	}

	/**
	* Returns value of name
	* @return
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* Sets new value of name
	* @param
	*/
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	* Returns value of end
	* @return
	*/
	public Date getEnd()
	{
		return this.end;
	}

	/**
	* Sets new value of end
	* @param
	*/
	public void setEnd(Date end)
	{
		this.end = end;
	}

	/**
	* Returns value of player
	* @return
	*/
	public Player getPlayer()
	{
		return this.player;
	}

	/**
	* Sets new value of player
	* @param
	*/
	public void setPlayer(Player player)
	{
		this.player = player;
	}

}
