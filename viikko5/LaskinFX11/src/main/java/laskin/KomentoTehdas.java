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

    TextField tuloskentta;
    TextField syotekentta;
    Button plus;
    Button miinus;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;
    private HashMap<Button, Komento> komennot;
    private ArrayDeque<Komento> historia;

    public KomentoTehdas(
        TextField tuloskentta, 
        TextField syotekentta, 
        Button plus, 
        Button miinus, 
        Button nollaa, 
        Button undo, 
        Sovelluslogiikka sovellus
    ) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.historia = new ArrayDeque<>();

        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(this.tuloskentta, this.syotekentta, this.nollaa, this.undo, this.sovellus));
        this.komennot.put(miinus, new Miinus(this.tuloskentta, this.syotekentta, this.nollaa, this.undo, this.sovellus));
        this.komennot.put(nollaa, new Nollaa(this.tuloskentta, this.syotekentta, this.nollaa, this.undo, this.sovellus));
    }

    public void execute(Event event) {

        Komento komento = null;
        Button komentoTyyppi = (Button) event.getTarget();
        
        if (komentoTyyppi == this.undo) {
            komento = this.historia.poll();
            komento.peru();
        } else {
            komento = this.haeKomento(komentoTyyppi);
            this.historia.addFirst(komento);
            komento.suorita();
        }

        this.paivitaTila();
    }

    private Komento haeKomento(Button kasky) {
        try {
            Komento komento = this.komennot.get(kasky)
                .getClass()
                .getDeclaredConstructor(tuloskentta.getClass(), syotekentta.getClass(), nollaa.getClass(), undo.getClass(), sovellus.getClass())
                .newInstance(this.tuloskentta, this.syotekentta, this.nollaa, this.undo, this.sovellus);            
            return komento;            
        } catch (Exception e) { 
            return null;
        }
    }

    protected void paivitaTila() {
        this.asetaTulos();
        this.asetaUndoNapinTila();
        this.asetaNollausNapinTila();
    }

    protected void asetaTulos() {    
        this.syotekentta.setText("");
        this.tuloskentta.setText("" + this.sovellus.tulos());
    }

    protected void asetaNollausNapinTila() {
        this.nollaa.disableProperty().set( this.sovellus.tulos() == 0);
    }

    protected void asetaUndoNapinTila() {
        this.undo.disableProperty().set( this.historia.isEmpty() );
    }
}