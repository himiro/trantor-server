import java.util.*;
import java.io.PrintWriter;

class Player extends Character
{
  protected Orientation orientation;
  protected int level;
  protected int vision;
  protected Map<String, Ressource> inventory = new HashMap<String, Ressource>();
  protected static int nb = 1;
  protected int id;
  protected Queue<Command> queue = new LinkedList<Command>();
  protected Date isFeed;
  protected PrintWriter writer;

  Player(int x, int y, int idSocket, String teamName, Orientation orientation, Map<String, Ressource> inventory, int frequence, PrintWriter writer)
  {
    super(x, y, teamName, Status.ALIVE, idSocket);
    this.orientation = orientation;
    this.level = 0;
    this.vision = 1;
    this.inventory = inventory;
    this.id = this.nb++;
    this.isFeed = new Date(System.currentTimeMillis() + (126 * 10000) / frequence);
    this.writer = writer;
  }

  /**
  * Returns value of id
  * @return
  */
  public int getId()
  {
    return this.id;
  }

  /**
  * Returns value of orientation
  * @return
  */
  public Orientation getOrientation()
  {
    return this.orientation;
  }

  /**
  * Sets new value of orientation
  * @param
  */
  public void setOrientation(Orientation orientation)
  {
    this.orientation = orientation;
  }

  /**
  * Returns value of level
  * @return
  */
  public int getLevel()
  {
    return this.level;
  }

  /**
  * Sets new value of level
  * @param
  */
  public void setLevel(int level)
  {
    this.level = level;
  }

  /**
  * Returns value of vision
  * @return
  */
  public int getVision()
  {
    return this.vision;
  }

  /**
  * Sets new value of vision
  * @param
  */
  public void setVision(int vision)
  {
    this.vision = vision;
  }

  /**
  * Returns value of inventory
  * @return
  */
  public Map<String, Ressource> getInventory()
  {
    return this.inventory;
  }

  /**
  * Sets new value of inventory
  * @param
  */
  public void setInventory(Map<String, Ressource> inventory)
  {
    this.inventory = inventory;
  }

  /**
  * Returns value of queue
  * @return
  */
  public Queue<Command> getQueue()
  {
    return this.queue;
  }

  /**
  * Sets new value of queue
  * @param
  */
  public void setQueue(Queue<Command> queue)
  {
    this.queue = queue;
  }

  /**
  * Returns value of isFeed
  * @return
  */
  public Date getIsFeed()
  {
    return this.isFeed;
  }

  /**
  * Sets new value of isFeed
  * @param
  */
  public void setIsFeed(Date isFeed)
  {
    this.isFeed = isFeed;
  }

  /**
  * Returns value of writer
  * @return
  */
  public PrintWriter getWriter()
  {
    return this.writer;
  }

  /**
  * Sets new value of isFeed
  * @param
  */
  public void setWriter(PrintWriter writer)
  {
    this.writer = writer;
  }

  public String feed(int frequence, Graphical graphical)
  {
    if (System.currentTimeMillis() > this.isFeed.getTime())
    {
      int nb = this.getInventory().get("Food").getNb();
      if (nb > 0)
      {
        this.getInventory().get("Food").setNb(--nb);
        this.isFeed = new Date(System.currentTimeMillis() + (126 * 10000) / frequence);
        System.out.println("Player eats");
        return "";
      }
      graphical.writeToGraphical("pdi " + this.id);
      return "dead";
    }
    return "";
  }

  public String forward(int sizeX, int sizeY)
  {
    switch (this.orientation)
    {
      case NORTH:
      System.out.println("Player goes North");
      this.y++;
      if (this.y > sizeY)
      this.y = 0;
      break;
      case EAST:
      System.out.println("Player goes East");
      this.x++;
      if (this.x > sizeX)
      this.x = 0;
      break;
      case WEST:
      System.out.println("Player goes West");
      this.x--;
      if (this.x < 0)
      this.x = sizeX;
      break;
      case SOUTH:
      System.out.println("Player goes South");
      this.y--;
      if (this.y < 0)
      this.y = sizeY;
      break;
      default:
      break;
    }
    System.out.println("X : " + this.x + " Y : " + this.y);
    return "true";
  }

