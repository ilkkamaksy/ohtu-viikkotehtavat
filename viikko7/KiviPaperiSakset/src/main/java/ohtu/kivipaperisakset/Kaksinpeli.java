package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Kaksinpeli extends Peli {

    public Kaksinpeli(IO io, Tuomari tuomari) {
        super(io, tuomari);
    }

    @Override
    public void pelaa() {
        super.ekanSiirto = super.io.teeSiirto("Ensimmäisen pelaajan siirto: ");
        super.tokanSiirto = super.io.teeSiirto("Toisen pelaajan siirto: ");
        super.kasitteleKierros();
    }

}