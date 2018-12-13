import java.util.*;

class Player extends Character
{
  protected Orientation orientation;
  protected int level;
  protected int vision;
  protected List<Ressource> inventory = new ArrayList<Ressource>();
  protected static int nb = 1;
  protected int id;
  protected Stack<Command> stack = new Stack<Command>();

  //For debug
  Player()
  {
    super(10, 10, "debug", Status.ALIVE, 42);
  }

  Player(int x, int y, int idSocket, String teamName, Orientation orientation, List<Ressource> inventory)
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
  public List<Ressource> getInventory()
  {
    return this.inventory;
  }

  /**
  * Sets new value of inventory
  * @param
  */
  public void setInventory(List<Ressource> inventory)
  {
    this.inventory = inventory;
  }

  /**
  * Returns value of stack
  * @return
  */
  public Stack<Command> getStack()
  {
    return this.stack;
  }

  /**
  * Sets new value of stack
  * @param
  */
  public void setStack(Stack<Command> stack)
  {
    this.stack = stack;
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
    System.exit(0);
  }

  public void left()
  {
    System.out.println("Player goes left");
  }

  public void right()
  {
    System.out.println("Player goes right");
  }

  public void look()
  {
    System.out.println("Player looked around");
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
