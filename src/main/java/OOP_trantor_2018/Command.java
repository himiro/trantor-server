import java.util.*;

class Command
{
  protected String name;
  protected Date end;
  protected long idPlayer;

  Command(String name, Date end, long idPlayer)
  {
    this.name = name;
    this.end = end;
    this.idPlayer = idPlayer;
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
	* Returns value of idPlayer
	* @return
	*/
	public long getIdPlayer()
	{
		return this.idPlayer;
	}

	/**
	* Sets new value of idPlayer
	* @param
	*/
	public void setIdPlayer(long idPlayer)
	{
		this.idPlayer = idPlayer;
	}
}
