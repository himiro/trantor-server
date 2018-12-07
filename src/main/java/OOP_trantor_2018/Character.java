abstract class Character
{
  public enum Status
  {
    LAYED,
    HATCHED,
    ALIVE,
    DEAD,
  }
  protected int x;
  protected int y;
  protected long idSocket;
  protected String teamName;
  protected Status status;

  Character(int x, int y, String teamName, Status status, long idSocket)
  {
    this.x = x;
    this.y = y;
    this.idSocket = idSocket;
    this.teamName = teamName;
    this.status = status;
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
  * Returns value of id
  * @return
  */
  public long getIdSocket()
  {
    return this.idSocket;
  }

  /**
  * Sets new value of id
  * @param
  */
  public void setIdSocket(long idSocket)
  {
    this.idSocket = idSocket;
  }

  /**
  * Returns value of teamName
  * @return
  */
  public String getTeamName()
  {
    return this.teamName;
  }

  /**
  * Sets new value of teamName
  * @param
  */
  public void setTeamName(String teamName)
  {
    this.teamName = teamName;
  }

  /**
  * Returns value of status
  * @return
  */
  public Status getStatus()
  {
    return this.status;
  }

  /**
  * Sets new value of status
  * @param
  */
  public void setStatus(Status status)
  {
    this.status = status;
  }

  public void dead()
  {
    System.out.println("Dead Character");
  }
}
