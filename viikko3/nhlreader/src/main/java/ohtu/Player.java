
package ohtu;

import com.google.gson.annotations.SerializedName;

public class Player {
    private String name;
    private String team;

    @SerializedName("goals") 
    private int numberOfGoals;

    @SerializedName("assists") 
    private int numberOfAssists;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return this.team;
    }

    public void setNumberOfGoals(int numberOfGoals) {
        this.numberOfGoals = numberOfGoals;
    } 

    public int getNumberOfGoals() {
        return this.numberOfGoals;
    }

    public void setNumberOfAssists(int numberOfAssists) {
        this.numberOfAssists = numberOfAssists;
    }

    public int getNumberOfAssists() {
        return this.numberOfAssists;
    }

    public int getTotalPoints() {
        return this.numberOfGoals + this.numberOfAssists;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\t Team: " + team + "\t Goals: " + numberOfGoals + " Assists: " + numberOfAssists + "\t Total " + getTotalPoints();
    }
      
}
