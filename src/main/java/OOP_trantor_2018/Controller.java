import java.util.*;

class Controller
{
  protected Timeline timeline;
  protected Map map;
  protected List<Team> teams = new ArrayList();

  Controller()
  {
    this.timeline = new Timeline();
    this.initMap();
  }

  public Timeline getTimeline()
  {
    return this.timeline;
  }

  public void setTimeline(Timeline timeline)
  {
    this.timeline = timeline;
  }

  public Map getMap()
  {
    return this.map;
  }

  public void setMap(Map map)
  {
    this.map = map;
  }

  public List<Team> getTeams()
  {
    return this.teams;
  }

  public void setTeams(List<Team> teams)
  {
    this.teams = teams;
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
