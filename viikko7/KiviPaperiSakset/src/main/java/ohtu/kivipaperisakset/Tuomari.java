package ohtu.kivipaperisakset;

import java.util.HashMap;

public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;
    private static HashMap<String, Boolean> voittoSiirrot = new HashMap<>();

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;
        voittoSiirrot.put("ks", true);
        voittoSiirrot.put("sp", true);
        voittoSiirrot.put("pk", true);
        
    }

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    private static boolean tasapeli(String eka, String toka) {
        if (eka.equals(toka)) {
            return true;
        }

        return false;
    }

    private static boolean ekaVoittaa(String eka, String toka) {
        if (voittoSiirrot.containsKey(eka + toka)) {
            return voittoSiirrot.get(eka + toka);
        }

        return false;
    }

    public String toString() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
    }
}