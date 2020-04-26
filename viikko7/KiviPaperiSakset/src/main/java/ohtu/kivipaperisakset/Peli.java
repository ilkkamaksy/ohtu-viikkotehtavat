package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class Peli {

    protected IO io;
    protected Tuomari tuomari;
    protected String ekanSiirto;
    protected String tokanSiirto;

    public Peli(IO io, Tuomari tuomari) {
        this.io = io;
        this.tuomari = tuomari;
    }

    public abstract void pelaa();

    protected void kasitteleKierros() {
        if (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            this.tuomaroi();
            this.pelaa();
        } else {
            this.lopeta();
        }
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    private void tuomaroi() {
        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        System.out.println(tuomari);
        System.out.println();
    }

    private void lopeta() {
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
}