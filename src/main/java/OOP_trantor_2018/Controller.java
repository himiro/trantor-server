import java.util.*;

class Controller
{
  protected Timeline timeline;
  protected WorldMap map;
  protected List<Team> teams = new ArrayList();
  protected int maxPlayerPerTeam;

  Controller()
  {
    this.timeline = new Timeline();
    this.initMap();
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
  public WorldMap getMap()
  {
    return this.map;
  }

  /**
  * Sets new value of map
  * @param
  */
  public void setMap(WorldMap map)
  {
    this.map = map;
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

  public void initMap()
  {
    //Creation et initialisation de l'objet Map
    // /!\ Ne pas oublier d'initialiser chaque Tile de la Map
    System.out.println("Map initialisation");
  }

  public void addCommandToTimeline()
  {
    System.out.println("New command in the timeline");
  }

  public void removeCommandFromTimeline()
  {
    System.out.println("Command removed in the timeline");
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
