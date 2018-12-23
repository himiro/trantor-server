import java.util.*;

class Controller
{
    protected Timeline timeline;
    protected WorldMap worldMap;
    protected List<Team> teams = new ArrayList<Team>();
    protected Parser parser;

    Controller(Parser Pars)
    {
        this.parser = Pars;
        this.timeline = new Timeline(Pars.getFreq());
        this.teams = Pars.getTeams();
        this.initWorldMap(Pars.getX(), Pars.getY());
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

    public void initWorldMap(int x, int y)
    {
        List<Tile> LTile = new ArrayList<Tile>();
        this.worldMap = new WorldMap(x, y);
        int nb;

        System.out.println("World Map initialisation");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int firnb = (int) (Math.random() * 4);
                Map<String, Ressource> base_ressource = new HashMap<String, Ressource>();
                    Linemate linemate = new Linemate((int)(Math.random() * 7 ));
                    Deraumere deraumere = new Deraumere((int)(Math.random() * 7 ));
                    Sibur sibur = new Sibur((int)(Math.random() * 7 ));
                    base_ressource.put("Linemate", linemate);
                    base_ressource.put("Deraumere", deraumere);
                    base_ressource.put("Sibur", sibur);
                    Mendiane mendiane = new Mendiane((int)(Math.random() * 7 ));
                    Phiras phiras = new Phiras((int)(Math.random() * 7 ));
                    Thystame thystame = new Thystame((int)(Math.random() * 7 ));
                    base_ressource.put("Mendiane", mendiane);
                    base_ressource.put("Phiras", phiras);
                    base_ressource.put("Thystame", thystame);
                    Food food = new Food((int)(Math.random()*6));
                    base_ressource.put("Food", food);
                    LTile.add(new Tile(i,j,base_ressource));
            }
        }
        this.worldMap.setTiles(LTile);
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
        if (command.getPlayer().getQueue().size() == 0 && timeline.isCommandFromPlayer(command) == false)
        {
            command = timeline.addCommand(command);
        }
        else
        {
            if (command.getPlayer().getQueue().size() < 10)
            {
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
                if (tmp.getEnd().getTime() < currentDate.getTime())
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
            this.lookTiles(cmd.getPlayer());
            break;
            case "Inventory":
            cmd.getPlayer().inventory();
            break;
            case "Fork":
            System.out.println("Fork");
            break;
            case "Eject":
            Tile ejectTile = this.worldMap.getTileByCoordinates(cmd.getPlayer().getX(), cmd.getPlayer().getY());
            if (ejectTile != null)
            {
                cmd.getPlayer().eject(ejectTile.getPlayers(), this.worldMap.getSizeX(), this.worldMap.getSizeY());
            }
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
                Tile takeTile = this.worldMap.getTileByCoordinates(cmd.getPlayer().getX(), cmd.getPlayer().getY());
                cmd.getPlayer().take(takeTile, cmd.getName().substring(cmd.getName().lastIndexOf(' ') + 1));
            }
            else if (cmd.getName().startsWith("Set ") == true)
            {
                Tile takeTile = this.worldMap.getTileByCoordinates(cmd.getPlayer().getX(), cmd.getPlayer().getY());
                cmd.getPlayer().set(takeTile, cmd.getName().substring(cmd.getName().lastIndexOf(' ') + 1));
            }
            break;
        }
    }

    public void lookTiles(Player player)
    {
        System.out.println("Player looked around");
        System.out.print("[");
        int x = player.getX();
        int y = player.getY();
        int length = 2;
        Tile tile = new Tile();

        tile = this.worldMap.getTileByCoordinates(player.getX(), player.getY());
        player.look(tile);
        while (y != (player.getY() + player.getVision()) % (this.worldMap.getSizeY()))
        {
            while (x != (player.getX() + length))
            {
                tile = this.worldMap.getTileByCoordinates(x % this.worldMap.getSizeX(), y % this.worldMap.getSizeY());
                player.look(tile);
                x++;
            }
            if (player.getX() - length < 0)
            {
                x = player.getX() + (this.worldMap.getSizeX() - length);
            }
            else
            {
                x = (player.getX() - length);
            }
            if (y > this.worldMap.getSizeY())
            {
                y = y % this.worldMap.getSizeY();
            }
            else
            {
                y++;
            }
            length++;
        }
        System.out.println("]");
    }

}
