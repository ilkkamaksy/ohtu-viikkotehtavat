package ohtu;

import ohtu.laskin.KonsoliIO;

public class Main {
    public static void main(String[] args) {
        Laskin laskin = new ohtu.Laskin( new ohtu.KonsoliIO() );
        laskin.suorita();
    }
}
