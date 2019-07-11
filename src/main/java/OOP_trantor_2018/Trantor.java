import java.util.*;

public class Trantor {
    public static void main(String[] args) throws InterruptedException {
        Parser Pars = new Parser(args);
        Server serv = new Server(4242, Pars);
        serv.open();
    }
}