  public String left()
  {
    switch (this.orientation)
    {
      case NORTH:
      this.orientation = orientation.WEST;
      System.out.println("Player turns WEST");
      break;
      case EAST:
      this.orientation = orientation.NORTH;
      System.out.println("Player turns North");
      break;
      case WEST:
      this.orientation = orientation.SOUTH;
      System.out.println("Player turns South");
      break;
      case SOUTH:
      this.orientation = orientation.EAST;
      System.out.println("Player turns East");
      break;
    }
    return "true";
  }

  public String right()
  {
    switch (this.orientation)
    {
      case NORTH:
      this.orientation = orientation.EAST;
      System.out.println("Player turns East");
      break;
      case EAST:
      this.orientation = orientation.SOUTH;
      System.out.println("Player turns South");
      break;
      case WEST:
      this.orientation = orientation.NORTH;
      System.out.println("Player turns North");
      break;
      case SOUTH:
      this.orientation = orientation.WEST;
      System.out.println("Player turns West");
      break;
    }
    return "true";
  }

  public String look(Tile tile)
  {
    return tile.displayRessources();
  }

  public String inventory()
  {
    return ("food " + this.inventory.get("Food").getNb() + ", linemate " + this.inventory.get("Linemate").getNb() + ", deraumere " + this.inventory.get("Deraumere").getNb() + ", sibur " + this.inventory.get("Sibur").getNb()
    + ", mendiane " + this.inventory.get("Mendiane").getNb() + ", phiras " + this.inventory.get("Phiras").getNb() + ", thystame " + this.inventory.get("Thystame").getNb());
  }

  public String broadcast()
  {
    System.out.println("Player make a sound");
    return "true";
  }

  public String take(Tile tile, Item object, Graphical graphical)
  {
    int nbPlayer = this.getInventory().get(object.getName()).getNb();
    int nbTile = tile.getRessources().get(object.getName()).getNb();
    if (nbTile > 0)
    {
      tile.getRessources().get(object.getName()).setNb(--nbTile);
      this.getInventory().get(object.getName()).setNb(++nbPlayer);
      graphical.writeToGraphical("pgt " + this.id + " " + object.getNumberItem(object));
      return "true";
    }
    return "false";
  }

  public String set(Tile tile, Item object, Graphical graphical)
  {
    int nbPlayer = this.getInventory().get(object.getName()).getNb();
    int nbTile = tile.getRessources().get(object.getName()).getNb();
    if (nbPlayer > 0)
    {
      tile.getRessources().get(object.getName()).setNb(++nbTile);
      this.getInventory().get(object.getName()).setNb(--nbPlayer);
      graphical.writeToGraphical("pdr " + this.id + " " + object.getNumberItem(object));
      return "true";
    }
    return "false";
  }

  public String elevation()
  {
    System.out.println("Players are doing some magic stuff");
    return "true";
  }

  public String fork()
  {
    System.out.println("Player reproducted");
    return "true";
  }

  public String eject(List<Player> players, int sizeX, int sizeY, Graphical graphical)
  {
    boolean areOthers = false;

    if (players != null)
    {
      for (Player pl : players)
      {
        switch(this.orientation)
        {
          case SOUTH:
          pl.setY(pl.getY()+1);
          if (pl.getY() > sizeY)
          pl.setY(0);
          graphical.writeToGraphical("pex " + pl.getId());
          areOthers = true;
          break;
          case NORTH:
          pl.setY(pl.getY()-1);
          if (pl.getY() < 0)
          pl.setY(sizeY);
          graphical.writeToGraphical("pex " + pl.getId());
          areOthers = true;
          break;
          case WEST:
          pl.setX(pl.getX()-1);
          if (pl.getX() < 0)
          pl.setX(sizeX);
          graphical.writeToGraphical("pex " + pl.getId());
          areOthers = true;
          break;
          case EAST:
          pl.setX(pl.getX()+1);
          if (pl.getX() > sizeY)
          pl.setX(0);
          graphical.writeToGraphical("pex " + pl.getId());
          areOthers = true;
          break;
          default:
          areOthers = false;
          break;
        }
      }
      if (areOthers)
      {
        return "true";
      }
      else
      {
        return "false";
      }
    }
    return "false";
  }
}
