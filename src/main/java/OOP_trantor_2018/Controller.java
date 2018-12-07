import java.util.*;

class Controller
{
  protected Timeline timeline;
  protected WorldMap worldMap;
  protected List<Team> teams = new ArrayList<Team>();
  protected int maxPlayerPerTeam;

  //debug
  Controller(List<Team> teams)
  {
    this.timeline = new Timeline();
    this.initWorldMap();
    this.teams = teams;
  }

  Controller()
  {
    this.timeline = new Timeline();
    this.initWorldMap();
  }


  /**
  * Returns value of timeline
  * @return
  */
  public Timeline getTimeline()
  {
    return this.timeline;
  }

  /**
  * Sets new value of timeline
  * @param
  */
  public void setTimeline(Timeline timeline)
  {
    this.timeline = timeline;
  }

  /**
  * Returns value of map
  * @return
  */
  public WorldMap getWorldMap()
  {
    return this.worldMap;
  }

  /**
  * Sets new value of map
  * @param
  */
  public void setWorldMap(WorldMap worldMap)
  {
    this.worldMap = worldMap;
  }

  /**
  * Returns value of teams
  * @return
  */
  public List<Team> getTeams()
  {
    return this.teams;
  }

  /**
  * Sets new value of teams
  * @param
  */
  public void setTeams(List<Team> teams)
  {
    this.teams = teams;
  }

  /**
  * Returns value of maxPlayerPerTeam
  * @return
  */
  public int getMaxPlayerPerTeam()
  {
    return this.maxPlayerPerTeam;
  }

  /**
  * Sets new value of maxPlayerPerTeam
  * @param
  */
  public void setMaxPlayerPerTeam(int maxPlayerPerTeam)
  {
    this.maxPlayerPerTeam = maxPlayerPerTeam;
  }

  public void initWorldMap()
  {
    //Creation et initialisation de l'objet Map
    // /!\ Ne pas oublier d'initialiser chaque Tile de la Map
    System.out.println("World Map initialisation");
  }

  public void createCommand(String command, int socketId)
  {
    //create command object to add it to the timeline
    Player tmp = this.findPlayerBySocketId(socketId);

  }

  public void addCommand(Command command)
  {
    //If player's stack !empty : add command to the player.
    //Else : add command to the timeline
    System.out.println("New command in the player' stack");
  }

  //Normalement pas utilisée
  public void removeCommand(Command command)
  {
    //remove from Timeline
    System.out.println("Command removed from the player' stack");
  }

  public void newTeam(String teamName)
  {
    //create new team
    System.out.println("New team");
  }

  public void removeTeam()
  {
    //Fonction à appeler seulement après l'enregistrement des données SQL
    System.out.println("Team removed");
  }

  public void saveResult()
  {
    System.out.println("Save result in SQL");
  }

  protected Player findPlayerBySocketId(int socketId)
  {
    System.out.println("findPlayerBySocketId() " + this.teams.size());
    for (Team team : this.teams)
    {
      System.out.println("Enter in " + team.getTeamName() + " team");
      for (Player player : team.getPlayers())
      {
        System.out.println("Player number " + player.getId() + " / " + player.getIdSocket());
      }
    }
    return new Player();
  }
}
