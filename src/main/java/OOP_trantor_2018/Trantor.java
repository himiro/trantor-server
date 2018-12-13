import java.util.*;

public class Trantor {
    public static void main(String[] args) {
        System.out.println("Hello World !");

        Linemate linemate = new Linemate();
        Deraumere deraumere = new Deraumere();
        Sibur sibur = new Sibur();
        Mendiane mendiane = new Mendiane();
        Phiras phiras = new Phiras();
        Thystame thystame = new Thystame();
        Food food = new Food();

        List<Ressource> base_ressource = new ArrayList<Ressource>();
        base_ressource.add(linemate);
        base_ressource.add(deraumere);
        base_ressource.add(sibur);
        base_ressource.add(mendiane);
        base_ressource.add(phiras);
        base_ressource.add(thystame);
        base_ressource.add(food);

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        Controller controller = new Controller(teams);

        Player player1 = new Player(10, 10, 123, team1.getTeamName(), Orientation.NORTH, base_ressource);
        Player player2 = new Player(10, 10, 124, team1.getTeamName(), Orientation.SOUTH, base_ressource);
        Player player3 = new Player(10, 10, 125, team2.getTeamName(), Orientation.EAST, base_ressource);
        Player player4 = new Player(10, 10, 126, team2.getTeamName(), Orientation.WEST, base_ressource);

        List<Player> plTeam1 = new ArrayList<Player>();
        plTeam1.add(player1);
        plTeam1.add(player2);
        team1.setPlayers(plTeam1);

        List<Player> plTeam2 = new ArrayList<Player>();
        plTeam2.add(player3);
        plTeam2.add(player4);
        team2.setPlayers(plTeam2);

        controller.createCommand("Look", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Forward", 126);
        controller.createCommand("Broadcast blah blah", 126);

        Player tmp = controller.findPlayerBySocketId(126);
        Command newCmd = new Command("Broadcast blah blah", tmp);
        controller.removeCommand(newCmd);

    }
}
