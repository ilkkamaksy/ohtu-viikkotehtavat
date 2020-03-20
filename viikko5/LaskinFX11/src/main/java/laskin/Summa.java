package main.java.laskin;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import laskin.Sovelluslogiikka;

/**
 * Summa
 */
public class Summa extends BinaariKomento {

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        otaArvo();
        sovellus.plus(arvo);
        asetaTulos();
    }

    @Override
    public void peru() {
        sovellus.miinus(arvo);
        asetaTulos();
    }
    
}