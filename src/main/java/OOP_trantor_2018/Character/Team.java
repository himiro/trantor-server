import java.util.*;

class Team
{
  protected String teamName;
  protected List<Player> players = new ArrayList<Player>();
  protected int nbClients = 3;

  Team(String teamName)
  {
    this.teamName = teamName;
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

  /**
  * Returns value of teamName
  * @return
  */
  public int getNbClients()
  {
    return this.nbClients;
  }

  /**
  * Sets new value of teamName
  * @param
  */
  public void setNbClients(int nb)
  {
    this.nbClients = nb;
  }
}
