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
}
