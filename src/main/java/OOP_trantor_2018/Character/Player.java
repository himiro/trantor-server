import java.util.*;

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

  Player(int x, int y, int idSocket, String teamName, Orientation orientation, Map<String, Ressource> inventory, int frequence)
  {
    super(x, y, teamName, Status.ALIVE, idSocket);
    this.orientation = orientation;
    this.level = 0;
    this.vision = 1;
    this.inventory = inventory;
    this.id = this.nb;
    this.isFeed = new Date(System.currentTimeMillis() + (126 * 10000) / frequence);
    System.out.println("Player number " + this.id + " from socket " + this.idSocket + " has been created");
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
      System.out.println("Player turn WEST");
      break;
      case EAST:
      this.orientation = orientation.NORTH;
      System.out.println("Player turn North");
      break;
      case WEST:
      this.orientation = orientation.SOUTH;
      System.out.println("Player turn South");
      break;
      case SOUTH:
      this.orientation = orientation.EAST;
      System.out.println("Player turn East");
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
      System.out.println("Player turn East");
      break;
      case EAST:
      this.orientation = orientation.SOUTH;
      System.out.println("Player turn South");
      break;
      case WEST:
      this.orientation = orientation.NORTH;
      System.out.println("Player turn North");
      break;
      case SOUTH:
      this.orientation = orientation.WEST;
      System.out.println("Player turn West");
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

  public String take(Tile tile, String object, Graphical graphical)
  {
    int nbPlayer = this.getInventory().get(object).getNb();
    int nbTile = tile.getRessources().get(object).getNb();
    if (nbTile > 0)
    {
      tile.getRessources().get(object).setNb(--nbTile);
      this.getInventory().get(object).setNb(++nbPlayer);
      System.out.println("Player took " + tile.getRessources().get(object).getName() + ".There is " + tile.getRessources().get(object).getNb() + " last on the tile");
      System.out.println("Player took " + tile.getRessources().get(object).getName() + ".He has " + this.getInventory().get(object).getNb() + " in his inventory");
      graphical.writeToGraphical("pgt " + this.id + "1");
      return "true";
    }
    return "false";
  }

  public String set(Tile tile, String object, Graphical graphical)
  {
    System.out.println("SET OBJECT : " + object);
    int nbPlayer = this.getInventory().get(object).getNb();
    int nbTile = tile.getRessources().get(object).getNb();
    if (nbPlayer > 0)
    {
      tile.getRessources().get(object).setNb(++nbTile);
      this.getInventory().get(object).setNb(--nbPlayer);
      System.out.println("Player drop " + tile.getRessources().get(object).getName() + ".There is " + tile.getRessources().get(object).getNb() + " last on the tile");
      System.out.println("Player drop " + tile.getRessources().get(object).getName() + ".He has " + this.getInventory().get(object).getNb() + " in his inventory");
      graphical.writeToGraphical("pdr " + this.id + "1");
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
          break;
          case NORTH:
          pl.setY(pl.getY()-1);
          if (pl.getY() < 0)
          pl.setY(sizeY);
          graphical.writeToGraphical("pex " + pl.getId());
          break;
          case WEST:
          pl.setX(pl.getX()-1);
          if (pl.getX() < 0)
          pl.setX(sizeX);
          graphical.writeToGraphical("pex " + pl.getId());
          break;
          case EAST:
          pl.setX(pl.getX()+1);
          if (pl.getX() > sizeY)
          pl.setX(0);
          graphical.writeToGraphical("pex " + pl.getId());
          break;
          default:
          break;
        }
      }
      System.out.println("Player eject");
      return "true";
    }
    return "false";
  }
}
