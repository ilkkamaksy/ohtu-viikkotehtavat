
package ohtu.kivipaperisakset;

import java.util.HashMap;

public class VaikeaTekoaly implements Tekoaly {

    private String[] muisti;
    private int vapaaMuistiIndeksi;
    private String viimeisinSiirto;
    private int k = 0;
    private int p = 0;
    private int s = 0;

    public VaikeaTekoaly(int muistinKoko) {
        muisti = new String[muistinKoko];
        vapaaMuistiIndeksi = 0;
    }
  
    public void lisaaMuistiin(String siirto) {
        this.vapautaMuistia();
        muisti[vapaaMuistiIndeksi] = siirto;    
        vapaaMuistiIndeksi++;
    }

    private void vapautaMuistia() {
        if(vapaaMuistiIndeksi == muisti.length) {
            for(int i = 1; i < muisti.length; i++) {
                muisti[i-1] = muisti[i];
            }
            vapaaMuistiIndeksi--;
        }
    }
  
    public String annaSiirto() {
        if(vapaaMuistiIndeksi == 0 || vapaaMuistiIndeksi == 1) {
            return "k";
        }

        return this.haeSuosituinSiirtoMuistista();
    }

    private String haeSuosituinSiirtoMuistista() {

        this.tarkistaSiirrotMuistissa();
        
        if(k > Math.max(p, s)) {
            return "p";
        } else if (p > Math.max(k, s)) {
            return "s";
        } 

        return "k";
    }

    private void tarkistaSiirrotMuistissa() {

        this.alustaSiirtolaskuri();

        viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];
        for(int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if(viimeisinSiirto.equals(muisti[i])) {
                this.paivitaSiirtoLaskuri(muisti[i+1]);
            }
        }
    }

    private void alustaSiirtolaskuri() {
        this.k = 0;
        this.p = 0;
        this.s = 0;
    }

    private void paivitaSiirtoLaskuri(String siirto) {
        if("k".equals(siirto)) {
            k++;
        } else if("p".equals(siirto)) {
            p++;
        } else {
            s++;
        }        
    }
}