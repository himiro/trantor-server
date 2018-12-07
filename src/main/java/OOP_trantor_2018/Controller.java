import java.util.*;

class Controller
{
  protected Timeline timeline;
  protected WorldMap worldMap;
  protected Map<String, Team> teams = new HashMap<String, Team>();
  protected int maxPlayerPerTeam;

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
  public Map<String, Team> getTeams()
  {
    return this.teams;
  }

  /**
  * Sets new value of teams
  * @param
  */
  public void setTeams(Map<String, Team> teams)
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
    System.out.println("Map initialisation");
  }

  public void addCommand(String command, String teamName, int socketPlayer)
  {
    //If player's stack !empty : add command to the player.
    //Else : add command to the timeline
    System.out.println("New command in the player' stack");
  }

  //Normalement pas utilisée
  public void removeCommand(String command)
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
}
