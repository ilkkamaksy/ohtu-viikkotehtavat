package main.java.ohtu;

/**
 * Player
 */
public class Player {

    private String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public void addPoint() {
        this.points += 1; 
    }

    public void deductPoints() {
        this.points -= 1;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.name;
    }

}