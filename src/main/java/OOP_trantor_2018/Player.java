import java.util.*;

class Player extends Character
{
  //Enum orientation;
  protected String orientation;
  protected int level;
  protected int vision;
  protected List<Ressource> inventory = new ArrayList();
  //stack
  protected Stack stack = new Stack();

  Player(int x, int y, long id, String teamName, String orientation /*ENUM*/, List<Ressource> inventory)
  {
    super(x, y, id, teamName, "Alive");
    this.orientation = orientation;
    this.level = 0;
    this.vision = 1;
    this.inventory = inventory;
  }

  /**
  * Returns value of orientation
  * @return
  */
  public String getOrientation()
  {
    return this.orientation;
  }

  /**
  * Sets new value of orientation
  * @param
  */
  public void setOrientation(String orientation)
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
  public Stack getStack()
  {
    return this.stack;
  }

  /**
  * Sets new value of stack
  * @param
  */
  public void setStack(Stack stack)
  {
    this.stack = stack;
  }

  public void feed()
  {
    System.out.println("Player feed");
  }

  public void move()
  {
    System.out.println("Player moved");
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
