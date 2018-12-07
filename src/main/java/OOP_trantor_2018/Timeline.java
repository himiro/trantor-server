import java.util.*;

class Timeline
{
  protected Map<String, Date> commands = new HashMap<String, Date>();

  Timeline()
  {

  }

  public Map<String, Date> getCommands()
  {
    return this.commands;
  }

  public void setCommands(Map<String, Date> commands)
  {
    this.commands = commands;
  }

  public void execute()
  {
    System.out.println("Execute");
  }

  public void addCommand()
  {
    System.out.println("Add command");
  }

  public void removeCommand()
  {
    System.out.println("Remove command");
  }
}
