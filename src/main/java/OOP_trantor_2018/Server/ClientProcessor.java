import java.util.*;
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

    public ClientProcessor(Socket pSock, Parser Pars, Controller pControl, int nbSocket)
    {
        this.sock = pSock;
        this.Parser = Pars;
        this.control = pControl;
        this.nbSocket = nbSocket;
    }

    public int getNbSocket()
    {
        return this.nbSocket;
    }

    public void run()
    {
        String response;
        String toSend;

        try {
            writer = new PrintWriter(sock.getOutputStream());
            reader = new BufferedInputStream(sock.getInputStream());
            init_connection();
            while(!sock.isClosed()) {

                response = read();
                System.out.println(Thread.currentThread().getName() + " : " + response);
                toSend = pars_command(response.substring(0, 1).toUpperCase() + response.substring(1));
                writer.write(toSend);
                writer.flush();

                if(closeConnexion){
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    writer = null;
                    reader = null;
                    sock.close();
                }
            }
        }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }
    }

    private String read() throws IOException
    {
        String response = "";
        int stream;
        byte[] b = new byte[1024];
        stream = reader.read(b);
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
            toSend = "WELCOME\n";
            writer.write(toSend);
            writer.flush(   );
            response = read();
            response = response.substring(0, response.length() - 1);
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
                }
            }
            toSend = Integer.toString(nbClient) + "\n" + Integer.toString(Parser.getX()) + " " + Integer.toString(Parser.getX()) + "\n";
            writer.write(toSend);
            writer.flush();
            remote = (InetSocketAddress)sock.getRemoteSocketAddress();
            debug = Thread.currentThread().getName() + " : Succesfully connected!\n";
            System.err.println("\n" + debug);
        }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            return ;
        } catch (IOException e) {
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
        System.out.println(this.Parser.getTeams().get(0).getTeamName());

        List<Team> teams = this.Parser.getTeams();

        for (Team team : teams)
        {
            if (team.getTeamName().equals(teamName) && team.getNbClients() > 0)
            {
                Random r = new Random();
                team.getPlayers().add(new Player(r.nextInt(11), r.nextInt(11), this.nbSocket, teamName, Orientation.getRandomOrientation(), inventory));
                control.setTeams(teams);
                return true;
            }
        }
        return false;
    }

    private String pars_command(String cmd)
    {
        String toSend = "ko\n";
        boolean isValidCommand = false;
        Command newCmd;
        Timeline time;

        switch(cmd)
        {
            case "Forward\n":
            case "Left\n":
            case "Right\n":
            case "Fork\n":
            toSend = "ok\n";
            isValidCommand = true;
            case "Look\n":
            case "Inventory\n":
            case "Connect_nbr\n":
            case "Eject\n":
            case "Incantation\n":
            isValidCommand = true;
            default:
            if (cmd.startsWith("Take ") || cmd.startsWith("Set ") || cmd.startsWith("Broadcast "))
            {
                toSend = "ok\n";
                isValidCommand = true;
            }
        }
        if (isValidCommand) {
            System.out.println("Commande valide!");
            cmd = cmd.substring(0, cmd.length() - 1);
            newCmd = this.control.createCommand(cmd, this.nbSocket);
            time = this.control.getTimeline();
            control.addCommand(newCmd);
            if (time.getCommands() != null) {
                for (int i = 0; i < time.getCommands().size(); i++) {
                    if (this.control.isActionFinished(time.getCommands().get(i))) {
                        System.out.println(newCmd.getName() + " is finish, removed from the stack");
                    }
                }
            }
        }
        return toSend;
    }

}
