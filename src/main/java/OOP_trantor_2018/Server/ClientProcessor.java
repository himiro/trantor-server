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

    public ClientProcessor(Socket pSock, Parser Pars, Controller pControl)
    {
        this.sock = pSock;
        this.Parser = Pars;
        this.control = pControl;
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
                toSend = pars_command(response);
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
            tmp = Parser.getTeams();
            for (int i = 0; i < tmp.size(); i++) {
                temp = tmp.get(i);
                if (response == temp.getTeamName()) {
                    if ((nbClient = temp.getNbClients()) != 0) {
                        nbClient-=1;
                        temp.setNbClients(nbClient);
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

    private String pars_command(String cmd)
    {
        String[] tab1 = {"Forward\n","Left\n","Right\n","Broadcast text\n","Fork\n"};
        String[] tab2 = {"Look\n","Inventory\n","Connect_nbr\n","Eject\n","Incantation\n"};
        String toSend = "ko\n";
        boolean isValidCommand = false;
        Command newCmd;
        Timeline time;

        for (int i = 0; i < 5; i++) {
            if (tab1[i].equals(cmd) || cmd.startsWith("Take ") || cmd.startsWith("Drop ")) {
                toSend = "ok\n";
                isValidCommand = true;
            } else if (tab2[i].equals(cmd)) {
                isValidCommand = true;
            }
        }
        if (isValidCommand) {
            System.out.println("Commande valide!");
            newCmd = this.control.createCommand(cmd,Integer.parseInt(this.sock.toString()));
            time = this.control.getTimeline();
            time.addCommand(newCmd);
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
