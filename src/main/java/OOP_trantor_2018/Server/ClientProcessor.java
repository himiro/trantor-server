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

    public ClientProcessor(Socket pSock, Parser Pars) {
        sock = pSock;
        Parser = Pars;
    }
    public void run() {
        InetSocketAddress remote;
        String response;
        List<Team> tmp = new ArrayList<Team>();
        Team temp;
        int nbClient = 0;
        String debug;
        String toSend;


        try {
            writer = new PrintWriter(sock.getOutputStream());
            reader = new BufferedInputStream(sock.getInputStream());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(!sock.isClosed()) {
            try {
                response = read();
                System.out.println(Thread.currentThread().getName() + " : " + response);
                toSend="ok\n";
                writer.write(toSend);
                writer.flush();

                if(closeConnexion){
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    writer = null;
                    reader = null;
                    sock.close();
                }
            }catch(SocketException e){
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String read() throws IOException {
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
}
