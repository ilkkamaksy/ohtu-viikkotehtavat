package main.java.laskin;

import javafx.scene.control.TextField;
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
    int tulos = 0;

    public Komento(
        TextField tuloskentta, 
        TextField syotekentta, 
        Button nollaa, 
        Button undo, 
        Sovelluslogiikka sovellus 
    ) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;  
        this.tulos = this.sovellus.tulos();
    }

    public abstract void suorita();

    public void peru() {
        sovellus.setTulos(this.tulos);
    }
}