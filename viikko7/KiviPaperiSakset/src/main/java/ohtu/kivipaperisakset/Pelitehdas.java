package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.*;
import ohtu.kivipaperisakset.Yksinpeli;
import ohtu.kivipaperisakset.Kaksinpeli;

public class Pelitehdas {

    public Peli kaksinPeli(IO io, Tuomari tuomari) {
        return new Kaksinpeli(io, tuomari);
    }

    public Peli yksinPeli(IO io, Tuomari tuomari) {
        return new Yksinpeli(io, tuomari, new NormaaliTekoaly());
    }

    public Peli vaikeaYksinPeli(IO io, Tuomari tuomari) {
        return new Yksinpeli(io, tuomari, new VaikeaTekoaly(20));
    }
    
}