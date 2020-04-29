package main.java.laskin;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import laskin.Sovelluslogiikka;

/**
 * Miinus
 */
public class Miinus extends BinaariKomento {

    private int arvo;
    private int tulos;
    
    public Miinus(
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
        super.sovellus.miinus(this.arvo);
    }
}