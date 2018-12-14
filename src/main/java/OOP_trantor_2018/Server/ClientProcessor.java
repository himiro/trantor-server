import java.util.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;


public class ClientProcessor implements Runnable {
    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private Parser Parser;

    public ClientProcessor(Socket pSock, Parser Pars) {
        sock = pSock;
        Parser = Pars;
    }
    public void run() {
        String response;
        boolean closeConnexion = false;
        InetSocketAddress remote;
        String debug;
        String toSend;
        List<Team> tmp = new ArrayList<Team>();
        Team temp;
        int nbClient = 0;

        while(!sock.isClosed()) {
            try {
                writer = new PrintWriter(sock.getOutputStream());
                reader = new BufferedInputStream(sock.getInputStream());
                toSend = "WELCOME\n";
                writer.write(toSend);
                writer.flush();
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
                toSend = Integer.toString(nbClient) + "\n";
                writer.write(toSend);
                writer.flush();
                toSend = Integer.toString(Parser.getX()) + " " + Integer.toString(Parser.getX()) + "\n";
                writer.write(toSend);
                writer.flush();
                remote = (InetSocketAddress)sock.getRemoteSocketAddress();
                debug = "";
                debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +".";
                debug += " Sur le port : " + remote.getPort() + ".\n";
                System.err.println("\n" + debug);
                response = read();
                System.out.println(response);
                if(closeConnexion){
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    writer = null;
                    reader = null;
                    sock.close();
                    break;
                }
            }catch(SocketException e){
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
