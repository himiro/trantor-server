import java.util.Random;

public enum Orientation
{
  NORTH,
  SOUTH,
  EAST,
  WEST;

  public static Orientation getRandomOrientation() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }

  public static String toString(Orientation Orientation) {
    if (Orientation == NORTH)
    {
      return ("North");
    }
    else if (Orientation == SOUTH)
    {
      return ("South");
    }
    else if (Orientation == EAST)
    {
      return ("East");
    }
    else
    {
      return ("West");
    }
  }
}
