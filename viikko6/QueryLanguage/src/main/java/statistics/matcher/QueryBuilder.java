package statistics.matcher;

import java.util.*;

public class QueryBuilder {
    
    Matcher query;
    ArrayList<Matcher> queries;
    ArrayList<Matcher> unionQueries;
    
    public QueryBuilder() {        
        this.queries = new ArrayList<>();
        this.unionQueries = new ArrayList<>();
        this.queries.add(new All());
    }

    public Matcher build() {
        if (this.unionQueries.size() > 0) {
            return new Or(unionQueries.toArray(Matcher[]::new));
        }

        return new And(queries.toArray(Matcher[]::new));
    }

    public QueryBuilder playsIn(String team) {
        queries.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        queries.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        queries.add(new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        for(Matcher matcher : matchers) {
            this.unionQueries.add(matcher);
        }
        return this;
    }
}