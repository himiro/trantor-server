import java.util.*;

class Egg extends Character
{
  protected static int id = 0;
  protected Date end;

  Egg(int x, int y, String teamName, Status status, long idSocket, Date end)
  {
    super(x, y, teamName, status, idSocket);
    this.id++;
    this.end = end;
    System.out.println("Egg number " + this.id + " from socket " + this.idSocket + " has been created");
  }

  public int getId()
  {
    return this.id;
  }
}
