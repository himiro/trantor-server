import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private int port = 4242;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;
    private Parser Parser;
    private Controller Control;

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
        Controller controller = new Controller(Parser);
        Control = controller;
    }

    public void open()
    {
        Thread thr = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){
                    try {
                        Socket client = server.accept();
                        Thread thr = new Thread(new ClientProcessor(client, Parser, Control));
                        thr.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });
        thr.start();
    }

    public void close(){
        isRunning = false;
    }
}
