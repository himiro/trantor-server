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

  //For debug
  Player()
  {
    super(10, 10, "debug", Status.ALIVE, 42);
  }

  Player(int x, int y, int idSocket, String teamName, Orientation orientation, Map<String, Ressource> inventory)
  {
    super(x, y, teamName, Status.ALIVE, idSocket);
    this.orientation = orientation;
    this.level = 0;
    this.vision = 1;
    this.inventory = inventory;
    this.id = this.nb++;
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

  public void feed()
  {
    System.out.println("Player feed");
  }

  public void forward(int sizeX, int sizeY)
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
  }

  public void left()
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
  }

  public void right()
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
  }

  public void look()
  {
    System.out.println("Player looked around");
  }

  public void inventory()
  {
    System.out.println("food " + this.inventory.get("food").getNb() + ", linemate " + this.inventory.get("linemate").getNb() + ", deraumere " + this.inventory.get("deraumere").getNb() + ", sibur " + this.inventory.get("sibur").getNb()
    + ", mendiane " + this.inventory.get("mendiane").getNb() + ", phiras " + this.inventory.get("phiras").getNb() + ", thystame " + this.inventory.get("thystame").getNb());
    System.exit(0);
  }

  public void broadcast()
  {
    System.out.println("Player make a sound");
  }

  public void take()
  {
    System.out.println("Player took ressource");
  }

  public void drop()
  {
    System.out.println("Player dropped ressource");
  }

  public void elevation()
  {
    System.out.println("Players are doing some magic stuff");
  }

  public void reproduct()
  {
    System.out.println("Player reproducted");
  }
}
