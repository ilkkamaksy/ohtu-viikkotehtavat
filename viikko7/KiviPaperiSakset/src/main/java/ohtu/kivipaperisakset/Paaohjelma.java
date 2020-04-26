package ohtu.kivipaperisakset;

import java.util.HashMap;

public class Paaohjelma {

    public static void main(String[] args) {

        KonsoliIO io = new KonsoliIO();
        Tuomari tuomari = new Tuomari();
        Pelitehdas pelitehdas = new Pelitehdas();
        
        HashMap<String, Peli> komennot = new HashMap<>();
        komennot.put("a", pelitehdas.kaksinPeli(io, tuomari));
        komennot.put("b", pelitehdas.yksinPeli(io, tuomari));
        komennot.put("c", pelitehdas.vaikeaYksinPeli(io, tuomari));

        while (true) {
            
            String peliValinta = io.valitsePeli();
            
            if (!komennot.containsKey(peliValinta)) {
                break;
            } 

            komennot.get(peliValinta).pelaa();
        }

    }
}
