import java.util.*;

class Timeline extends TimeManagement
{
  protected Map<Command, Date> commands = new HashMap<Command, Date>();
  protected final Map<String, Integer> COMMAND_TIME = new HashMap<String, Integer>();

  Timeline()
  {
    initCommandTime();
  }

  public Map<Command, Date> getCommands()
  {
    return this.commands;
  }

  public void setCommands(Map<Command, Date> commands)
  {
    this.commands = commands;
  }

  public Map<String, Integer> getCommandTime()
  {
    return this.COMMAND_TIME;
  }

  public void execute()
  {
    //call command + removeCommand from Timeline + add the next player's command to the timeline
    System.out.println("Execute");
  }

  public void addCommand(Command command)
  {
    //add command in the timeline + define endTime
    // + if stack !empty -> remove it from player's stack
    System.out.println("Add command");
  }

  public void removeCommand(Command command)
  {
    //if action finished
    //remove the command from the timeline and check if another command is waiting in the player's stack
    // if yes -> addCommand from player
    System.out.println("Remove command");
  }

  private void initCommandTime()
  {
    COMMAND_TIME.put("Forward", Integer.valueOf(7));
    COMMAND_TIME.put("Right", Integer.valueOf(7));
    COMMAND_TIME.put("Left", Integer.valueOf(7));
    COMMAND_TIME.put("Look", Integer.valueOf(7));
    COMMAND_TIME.put("Inventory", Integer.valueOf(1));
    COMMAND_TIME.put("Broadcast", Integer.valueOf(7));
    COMMAND_TIME.put("Connect_nbr", Integer.valueOf(0));
    COMMAND_TIME.put("Fork", Integer.valueOf(42));
    COMMAND_TIME.put("Eject", Integer.valueOf(7));
    COMMAND_TIME.put("Take", Integer.valueOf(7));
    COMMAND_TIME.put("Set", Integer.valueOf(7));
    COMMAND_TIME.put("Incantation", Integer.valueOf(300));
  }
}
