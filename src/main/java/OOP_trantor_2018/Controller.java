import java.util.*;

class Controller
{
  protected Timeline timeline;
  protected WorldMap worldMap;
  protected List<Team> teams = new ArrayList<Team>();
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
    System.out.println("Map initialisation");
  }

  public void addCommand()
  {
    //If player's stack !empty : add command to the player.
    //Else : add command to the timeline
    System.out.println("New command in the player stack");
  }

  public void removeCommand()
  {
    //If player's stack !empty : add command to the player.
    //Else : add command to the timeline
    System.out.println("Command removed from the player's stack");
  }

  public void newTeam()
  {
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

  public void addCommandToPlayer()
  {
    System.out.println("Add command to player stack");
  }

  public void removeCommmandFromPlayer()
  {
    System.out.println("Remove command from player stack");
  }

}
