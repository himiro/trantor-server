import java.util.*;

class Controller
{
    protected Timeline timeline;
    protected WorldMap worldMap;
    protected List<Team> teams = new ArrayList<Team>();
    protected int maxPlayerPerTeam;

    //debug
    Controller(List<Team> teams)
    {
        this.timeline = new Timeline();
        this.initWorldMap();
        this.teams = teams;
    }

    Controller()
    {
        this.timeline = new Timeline();
        this.initWorldMap();
    }


    /**
    * Returns value of timeline
    * @return
    */
    public Timeline getTimeline()
    {
        return this.timeline;
    }

    /**
    * Sets new value of timeline
    * @param
    */
    public void setTimeline(Timeline timeline)
    {
        this.timeline = timeline;
    }

    /**
    * Returns value of map
    * @return
    */
    public WorldMap getWorldMap()
    {
        return this.worldMap;
    }

    /**
    * Sets new value of map
    * @param
    */
    public void setWorldMap(WorldMap worldMap)
    {
        this.worldMap = worldMap;
    }

    /**
    * Returns value of teams
    * @return
    */
    public List<Team> getTeams()
    {
        return this.teams;
    }

    /**
    * Sets new value of teams
    * @param
    */
    public void setTeams(List<Team> teams)
    {
        this.teams = teams;
    }

    /**
    * Returns value of maxPlayerPerTeam
    * @return
    */
    public int getMaxPlayerPerTeam()
    {
        return this.maxPlayerPerTeam;
    }

    /**
    * Sets new value of maxPlayerPerTeam
    * @param
    */
    public void setMaxPlayerPerTeam(int maxPlayerPerTeam)
    {
        this.maxPlayerPerTeam = maxPlayerPerTeam;
    }

    public void initWorldMap()
    {
        //Creation et initialisation de l'objet Map
        // /!\ Ne pas oublier d'initialiser chaque Tile de la Map

        //debug
        this.worldMap = new WorldMap(10, 10);
        System.out.println("World Map initialisation");
    }

    public Command createCommand(String command, int socketId)
    {
        //create command object to add it to the timeline
        Player tmp = this.findPlayerBySocketId(socketId);
        Command newCmd = new Command(command, tmp);
        this.addCommand(newCmd);
        return newCmd;
    }

    public void addCommand(Command command)
    {
        //If player's queue !empty : add command to the player.
        //Else : add command to the timeline
        //System.out.println("ICIIIIIIIIIIII : " + command.getPlayer().getQueue().empty());
        if (command.getPlayer().getQueue().size() == 0 && timeline.isCommandFromPlayer(command) == false)
        {
            System.out.println("First command");
            command = timeline.addCommand(command);
        }
        else
        {
            if (command.getPlayer().getQueue().size() < 10)
            {
                //System.out.println("Plus command");
                Date endDate = new Date(System.currentTimeMillis());
                if (command.getName().startsWith("Broadcast "))
                {
                    endDate = new Date(System.currentTimeMillis() + (this.timeline.getCommandTime().get(command.getName().substring(0, 9)) * 1000));
                }
                else if (command.getName().startsWith("Take "))
                {
                    endDate = new Date(System.currentTimeMillis() + (this.timeline.getCommandTime().get(command.getName().substring(0, 4)) * 1000));
                }
                else if (command.getName().startsWith("Set "))
                {
                    endDate = new Date(System.currentTimeMillis() + (this.timeline.getCommandTime().get(command.getName().substring(0, 3)) * 1000));
                }
                else
                {
                    endDate = new Date(System.currentTimeMillis() + (this.timeline.getCommandTime().get(command.getName()) * 1000));
                }

                command.setEnd(endDate);
                command.getPlayer().getQueue().add(command);
            }
        }
    }

    public void pushCommandToTimeline(Command command)
    {
        System.out.println("\nPush command to timeline : " + command.getName());
        command = timeline.addCommand(command);
    }

    public void removeCommand(Command command)
    {
        //if action finished
        //remove the command from the timeline and check if another command is waiting in the player's queue
        // if yes -> addCommand from player
        //execute
        this.timeline.getCommands().remove(command);
        System.out.println("Execute command : " + command.getName());
        this.execute(command);
        if (command.getPlayer().getQueue().size() != 0)
        {
            this.pushCommandToTimeline(command.getPlayer().getQueue().peek());

            /*System.out.println("\n\n\n");
            System.out.println("TIMELINE : ");
            this.getTimeline().getCommands().forEach(k->{
                System.out.println(""+k.getName());
            });
            System.out.println("");
            System.out.println("Player queue : ");
            command.getPlayer().getQueue().forEach(k->{
                System.out.println(""+k.getName());
            });
            System.out.println("\n\n\n");*/
        }
        else
        {
            System.out.println("Any command left");
    }
}

    public void newTeam(String teamName)
    {
        //create new team
        System.out.println("New team");
    }

    public void removeTeam()
    {
        //Fonction à appeler seulement après l'enregistrement des données SQL
        System.out.println("Team removed");
    }

    public void saveResult()
    {
        System.out.println("Save result in SQL");
    }

    protected Player findPlayerBySocketId(int socketId)
    {
        for (Team team : this.teams)
        {
            for (Player player : team.getPlayers())
            {
                if (player.getIdSocket() == socketId)
                {
                    return (player);
                }
            }
        }
        return null;
    }

    public boolean isActionFinished(Command command)
    {
        //go through timeline if currentdate > endDate call remove command
        Date currentDate = new Date(System.currentTimeMillis());

        for (Command tmp : this.timeline.getCommands())
        {
            if (tmp.equals(command))
            {
                if (tmp.getEnd().getTime() > currentDate.getTime())
                {
                    this.removeCommand(tmp);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public void execute(Command cmd)
    {
        //call command
        switch(cmd.getName())
        {
            case "Forward":
            cmd.getPlayer().forward(this.worldMap.getSizeX(), this.worldMap.getSizeY());
            break;
            case "Right":
            cmd.getPlayer().right();
            break;
            case "Left":
            cmd.getPlayer().left();
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
    }

}
