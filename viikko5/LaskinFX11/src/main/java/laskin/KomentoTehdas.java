package main.java.laskin;

import javafx.scene.control.Button;
import java.util.ArrayDeque;
import java.util.HashMap;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import main.java.laskin.Komento;
import main.java.laskin.KomentoTehdas;
import main.java.laskin.Miinus;
import main.java.laskin.Nollaa;
import main.java.laskin.Summa;
import laskin.Sovelluslogiikka;


/**
 * KomentoTehdas
 */
public class KomentoTehdas {

    private HashMap<Button, Komento> komennot;
    private ArrayDeque<Komento> historia;

    public KomentoTehdas(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.komennot = new HashMap<>();    
        this.komennot.put(plus, makeSumma(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(miinus, makeMiinus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(nollaa, makeNollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.historia = new ArrayDeque<>();
    }

    public Komento make(Event event) {
        Komento komento = this.komennot.get( (Button)event.getTarget() );
        historia.addFirst(komento);
        return komento;
    }

    private static Komento makeSumma(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        return new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    private static Komento makeMiinus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        return new Miinus(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    private static Komento makeNollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        return new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    public Komento getPrevious() {
        return historia.poll();
    }

    @Override
    public String toString() {
        return "komentotehdas " + this.historia.toString();
    }
}