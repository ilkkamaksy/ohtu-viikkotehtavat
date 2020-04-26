package ohtu.kivipaperisakset;

public class Yksinpeli extends Peli {

    private Tekoaly tekoaly;
    
    public Yksinpeli(IO io, Tuomari tuomari, Tekoaly tekoaly) {
        super(io, tuomari);
        this.tekoaly = tekoaly;
    }

    @Override
    public void pelaa() {
        super.ekanSiirto = super.io.teeSiirto("Ensimmäisen pelaajan siirto: ");
        this.tekoalynSiirto();
        super.kasitteleKierros();
    }

    private void tekoalynSiirto() {
        super.tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.lisaaMuistiin(ekanSiirto);
    }

}