package main.java.laskin;

import javafx.scene.control.TextField;

import java.util.ArrayDeque;
import javafx.scene.control.Button;
import laskin.Sovelluslogiikka;

/**
 * Komento
 */
public abstract class Komento {

    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    int arvo = 0;

    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public abstract void suorita();

    public abstract void peru();

    public void asetaTulos() {
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        this.asetaNollausNapinTila();
        this.asetaUndoNapinTila();
    }

    protected void asetaNollausNapinTila() {
        nollaa.disableProperty().set(sovellus.tulos() == 0);
    }

    protected void asetaUndoNapinTila() {
        undo.disableProperty().set(sovellus.tulos() == 0);
    }

}