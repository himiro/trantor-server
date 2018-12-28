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
    private Controller control;
    protected int nbSocket = 0;
    protected Graphical graphical;

    /**
    * Constructor
    */
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
        control = new Controller(Parser);
        this.graphical = new Graphical(this.control, null);
    }


    /**
    * Returns value of port
    * @return
    */
    public int getPort()
    {
        return this.port;
    }

    /**
    * Returns value of host
    * @return
    */
    public String getHost()
    {
        return this.host;
    }

    /**
    * Returns value of server
    * @return
    */
    public ServerSocket getServer()
    {
        return this.server;
    }

    /**
    * Sets new value of server
    * @param
    */
    public void setServer(ServerSocket server)
    {
        this.server = server;
    }

    /**
    * Returns value of isRunning
    * @return
    */
    public boolean getIsRunning()
    {
        return this.isRunning;
    }

    /**
    * Sets new value of isRunning
    * @param
    */
    public void setIsRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
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

    /**
    * Open
    */
    public void open()
    {
        Thread gameloop = new Thread(new Runnable(){
            public void run(){
                Thread thr = new Thread(new GameLoop(control, graphical));
                thr.start();
            }
        });
        gameloop.start();

        Thread thr = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){
                    try {
                        Socket client = server.accept();
                        Thread thr = new Thread(new ClientProcessor(client, Parser, control, ++nbSocket, graphical));
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
