import java.util.*;

public class Trantor {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World !");
        Parser Pars = new Parser(args);
        Server serv = new Server(4242, Pars);
        serv.open();

        /*Linemate linemate = new Linemate();
        Deraumere deraumere = new Deraumere();
        Sibur sibur = new Sibur();
        Mendiane mendiane = new Mendiane();
        Phiras phiras = new Phiras();
        Thystame thystame = new Thystame();
        Food food = new Food();

        Map<String, Ressource> base_ressource = new HashMap<String, Ressource>();
        base_ressource.put("Linemate", linemate);
        base_ressource.put("Deraumere", deraumere);
        base_ressource.put("Sibur", sibur);
        base_ressource.put("Mendiane", mendiane);
        base_ressource.put("Phiras", phiras);
        base_ressource.put("Thystame", thystame);
        base_ressource.put("Food", food);

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);



        Player player1 = new Player(10, 10, 123, team1.getTeamName(), Orientation.NORTH, base_ressource);
        Player player2 = new Player(0, 0, 124, team1.getTeamName(), Orientation.SOUTH, base_ressource);
        Player player3 = new Player(10, 10, 125, team2.getTeamName(), Orientation.EAST, base_ressource);
        Player player4 = new Player(0, 0, 126, team2.getTeamName(), Orientation.WEST, base_ressource);

        List<Player> plTeam1 = new ArrayList<Player>();
        plTeam1.add(player1);
        plTeam1.add(player2);
        team1.setPlayers(plTeam1);

        List<Player> plTeam2 = new ArrayList<Player>();
        plTeam2.add(player3);
        plTeam2.add(player4);
        team2.setPlayers(plTeam2);

        Command forwardCmd = controller.createCommand("Forward", 126);
        Command leftCmd = controller.createCommand("Left", 126);
        Command rightCmd = controller.createCommand("Right", 126);
        Command inventoryCmd = controller.createCommand("Inventory", 126);
        Command ejectCmd = controller.createCommand("Eject", 126);
        Command takeCmd = controller.createCommand("Take Food", 126);
        /*Command takeCmd = controller.createCommand("Take Linemate", 126);
        Command takeCmd = controller.createCommand("Take Sibur", 126);
        Command takeCmd = controller.createCommand("Take Phiras", 126);
        Command takeCmd = controller.createCommand("Take Thystame", 126);
        Command takeCmd = controller.createCommand("Take Mendiane", 126);
        Command takeCmd = controller.createCommand("Take Deraumere", 126);*/
        //Command setCmd = controller.createCommand("Set Food", 126);
        /*Command setCmd = controller.createCommand("Set Linemate", 126);
        Command setCmd = controller.createCommand("Set Sibur", 126);
        Command setCmd = controller.createCommand("Set Phiras", 126);
        Command setCmd = controller.createCommand("Set Thystame", 126);
        Command setCmd = controller.createCommand("Set Mendiane", 126);
        Command setCmd = controller.createCommand("Set Deraumere", 126);*/
        //Command lookCmd = controller.createCommand("Look", 126);
        //Command broadcastCmd = controller.createCommand("Broadcast blah blah", 126);
        //Command incantationCmd = controller.createCommand("Incantation", 126);
        //Command forkCmd = controller.createCommand("Fork", 126);
        //Plus de 10 commandes
        //controller.createCommand("Forward", 126);
        //controller.createCommand("Broadcast dezio edjzio", 126);
        //Command falseCmd = controller.createCommand("False", 126);

        /*System.out.println("\n\n\n");
        System.out.println("TIMELINE : ");
        controller.getTimeline().getCommands().forEach(k->{
            System.out.println(""+k.getName());
        });
        System.out.println("");
        System.out.println("Player queue : ");
        player4.getQueue().forEach(k->{
            System.out.println(""+k.getName());
        });
        System.out.println("\n\n\n");
        while(true)
        {
            controller.isActionFinished(forwardCmd);
            controller.isActionFinished(leftCmd);
            controller.isActionFinished(rightCmd);
            controller.isActionFinished(inventoryCmd);
            controller.isActionFinished(ejectCmd);
            controller.isActionFinished(takeCmd);
            controller.isActionFinished(setCmd);
            controller.isActionFinished(lookCmd);
        }*/

    }
}
