import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Server {
    private int port = 4242;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;
    private Parser Parser;
    private Controller Control;
    protected int nbSocket = 0;
    protected Graphical graphical;

    public Server(int pPort, Parser pars)
    {
        port = pPort;
        Parser = pars;
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Control = new Controller(Parser);
        this.graphical = new Graphical(this.Control, null);
    }

    public void open()
    {
        Thread thr = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){
                    try {
                        Socket client = server.accept();
                        Thread thr = new Thread(new ClientProcessor(client, Parser, Control, ++nbSocket, graphical));
                        thr.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thr.start();
    }
}
