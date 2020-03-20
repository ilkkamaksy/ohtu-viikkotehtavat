package main.java.laskin;

import javafx.scene.control.TextField;

import javafx.scene.control.Button;
import laskin.Sovelluslogiikka;

/**
 * BinaariKomento
 */
public abstract class BinaariKomento extends Komento {
    
    public BinaariKomento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    public abstract void suorita();

    public abstract void peru();

    public void otaArvo() {
        try {
            arvo = Integer.parseInt(this.syotekentta.getText());
        } catch (Exception e) {
        }
    }
}