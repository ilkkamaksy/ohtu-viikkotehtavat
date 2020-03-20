package main.java.laskin;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import laskin.Sovelluslogiikka;

/**
 * Miinus
 */
public class Miinus extends BinaariKomento {

    public Miinus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        otaArvo();
        sovellus.miinus(arvo);
        asetaTulos();
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
        asetaTulos();
    }
    
    
}