import java.util.Random;

public enum Orientation
{
  NORTH("North"),
  SOUTH("South"),
  EAST("East"),
  WEST("West");

  private String name;

  Orientation(String name)
  {
      this.name = name;
  }

  public String getName()
  {
      return this.name;
  }

  public static Orientation getRandomOrientation() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}
