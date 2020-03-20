package main.java.laskin;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import laskin.Sovelluslogiikka;

/**
 * Nollaa
 */
public class Nollaa extends Komento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        sovellus.nollaa();
        super.asetaTulos();
    }
    
    @Override
    public void peru() {
        
    }
    
}