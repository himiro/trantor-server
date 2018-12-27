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
        if (command != null || timeline != null) {
            if (command.getPlayer().getQueue().size() == 0 && timeline.isCommandFromPlayer(command) == false) {
                command = timeline.addCommand(command);
            }
            else {
                if (command.getPlayer().getQueue().size() < 10)
                {
                    command.getPlayer().getQueue().add(command);
                }
            }
        }
    }

    public void pushCommandToTimeline(Command command)
    {
        System.out.println("\nPush command to timeline : " + command.getName());
        command = timeline.addCommand(command);
    }

    public String removeCommand(Command command, Graphical graphical)
    {
        //if action finished
        //remove the command from the timeline and check if another command is waiting in the player's queue
        // if yes -> addCommand from player
        //execute
        this.timeline.getCommands().remove(command);
        System.out.println("Execute command : " + command.getName());
        String ret = this.execute(command, graphical);
        if (command.getPlayer().getQueue().size() != 0)
        {
            this.pushCommandToTimeline(command.getPlayer().getQueue().peek());
        }
        else
        {
            System.out.println("Any command left");
        }
        return ret;
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

    public String isActionFinished(Command command, Graphical graphical)
    {
        //go through timeline if currentdate > endDate call remove command
        Date currentDate = new Date(System.currentTimeMillis());

        for (Command tmp : this.timeline.getCommands())
        {
            if (tmp.equals(command))
            {
                if (tmp.getEnd().getTime() < currentDate.getTime())
                {
                    //move remove command to get the removeCommand return value and call removeCommand after called isActionFinished
                    String ret = this.removeCommand(tmp, graphical);
                    return (ret);
                }
                return "nope";
            }
        }
        return null;
    }


    public String execute(Command cmd, Graphical graphical)
    {
        switch(cmd.getName())
        {
            case "Forward":
            return (cmd.getPlayer().forward(this.worldMap.getSizeX(), this.worldMap.getSizeY()));
            case "Right":
            return (cmd.getPlayer().right());
            case "Left":
            return (cmd.getPlayer().left());
            case "Look":
            return (this.lookTiles(cmd.getPlayer()));
            case "Inventory":
            return (cmd.getPlayer().inventory());
            case "Fork":
            System.out.println("Fork");
            break;
            case "Eject":
            Tile ejectTile = this.worldMap.getTileByCoordinates(cmd.getPlayer().getX(), cmd.getPlayer().getY());
            if (ejectTile != null)
            {
                return (cmd.getPlayer().eject(ejectTile.getPlayers(), this.worldMap.getSizeX(), this.worldMap.getSizeY(), graphical));
            }
            return "false";
            case "Incantation":
            System.out.println("Incantation");
            break;
            case "Connect_nbr":
            for (int i = 0; i < this.teams.size(); i++)
            {
                if (this.teams.get(i).getTeamName().equals(cmd.getPlayer().getTeamName()))
                {
                    return (Integer.toString(this.teams.get(i).getNbClients()));
                }
                return "false";
            }
            break;
            default:
            if (cmd.getName().startsWith("Broadcast ") == true)
            {
                System.out.println("Broadcast");
            }
            else if (cmd.getName().startsWith("Take ") == true)
            {
                Tile takeTile = this.worldMap.getTileByCoordinates(cmd.getPlayer().getX(), cmd.getPlayer().getY());
                if (takeTile != null)
                {
                    String object = cmd.getName().substring(cmd.getName().lastIndexOf(' ') + 1);
                    object = object.toLowerCase();
                    object = object.substring(0,1).toUpperCase() + object.substring(1);
                    Item item = Item.get(object);
                    return (cmd.getPlayer().take(takeTile, item, graphical));
                }
                return "false";
            }
            else if (cmd.getName().startsWith("Set ") == true)
            {
                Tile setTile = this.worldMap.getTileByCoordinates(cmd.getPlayer().getX(), cmd.getPlayer().getY());
                if (setTile != null)
                {
                    String object = cmd.getName().substring(cmd.getName().lastIndexOf(' ') + 1);
                    object = object.toLowerCase();
                    object = object.substring(0,1).toUpperCase() + object.substring(1);
                    Item item = Item.get(object);
                    return (cmd.getPlayer().set(setTile, item, graphical));
                }
                return "false";
            }
            return "false";
        }
        return "false";
    }

    public String lookTiles(Player player)
    {
        int x = player.getX();
        int y = player.getY();
        int length = 2;
        Tile tile = new Tile();
        String res;

        tile = this.worldMap.getTileByCoordinates(player.getX(), player.getY());
        System.out.println("Player looked around");
        res = "[";
        res = res + player.look(tile);
        while (y != (player.getY() + player.getVision()) % (this.worldMap.getSizeY()))
        {
            while (x != (player.getX() + length))
            {
                tile = this.worldMap.getTileByCoordinates(x % this.worldMap.getSizeX(), y % this.worldMap.getSizeY());
                res = res + player.look(tile);
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
        res = res + "]";
        return res;
    }

}
