package tsp;

public class City {
    private String name;
    private int x;
    private int y;

    public City(String name, int x, int y) { //Clasa orasului.
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
