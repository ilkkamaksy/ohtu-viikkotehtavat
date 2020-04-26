package statistics.matcher;

import java.util.*;

public class QueryB {
    
    Matcher query;
    
    public QueryB() {        
        this.query = new All();
    }

    public Matcher build() {
        return query;
    }

    public QueryB playsIn(String team) {
        query = new PlaysIn(team);
        return this;
    }

    public QueryB hasAtLeast(int value, String category) {
        query = new HasAtLeast(value, category);
        return this;
    }

    public QueryB hasFewerThan(int value, String category) {
        query = new HasFewerThan(value, category);
        return this;
    }

    public Matcher oneOf(Matcher... matchers) {
        return new Or(matchers);
    }
}