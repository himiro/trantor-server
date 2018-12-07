class Egg extends Character
{
  protected static int id = 0;

  Egg(int x, int y, String teamName, String status, long idSocket)
  {
    super(x, y, teamName, status, idSocket);
    this.id++;
    System.out.println("Egg number " + this.id + " from socket " + this.idSocket + " has been created");
  }

  public int getId()
  {
    return this.id;
  }
}
