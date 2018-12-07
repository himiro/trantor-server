abstract class Ressource
{
  protected String name;
  protected int nb;

  Ressource(String name, int nb)
  {
    this.name = name;
    this.nb = nb;
  }

  /**
  * Returns value of name
  * @return
  */
  public String getName()
  {
    return this.name;
  }

  /**
  * Sets new value of name
  * @param
  */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
  * Returns value of nb
  * @return
  */
  public int getNb()
  {
    return this.nb;
  }

  /**
  * Sets new value of nb
  * @param
  */
  public void setNb(int nb)
  {
    this.nb = nb;
  }

  public void addRessource()
  {
    this.nb++;
  }

  public void removeRessource()
  {
    if (this.nb > 0)
      this.nb--;
  }
}
