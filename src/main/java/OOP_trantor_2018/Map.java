import java.util.*;

class Map
{
  protected int sizeX;
  protected int sizeY;
  protected List<Tile> tiles = new ArrayList();

  Map()
  {

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

  public void setSizeX(int sizeX)
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
}
