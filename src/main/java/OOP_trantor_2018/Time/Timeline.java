import java.util.*;
import java.text.*;

class Timeline
{
    protected List<Command> commands = new ArrayList<Command>();
    protected static final Map<String, Integer> COMMAND_TIME = new HashMap<String, Integer>();
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss::SS");
    protected int frequence;

    Timeline(int frequence)
    {
        this.frequence = frequence;
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

    public void setFrequence(int frequence)
    {
        this.frequence = frequence;
    }

    public int getFrequence()
    {
        return (this.frequence);
    }

    public Command addCommand(Command command)
    {
        //add command in the timeline + define endTime
        // + if player's queue !empty -> remove it from player's queue
        Date endDate = new Date(System.currentTimeMillis());
        System.out.println("Avant : " + endDate.getTime());
        if (command.getName().startsWith("Broadcast "))
        {
            endDate = new Date(System.currentTimeMillis() + ((COMMAND_TIME.get(command.getName().substring(0, 9)) * 10000) / this.getFrequence()));
        }
        else if (command.getName().startsWith("Take "))
        {
            endDate = new Date(System.currentTimeMillis() + ((COMMAND_TIME.get(command.getName().substring(0,4)) * 10000) / this.getFrequence()));
        }
        else if (command.getName().startsWith("Set "))
        {
            endDate = new Date(System.currentTimeMillis() + ((COMMAND_TIME.get(command.getName().substring(0,3)) * 10000) / this.getFrequence()));
        }
        else
        {
            endDate = new Date(System.currentTimeMillis() + ((COMMAND_TIME.get(command.getName()) * 10000) / this.getFrequence()));
        }
        System.out.println("Après : " + endDate.getTime());
        command.setEnd(endDate);

        for (int i = 0; i < this.commands.size(); i++)
        {
            if (command.getEnd().getTime() < this.commands.get(i).getEnd().getTime())
            {
                this.commands.add(i, command);
                if (command.getPlayer().getQueue().size() > 0)
                {
                    command.getPlayer().getQueue().remove();
                }
                return command;
            }
        }
        this.commands.add(command);
        if (command.getPlayer().getQueue().size() > 0)
        {
            command.getPlayer().getQueue().remove();
        }
        return command;
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
