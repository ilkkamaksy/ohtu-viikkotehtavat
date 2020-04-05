package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
        String url = "https://nhl27112019.herokuapp.com/players.txt";
        // ajan tasalla olevat tilastot osoitteessa
        // "https://nhlstatisticsforohtu.herokuapp.com/players.txt"

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        QueryBuilder query1 = new QueryBuilder();
        Matcher m1 = query1.playsIn("PHI")
          .hasAtLeast(10, "assists")
          .hasFewerThan(8, "goals").build();

        QueryBuilder query2 = new QueryBuilder();
        Matcher m2 = query2.playsIn("EDM")
                .hasAtLeast(20, "points").build();

        QueryBuilder query3 = new QueryBuilder();
        Matcher m = query3.oneOf(m1, m2).build();
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }

    }
}
