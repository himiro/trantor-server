import java.util.*;
import java.io.PrintWriter;

class Graphical
{
    protected Controller controller;
    protected PrintWriter writer;
    protected List<PrintWriter> graphicals = new ArrayList<PrintWriter>();

    Graphical(Controller controller, PrintWriter writer)
    {
        this.controller = controller;
        this.writer = writer;
    }

    public Controller getController()
    {
        return (this.controller);
    }

    public void getController(Controller controller)
    {
        this.controller = controller;
    }

    public PrintWriter getWriter()
    {
        return (this.writer);
    }

    public void setWriter(PrintWriter writer)
    {
        this.writer = writer;
    }

    public List<PrintWriter> getGraphicals()
    {
        return (this.graphicals);
    }

    public void setGraphicals(List<PrintWriter> graphicals)
    {
        this.graphicals = graphicals;
    }

    public boolean contentOfTile(int x, int y, boolean all)
    {
        List<Tile> tiles = this.controller.getWorldMap().getTiles();
        StringBuffer buff = new StringBuffer();

        for (Tile tile : tiles)
        {
            if (all == true)
            {
                Map<String, Ressource> ressources = tile.getRessources();
                buff.append("bct " + tile.getX() + " " + tile.getY() + " " + ressources.get("Food").getNb() + " " + ressources.get("Linemate").getNb() + " "
                + ressources.get("Deraumere").getNb() + " " + ressources.get("Sibur").getNb() + " " + ressources.get("Mendiane").getNb() + " "
                + ressources.get("Phiras").getNb() + " " + ressources.get("Thystame").getNb() + '\n');
            }
            else if (tile.getX() == x && tile.getY() == y)
            {
                Map<String, Ressource> ressources = tile.getRessources();
                buff.append("bct " + x + " " + y + " " + ressources.get("Food").getNb() + " " + ressources.get("Linemate").getNb() + " "
                + ressources.get("Deraumere").getNb() + " " + ressources.get("Sibur").getNb() + " " + ressources.get("Mendiane").getNb() + " "
                + ressources.get("Phiras").getNb() + " " + ressources.get("Thystame").getNb() + '\n');
                if (all == false)
                {
                    buff.deleteCharAt(buff.length() - 1);
                    this.writer.println(buff);
                    this.writer.flush();
                    return true;
                }
            }
        }
        buff.deleteCharAt(buff.length() - 1);
        this.writer.println(buff);
        this.writer.flush();
        return false;
    }

    public void nameOfTeams()
    {
        StringBuffer buff = new StringBuffer();
        for (Team team : this.controller.getTeams())
        {
            buff.append(team.getTeamName() + '\n');
        }
        buff.deleteCharAt(buff.length() - 1);
        this.writer.println(buff);
        this.writer.flush();
    }

    public void writeToGraphical(String message)
    {
        for (PrintWriter graphical : this.graphicals)
        {
            graphical.println(message);
            graphical.flush();
        }
    }
}
