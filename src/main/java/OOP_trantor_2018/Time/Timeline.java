import java.util.*;
import java.text.*;

class Timeline extends TimeManagement
{
  protected List<Command> commands = new ArrayList<Command>();
  protected static final Map<String, Integer> COMMAND_TIME = new HashMap<String, Integer>();
  private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss::SS");

  Timeline()
  {
    initCommandTime();
  }

  public List<Command> getCommands()
  {
    return this.commands;
  }

  public void setCommands(List<Command> commands)
  {
    this.commands = commands;
  }

  public Map<String, Integer> getCommandTime()
  {
    return this.COMMAND_TIME;
  }

  public void execute(Command cmd)
  {
    //call command
    switch(cmd.getName())
    {
      case "Forward":
      System.out.println("Forward");
      break;
      case "Right":
      System.out.println("Right");
      break;
      case "Left":
      System.out.println("Left");
      break;
      case "Look":
      System.out.println("Look");
      break;
      case "Inventory":
      System.out.println("Inventory");
      break;
      case "Fork":
      System.out.println("Fork");
      break;
      case "Eject":
      System.out.println("Eject");
      break;
      case "Incantation":
      System.out.println("Incantation");
      break;
      default:
      if (cmd.getName().startsWith("Broadcast ") == true)
      {
        System.out.println("Broadcast");
      }
      else if (cmd.getName().startsWith("Take ") == true)
      {
        System.out.println("Take");
      }
      else if (cmd.getName().startsWith("Set ") == true)
      {
        System.out.println("Set");
      }
      break;
    }
    System.out.println("Execute");
  }

  public void addCommand(Command command)
  {
    //add command in the timeline + define endTime
    // + if player's stack !empty -> remove it from player's stack
    Date endDate = new Date(System.currentTimeMillis() + (COMMAND_TIME.get(command.getName()) * 1000));

    command.setEnd(endDate);
    this.commands.add(command);
    if (command.getPlayer().getStack().size() > 0)
    {
      command.getPlayer().getStack().pop();
    }
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

  public boolean isCommandFromPlayer(Command command)
  {
    for (Command cmd : this.commands)
    {
      if (cmd.getPlayer().getId() == command.getPlayer().getId())
      {
        return true;
      }
    }
    return false;
  }
}
