package laskin;

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

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    private KomentoTehdas komentotehdas;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) { 
        this.undo = undo;
        Sovelluslogiikka sovellus = new Sovelluslogiikka();
        this.komentotehdas = new KomentoTehdas(tuloskentta, syotekentta, plus, miinus, nollaa, undo, sovellus);
    }
    
    @Override
    public void handle(Event event) {
        komentotehdas.execute(event);
    }

}
