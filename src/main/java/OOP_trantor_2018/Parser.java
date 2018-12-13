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
                setTeams(args[i+1]);
                if (select == 0)
                    setTeams(args[i+2]);
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
		this.teams.add(new Team(team_name));
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
}
