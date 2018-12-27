import java.util.*;

public enum Item
{
    FOOD("Food"),
    LINEMATE("Linemate"),
    DERAUMERE("Deraumere"),
    SIBUR("Sibur"),
    MENDIANE("Mendiane"),
    PHIRAS("Phiras"),
    THYSTAME("Thystame");

    private String name;

    Item(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String getNumberItem(Item item)
    {
        switch (item)
        {
            case FOOD:
            return "0";
            case LINEMATE:
            return "1";
            case DERAUMERE:
            return "2";
            case SIBUR:
            return "3";
            case MENDIANE:
            return "4";
            case PHIRAS:
            return "5";
            case THYSTAME:
            return "6";
        }
        return "";
    }

    private static final Map<String, Item> lookup = new HashMap<String, Item>();

    static
    {
        for(Item item : Item.values())
        {
            lookup.put(item.getName(), item);
        }
    }

    public static Item get(String name)
    {
        return lookup.get(name);
    }
}
