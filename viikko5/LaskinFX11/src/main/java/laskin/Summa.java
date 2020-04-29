package main.java.laskin;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import laskin.Sovelluslogiikka;

/**
 * Summa
 */
public class Summa extends BinaariKomento {

    private int arvo;
    private int tulos;

    public Summa(
        TextField tuloskentta, 
        TextField syotekentta, 
        Button nollaa, 
        Button undo, 
        Sovelluslogiikka sovellus
    ) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        this.tulos = sovellus.tulos();
        this.arvo = Integer.valueOf(syotekentta.getText());
        sovellus.plus(this.arvo);
    }

}