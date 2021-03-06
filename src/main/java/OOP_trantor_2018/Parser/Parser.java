import java.util.*;

class Parser {
    private int port = 4242;
    private int x = 10;
    private int y = 10;
    private List<Team> teams = new ArrayList<Team>();
    private int nbClient = 6;
    private int freq = 100;

    Parser(String[] args)
    {
        String[] tab = {"-p","-x","-y","-c","-f"};
        int select = 0;
        int select2 = 0;

        for (int i = 0; i < args.length; i+=2) {
            if (i + 1 >= args.length)
            {
                this.displayUsage();
                System.exit(84);
            }
            boolean isInt = this.isNumeric(args[i+1]);
            switch (args[i]) {
                case "-p" :
                if (isInt == true && (Integer.parseInt(args[i+1]) > 9999 || Integer.parseInt(args[i+1]) == 0))
                setPort(4242);
                else if (isInt == true)
                setPort(Integer.parseInt(args[i+1]));
                else
                this.displayUsage();
                break;
                case "-x" :
                if (isInt == true && (Integer.parseInt(args[i+1]) >= 100))
                setX(100);
                else if (isInt == true && (Integer.parseInt(args[i+1]) == 0))
                setX(10);
                else if (isInt == true)
                setX(Integer.parseInt(args[i+1]));
                else
                this.displayUsage();
                break;
                case "-y" :
                if (isInt == true && (Integer.parseInt(args[i+1]) >= 100))
                setY(100);
                else if (isInt == true && (Integer.parseInt(args[i+1]) == 0))
                setX(10);
                else if (isInt == true)
                setY(Integer.parseInt(args[i+1]));
                else
                this.displayUsage();
                break;
                case "-n" :
                if (i+2 == args.length)
                {
                    select = 1;
                }
                setTeams(args[i+1]);
                select2 = 1;
                if (select == 0)
                {
                    setTeams(args[i+2]);
                    i++;
                }
                break;
                case "-c" :
                if (isInt == true && (Integer.parseInt(args[i+1]) >= 100))
                setNbClient(100);
                else if (isInt == true)
                setNbClient(Integer.parseInt(args[i+1]));
                else
                this.displayUsage();
                break;
                case "-f" :
                if (isInt == true)
                setFreq(Integer.parseInt(args[i+1]));
                else
                this.displayUsage();
                break;
                default :
                this.displayUsage();
                System.exit(84);
            }
        }
        if (select2 == 0)
        setTeams("squadCL");
        for (int i = 0; i < 1; i++) {
            teams.get(i).setNbClients(nbClient);
        }
    }
    /**
    * Returns value of port
    * @return
    */
    public int getPort() {
        return port;
    }

    /**
    * Sets new value of port
    * @param
    */
    public void setPort(int port) {
        this.port = port;
    }

    /**
    * Returns value of x
    * @return
    */
    public int getX() {
        return x;
    }

    /**
    * Sets new value of x
    * @param
    */
    public void setX(int x) {
        this.x = x;
    }

    /**
    * Returns value of y
    * @return
    */
    public int getY() {
        return y;
    }

    /**
    * Sets new value of y
    * @param
    */
    public void setY(int y) {
        this.y = y;
    }

    /**
    * Returns value of teams
    * @return
    */
    public List<Team> getTeams() {
        return teams;
    }

    /**
    * Sets new value of teams
    * @param
    */
    public void setTeams(String team_name) {
        this.teams.add(new Team(team_name, this.nbClient));
    }

    /**
    * Returns value of nbClient
    * @return
    */
    public int getNbClient() {
        return nbClient;
    }

    /**
    * Sets new value of nbClient
    * @param
    */
    public void setNbClient(int nbClient) {
        this.nbClient = nbClient;
    }

    /**
    * Returns value of freq
    * @return
    */
    public int getFreq() {
        return freq;
    }

    /**
    * Sets new value of freq
    * @param
    */
    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void displayUsage()
    {
        System.out.println("USAGE: trantor.jar -p port -x width -y height -n name1 name2 -c clientsNb -f freq\n");
        System.out.println("    port        is the port number");
        System.out.println("    width       is the width of the world");
        System.out.println("    height      is the height of the world");
        System.out.println("    nameX       is the name of the team X");
        System.out.println("    clientsNb   is the number of authorized clients per team");
        System.out.println("    freq        is the reciprocal of time unit for execution of actions");
    }

    public boolean isNumeric(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
            {
                return false;
            }
        }
        return true;
    }
}
