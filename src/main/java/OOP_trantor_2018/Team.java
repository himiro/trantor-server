import java.util.*;

class Team
{
  protected String teamName;
  protected List<Player> players = new ArrayList<Player>();

  Team()
  {

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
}
