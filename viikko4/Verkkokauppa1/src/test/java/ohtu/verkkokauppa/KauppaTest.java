package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import ohtu.verkkokauppa.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    final String kaupanTili = "33333-44455";
    final String asiakkaanTili = "9876-9876";
    final String asiakas = "Pekka";
    int viitenumero;
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        viitenumero = 42;
        asetaViiteNumero(viitenumero);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);

        asetaTuotteenSaldoVarastossa(1, 10);
        kunHaetaanTuoteVarastosta(1, "maito", 5);
        asetaTuotteenSaldoVarastossa(2, 4);
        kunHaetaanTuoteVarastosta(2, "leipä", 5);
        asetaTuotteenSaldoVarastossa(3, 0);
        kunHaetaanTuoteVarastosta(3, "juusto", 10);
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunTuotteitaYksi() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1); 
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 5);   
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunKaksiErilaistaTuotetta() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 10);
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunKaksiSamaaTuotetta() {        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 10);
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunKaksiTuotettaJoistaToisenSaldoNolla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 5);
    }

    @Test
    public void aloitaAsiointiNollaaOstoskorin() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(2);
        tehdaanOstokset();
        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 20);
    }

    @Test
    public void kauppaPyytaaUudenViitenumeroJokaiselleMaksutapahtumalle() {
        when(viite.uusi()).
                thenReturn(42).
                thenReturn(43).
                thenReturn(44);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 5);
        viitenumero = 43;

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 5);
        viitenumero = 44;

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 5);
    }

    @Test 
    public void tuotteenPoistoKoristaVahentaaTuotteenOstoksista() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.poistaKorista(2);
        tehdaanOstokset();

        verify(pankki).tilisiirto(asiakas, viitenumero, asiakkaanTili, kaupanTili, 5);
    }

    // Helper functions
    public void tehdaanOstokset() {
        kauppa.tilimaksu(asiakas, asiakkaanTili);
    }

    public void asetaViiteNumero(int viitenumero) {
        when(viite.uusi()).thenReturn(viitenumero);
    }

    public void asetaTuotteenSaldoVarastossa(int tuoteKoodi, int saldo) {
        when(varasto.saldo(tuoteKoodi)).thenReturn(saldo);
    }

    public void kunHaetaanTuoteVarastosta(int tuotekoodi, String tuotenimi, int hinta) {
        when(varasto.haeTuote(tuotekoodi)).thenReturn(luoTuote(tuotekoodi, tuotenimi, hinta));
    }

    public Tuote luoTuote(int tuotekoodi, String tuotenimi, int hinta) {
        return new Tuote(tuotekoodi, tuotenimi, hinta);
    }
    
}