package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KonsoliIO implements IO {
    
    private static final Scanner scanner = new Scanner(System.in);   

    public String valitsePeli() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan"
        );
        
        return scanner.nextLine();
    }

    public String teeSiirto(String ohje) {
        System.out.print(ohje);
        return scanner.nextLine();
    }
}