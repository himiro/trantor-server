import java.util.*;
import java.io.PrintWriter;

public class GameLoop implements Runnable
{
    protected Controller controller;
    protected Graphical graphical;

    public GameLoop(Controller controller, Graphical graphical)
    {
        this.controller = controller;
        this.graphical = graphical;
    }

    public void run()
    {
        while (true)
        {
            if (this.controller.endOfGame(this.graphical) == true)
            {
                return ;
            }
            Timeline time = this.controller.getTimeline();
            List<Command> commands = time.getCommands();
            String toSend = "";

            if (commands != null && commands.size() > 0)
            {
                for (int i = 0; i < commands.size(); i++)
                {
                    PrintWriter tmp = commands.get(i).getPlayer().getWriter();
                    toSend = this.controller.isActionFinished(commands.get(i), this.graphical);
                    if (toSend != null && toSend != "nope")
                    {
                        System.out.println("ISACTIONFINISHED : " + time.getCommands() + " / " + toSend);
                        if (toSend.equals("true"))
                        {
                            tmp.println("ok");
                        }
                        else if (toSend.equals("false"))
                        {
                            tmp.println("ko");
                        }
                        else
                        {
                            tmp.println(toSend);
                        }
                        tmp.flush();
                    }
                }
                List<Team> teams = this.controller.getTeams();

                for (Team team : teams)
                {
                    List<Player> players = team.getPlayers();
                    for (Player player : players)
                    {
                        if (player.feed(time.getFrequence(), this.graphical).equals("dead"))
                        {
                            player.getWriter().println("dead");
                            player.getWriter().flush();
                            players.remove(player);
                            team.setPlayers(players);
                            int nbClient = team.getNbClients();
                            team.setNbClients(++nbClient);
                        }
                    }
                }
            }
        }
    }
}
