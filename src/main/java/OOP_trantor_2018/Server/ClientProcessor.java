import java.util.*;
import java.util.regex.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class ClientProcessor implements Runnable {
    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private Parser Parser;
    private boolean closeConnexion = false;
    private Controller control;
    protected int nbSocket;
    protected boolean isGraphical = false;
    protected Graphical graphical;

    public ClientProcessor(Socket pSock, Parser Pars, Controller pControl, int nbSocket, Graphical graphical)
    {
        this.sock = pSock;
        this.Parser = Pars;
        this.control = pControl;
        this.nbSocket = nbSocket;
        this.graphical = graphical;
    }

    /**
	* Returns value of sock
	* @return
	*/
	public Socket getSock()
	{
		return this.sock;
	}

	/**
	* Sets new value of sock
	* @param
	*/
	public void setSock(Socket sock)
	{
		this.sock = sock;
	}

	/**
	* Returns value of writer
	* @return
	*/
	public PrintWriter getWriter()
	{
		return this.writer;
	}

	/**
	* Sets new value of writer
	* @param
	*/
	public void setWriter(PrintWriter writer)
	{
		this.writer = writer;
	}

	/**
	* Returns value of reader
	* @return
	*/
	public BufferedInputStream getReader()
	{
		return this.reader;
	}

	/**
	* Sets new value of reader
	* @param
	*/
	public void setReader(BufferedInputStream reader)
	{
		this.reader = reader;
	}

	/**
	* Returns value of Parser
	* @return
	*/
	public Parser getParser()
	{
		return this.Parser;
	}

	/**
	* Sets new value of Parser
	* @param
	*/
	public void setParser(Parser Parser)
	{
		this.Parser = Parser;
	}

	/**
	* Returns value of closeConnexion
	* @return
	*/
	public boolean getCloseConnexion()
	{
		return this.closeConnexion;
	}

	/**
	* Sets new value of closeConnexion
	* @param
	*/
	public void setCloseConnexion(boolean closeConnexion)
	{
		this.closeConnexion = closeConnexion;
	}

	/**
	* Returns value of control
	* @return
	*/
	public Controller getControl()
	{
		return this.control;
	}

	/**
	* Sets new value of control
	* @param
	*/
	public void setControl(Controller control)
	{
		this.control = control;
	}

	/**
	* Returns value of nbSocket
	* @return
	*/
	public int getNbSocket()
	{
		return this.nbSocket;
	}

	/**
	* Sets new value of nbSocket
	* @param
	*/
	public void setNbSocket(int nbSocket)
	{
		this.nbSocket = nbSocket;
	}

	/**
	* Returns value of isGraphical
	* @return
	*/
	public boolean getIsGraphical()
	{
		return this.isGraphical;
	}

	/**
	* Sets new value of isGraphical
	* @param
	*/
	public void setIsGraphical(boolean isGraphical)
	{
		this.isGraphical = isGraphical;
	}

	/**
	* Returns value of graphical
	* @return
	*/
	public Graphical getGraphical()
	{
		return this.graphical;
	}

	/**
	* Sets new value of graphical
	* @param
	*/
	public void setGraphical(Graphical graphical)
	{
		this.graphical = graphical;
	}

    public void run()
    {
        String response;
        String toSend;

        try {
            this.writer = new PrintWriter(sock.getOutputStream());
            this.reader = new BufferedInputStream(sock.getInputStream());
            this.graphical.setWriter(this.writer);
            init_connection();
            while(!sock.isClosed()) {
                if (this.reader.available() > 0)
                {
                    response = read();
                    System.out.println(Thread.currentThread().getName() + " : " + response);
                    response = response.toLowerCase();
                    if (this.isGraphical == true)
                    {
                        pars_command_graphical(response);
                    }
                    else
                    {
                        if (response != null)
                        {
                            pars_command_player(response.substring(0, 1).toUpperCase() + response.substring(1));
                        }
                    }
                }
                if(closeConnexion){
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    this.writer = null;
                    this.reader = null;
                    sock.close();
                }
            }
        }
        catch(SocketException e)
        {
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            return ;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return ;
        }
    }

    private String read() throws IOException
    {
        String response = "";
        int stream;
        byte[] b = new byte[1024];
        stream = this.reader.read(b);
        if (stream == -1)
        this.closeConnexion = true;
        else
        response = new String(b, 0, stream);
        return response;
    }

    private void init_connection()
    {
        InetSocketAddress remote;
        List<Team> tmp = new ArrayList<Team>();
        Team temp;
        String toSend;
        String response;
        String debug;
        int nbClient = 0;

        try {
            toSend = "WELCOME";
            this.writer.println(toSend);
            this.writer.flush();
            response = read();
            response = response.substring(0, response.length() - 1);
            if (response.equals("GRAPHICAL"))
            {
                this.isGraphical = true;
                this.graphical.getGraphicals().add(this.writer);
            }
            else
            {
                tmp = Parser.getTeams();
                for (int i = 0; i < tmp.size(); i++) {
                    temp = tmp.get(i);
                    if (response.equals(temp.getTeamName())) {
                        if ((nbClient = temp.getNbClients()) != 0) {
                            Map<String, Ressource> inventory = this.createInventory();
                            if (this.createPlayer(response, inventory) == true)
                            {
                                nbClient-=1;
                                temp.setNbClients(nbClient);
                            }
                        }
                        else
                        {
                            this.writer = null;
                            this.reader = null;
                            List<Team> teams = Parser.getTeams();
                            for (Team team : teams) {
                                if (response.equals(team.getTeamName()))
                                {
                                    nbClient = team.getNbClients();
                                    team.setNbClients(++nbClient);
                                }
                            }
                            sock.close();
                        }
                    }
                }
            }
            toSend = Integer.toString(nbClient) + "\n" + Integer.toString(Parser.getX()) + " " + Integer.toString(Parser.getX());
            this.writer.println(toSend);
            this.writer.flush();
            remote = (InetSocketAddress)sock.getRemoteSocketAddress();
            debug = Thread.currentThread().getName() + " : Succesfully connected!\n";
            System.err.println(debug);
        }
        catch(SocketException e)
        {
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            return ;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return ;
        }
    }


    public Map<String, Ressource> createInventory()
    {
        Linemate linemate = new Linemate();
        Deraumere deraumere = new Deraumere();
        Sibur sibur = new Sibur();
        Mendiane mendiane = new Mendiane();
        Phiras phiras = new Phiras();
        Thystame thystame = new Thystame();
        Food food = new Food();

        Map<String, Ressource> base_ressource = new HashMap<String, Ressource>();
        base_ressource.put("Linemate", linemate);
        base_ressource.put("Deraumere", deraumere);
        base_ressource.put("Sibur", sibur);
        base_ressource.put("Mendiane", mendiane);
        base_ressource.put("Phiras", phiras);
        base_ressource.put("Thystame", thystame);
        base_ressource.put("Food", food);

        return base_ressource;
    }

    public boolean createPlayer(String teamName, Map<String, Ressource> inventory)
    {
        List<Team> teams = this.Parser.getTeams();

        for (Team team : teams)
        {
            if (team.getTeamName().equals(teamName) && team.getNbClients() > 0)
            {
                Random r = new Random();
                Player player = new Player(r.nextInt(this.Parser.getX()), r.nextInt(this.Parser.getY() + 1), this.nbSocket, teamName, Orientation.getRandomOrientation(), inventory, this.control.getTimeline().getFrequence(), this.writer);
                team.getPlayers().add(player);
                control.setTeams(teams);
                this.graphical.writeToGraphical("pnw #" + player.getId() + " " + player.getX() + " " + player.getY() + " " + player.getOrientation().getName() + " " + player.getLevel() + " " + team.getTeamName());
                return true;
            }
        }
        return false;
    }

    private void pars_command_graphical(String cmd)
    {
        String regex;
        Pattern pattern;
        Matcher matcher;
        boolean valid = false;
        cmd = cmd.replaceAll("\\s{2,}", " ").trim();
        cmd += '\n';
        switch (cmd)
        {
            case "msz\n":
            this.writer.println("msz " + control.getWorldMap().getSizeX() + " " + control.getWorldMap().getSizeY());
            this.writer.flush();
            valid = true;
            break;
            case "mct\n":
            this.graphical.contentOfTile(0, 0, true);
            valid = true;
            break;
            case "tna\n":
            this.graphical.nameOfTeams();
            valid = true;
            break;
            case "sgt\n":
            this.writer.println("sgt " + control.getTimeline().getFrequence());
            this.writer.flush();
            valid = true;
            break;
            default:
            break;
        }
        if (valid == false && cmd.length() > 4)
        {
            switch (cmd.substring(0, 4))
            {
                case "bct ":
                regex = "\\bbct\\b\\s(\\d+)\\s(\\d+)\n";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(cmd);
                if (matcher.matches())
                {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    if (this.graphical.contentOfTile(x, y, false))
                    {
                        this.writer.println("ko");
                        this.writer.flush();
                    }
                    valid = true;
                }
                break;
                case "ppo ":
                regex = "\\bppo\\b\\s#(\\d+)\n";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(cmd);
                if (matcher.matches())
                {
                    int id = Integer.parseInt(matcher.group(1));
                    Player player = this.control.findPlayerBySocketId(id);
                    if (player != null)
                    {
                        this.writer.println("ppo " + id + " " + player.getX() + " " + player.getY() + " " + player.getOrientation().getName());
                    }
                    else
                    {
                        this.writer.println("ko");
                    }
                    this.writer.flush();
                    valid = true;
                }
                break;
                case "plv ":
                regex = "\\bplv\\b\\s#(\\d+)\n";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(cmd);
                if (matcher.matches())
                {
                    int id = Integer.parseInt(matcher.group(1));
                    Player player = this.control.findPlayerBySocketId(id);
                    if (player != null)
                    {
                        this.writer.println("plv " + id + " " + player.getLevel());
                    }
                    else
                    {
                        this.writer.println("ko");
                    }
                    this.writer.flush();
                    valid = true;
                }
                break;
                case "pin ":
                regex = "\\bpin\\b\\s#(\\d+)\n";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(cmd);
                if (matcher.matches())
                {
                    int id = Integer.parseInt(matcher.group(1));
                    Player player = this.control.findPlayerBySocketId(id);
                    if (player != null)
                    {
                        Map<String, Ressource> inventory = player.getInventory();
                        this.writer.println("pin " + id + " " + player.getX() + " " + player.getY() + " " + inventory.get("Food").getNb() + " "
                        + inventory.get("Linemate").getNb() + " " + inventory.get("Deraumere").getNb() + " " + inventory.get("Sibur").getNb() + " "
                        + inventory.get("Mendiane").getNb() + " " + inventory.get("Phiras").getNb() + " " + inventory.get("Thystame").getNb());
                    }
                    else
                    {
                        this.writer.println("ko");
                    }
                    this.writer.flush();
                    valid = true;
                }
                break;
                case "sst ":
                regex = "\\bsst\\b\\s(\\d+)\n";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(cmd);
                if (matcher.matches())
                {
                    int timeUnit = Integer.parseInt(matcher.group(1));
                    control.getTimeline().setFrequence(timeUnit);
                    this.writer.println("sst " + control.getTimeline().getFrequence());
                    this.writer.flush();
                    valid = true;
                }
                break;
                default:
                break;
            }
        }
        if (valid == false)
        {
            graphical.writeToGraphical("suc");
            this.writer.println("invalid command");
            this.writer.flush();
        }
    }

    private void pars_command_player(String cmd)
    {
        String toSend = "ko";
        boolean isValidCommand = false;
        Timeline time;

        switch(cmd)
        {
            case "Forward\n":
            case "Right\n":
            case "Left\n":
            case "Look\n":
            case "Inventory\n":
            case "Connect_nbr\n":
            case "Fork\n":
            case "Eject\n":
            case "Incantation\n":
            isValidCommand = true;
            break;
            default:
            if (cmd.startsWith("Take ") || cmd.startsWith("Set ") || cmd.startsWith("Broadcast "))
            {
                isValidCommand = true;
            }
            break;
        }
        if (isValidCommand)
        {
            cmd = cmd.substring(0, cmd.length() - 1);
            if (control != null || cmd != null)
            this.control.createCommand(cmd, this.nbSocket);
        }
        else
        {
            this.writer.println("invalid command");
            this.writer.flush();
        }
    }
}
