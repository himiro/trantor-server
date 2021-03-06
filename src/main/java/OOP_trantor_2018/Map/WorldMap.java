import java.util.*;

class WorldMap
{
  protected int sizeX;
  protected int sizeY;
  protected List<Tile> tiles = new ArrayList<Tile>();

  WorldMap(int sizeX, int sizeY)
  {
    this.sizeX = sizeX;
    this.sizeY = sizeY;
  }

  public int getSizeX()
  {
    return this.sizeX;
  }

  public void setSizeX(int sizeX)
  {
    this.sizeX = sizeX;
  }

  public int getSizeY()
  {
    return this.sizeY;
  }

  public void setSizeY(int sizeX)
  {
    this.sizeX = sizeX;
  }

  public List<Tile> getTiles()
  {
    return this.tiles;
  }

  public void setTiles(List<Tile> tiles)
  {
    this.tiles = tiles;
  }

  public Tile getTileByCoordinates(int x, int y)
  {
    for (Tile tmp : this.tiles)
    {
      if (tmp.getX() == x && tmp.getY() == y)
      {
        return (tmp);
      }
    }
    return null;
  }
}
