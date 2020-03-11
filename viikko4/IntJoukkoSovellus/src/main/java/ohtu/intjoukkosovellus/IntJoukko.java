
package ohtu.intjoukkosovellus;

public class IntJoukko {

    private static int OLETUSKAPASITEETTI = 5; 
    private static int OLETUSKASVATUSKOKO = 5;  
    
    private int kapasiteetti;
    private int kasvatuskoko;
    private int[] lukuJoukko;      
    private int alkioidenLkm;

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUSKOKO);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUSKOKO);
    }
   
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this.kapasiteetti = kapasiteetti;
        this.kasvatuskoko = kasvatuskoko;
        this.onkoValidiKapasiteettiJaKasvatuskoko();
        this.alkioidenLkm = 0;
        this.lukuJoukko = new int[kapasiteetti];
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukuJoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            tarkistaLukujoukonKoko();
            return true;
        }
        return false;
    }

    private void tarkistaLukujoukonKoko() {
        if (alkioidenLkm % lukuJoukko.length == 0) {
            kasvataLukujoukkoa();
        }
    }

    private void kasvataLukujoukkoa() {
        int[] vanhaTaulukko = lukuJoukko;
        this.lukuJoukko = new int[alkioidenLkm + kasvatuskoko];
        this.lukuJoukko = kopioiTaulukko(vanhaTaulukko, this.lukuJoukko);
    }

    private int[] kopioiTaulukko(int[] lahde, int[] kohde) {
        for (int i = 0; i < lahde.length; i++) {
            kohde[i] = lahde[i];
        }
        return kohde;
    }

    public boolean kuuluu(int luku) {
        if (haeLuvunIndeksi(luku) > -1) {
            return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = haeLuvunIndeksi(luku);
        if (indeksi > -1) {
            lukuJoukko[indeksi] = 0;
            siirraAlkiotIndeksista(indeksi);
            alkioidenLkm--;
            return true;
        }        
        return false;
    }

    private int haeLuvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                return i; 
            }
        }
        return -1;
    }

    private void siirraAlkiotIndeksista(int indeksi) {
        for (int j = indeksi; j < alkioidenLkm - 1; j++) {
            vaihdaAlkiotIndekseissa(j, j+1);
        }
    }

    private void vaihdaAlkiotIndekseissa(int lahdeIndeksi, int kohdeIndeksi) {
        int tempLuku = lukuJoukko[lahdeIndeksi];
        lukuJoukko[lahdeIndeksi] = lukuJoukko[kohdeIndeksi];
        lukuJoukko[kohdeIndeksi] = tempLuku;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    public static IntJoukko yhdiste(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko tulos = new IntJoukko();
        
        int[] arvotA = joukkoA.toIntArray();
        tulos = lisaaArvotJoukkoon(arvotA, tulos);
        
        int[] arvotB = joukkoB.toIntArray();
        tulos = lisaaArvotJoukkoon(arvotB, tulos);
        
        return tulos;
    }

    public static IntJoukko leikkaus(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko tulos = new IntJoukko();
        int[] arvotA = joukkoA.toIntArray();
        
        for (int i = 0; i < arvotA.length; i++) {
            if(joukkoB.kuuluu(arvotA[i])) {
                tulos.lisaa(arvotA[i]);
            }
        }
        
        return tulos;
    }
    
    public static IntJoukko erotus (IntJoukko lahde, IntJoukko poistettavat) {
        IntJoukko tulos = new IntJoukko();
        
        int[] lahdeArvot = lahde.toIntArray();
        tulos = lisaaArvotJoukkoon(lahdeArvot, tulos);

        int[] poistoArvot = poistettavat.toIntArray();
        tulos = poistaArvotJoukosta(poistoArvot, tulos);

        return tulos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJoukko[i];
        }
        return taulu;
    }

    private static IntJoukko lisaaArvotJoukkoon(int[] arvot, IntJoukko joukko) {
        for (int i = 0; i < arvot.length; i++) {
            joukko.lisaa(arvot[i]);
        }
        return joukko;
    }

    private static IntJoukko poistaArvotJoukosta(int[] arvot, IntJoukko joukko) {
        for (int i = 0; i < arvot.length; i++) {
            joukko.poista(arvot[i]);
        }
        return joukko;
    }

    private void onkoValidiKapasiteettiJaKasvatuskoko() {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetin ja kasvatuskoon tulee olla positiivisia lukuja"); 
        }
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukuJoukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukuJoukko[i];
                tuotos += ", ";
            }
            tuotos += lukuJoukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }
}
