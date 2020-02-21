package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

/**
 * StatisticsTest
 */
public class StatisticsTest {

    @Test
    public void palauttaaOikeanPelaajan() {
        Player player = stats.search("Semenko");
        assertEquals(player.getName(), "Semenko");
        assertEquals(player.getGoals(), 4);
        assertEquals(player.getAssists(), 12);
    }

    @Test
    public void palauttaaNullKunPelaajaaEiLoydy() {
        Player player = stats.search("Ei ole");
        assertNull(player);
    }

    @Test
    public void joukkueenPelaajatLoytyy() {
        List<Player> team = stats.team("EDM");
        assertEquals(team.size(), 3);
    }

    @Test
    public void palauttaaTyhjanListanKunJoukkuettaEiLoydy() {
        List<Player> team = stats.team("ei ole");
        List<Player> tyhja = new ArrayList<>();
        assertEquals(tyhja, team);
    }

    @Test
    public void palauttaaParhaatKolme() {
        List<Player> players = stats.topScorers(3);
        assertEquals(players.size(), 3);
    }

    @Test
    public void palauttaaParhaatNolla() {
        List<Player> players = stats.topScorers(0);
        assertEquals(players.size(), 0);
    }

    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }
    
}