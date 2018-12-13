import java.util.*;

class Parser {
    private int port;
    private int x;
    private int y;
    private List<Team> teams = new ArrayList<Team>();
    private int nbClient;
    private int freq;

    Parser(String[] args)
    {
        String[] tab = {"-p","-x","-y","-c","-f"};
        int select = 0;

        for (int i = 0; i < args.length; i+=2) {
            switch (args[i]) {
                case "-p" :
                if (Integer.parseInt(args[i+1]) > 9999)
                    setPort(4242);
                else
                    setPort(Integer.parseInt(args[i+1]));
                break;
                case "-x" :
                if (Integer.parseInt(args[i+1]) >= 100)
                    setX(100);
                else
                    setX(Integer.parseInt(args[i+1]));
                break;
                case "-y" :
                if (Integer.parseInt(args[i+1]) >= 100)
                    setY(100);
                else
                    setY(Integer.parseInt(args[i+1]));
                break;
                case "-n" :
                for (int y = 0; y<5; y++) {
                    if (args[i+2] == tab[y])
                        select = 1;
                }
                setSquadName(args[i+1]);
                if (select == 0)
                    setSquadName(args[i+2]);
                break;
                case "-c" :
                if (Integer.parseInt(args[i+1]) >= 100)
                    setNbClient(100);
                else
                    setNbClient(Integer.parseInt(args[i+1]));
                break;
                case "-f" :
                setFreq(Integer.parseInt(args[i+1]));
                break;
            }
        }
    }

    void setPort(int port) {
        this.port = port;
    }
    void setX(int x) {
        this.x = x;
    }
    void setY(int y) {
        this.y = y;
    }
    void setSquadName(String team_name) {
        this.teams.add(new Team(team_name));
    }
    void setNbClient(int nbClient) {
        this.nbClient = nbClient;
    }
    void setFreq(int freq) {
        this.freq = freq;
    }
}
