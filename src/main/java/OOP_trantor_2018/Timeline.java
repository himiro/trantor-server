import java.util.*;

class Timeline
{
  protected Map<Command, Date> commands = new HashMap<Command, Date>();

  Timeline()
  {

  }

  public Map<Command, Date> getCommands()
  {
    return this.commands;
  }

  public void setCommands(Map<Command, Date> commands)
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
