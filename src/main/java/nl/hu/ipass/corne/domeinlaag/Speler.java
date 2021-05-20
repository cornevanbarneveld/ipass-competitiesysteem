package nl.hu.ipass.corne.domeinlaag;

import nl.hu.ipass.corne.domeinlaag.Gebruiker;

import java.util.ArrayList;

public class Speler extends Gebruiker {
    private String naam;
    private static int gelKaarten;
    private static int rodeKaarten;


    public Speler(String nm , String gnm, String ww, String tp){
        super(gnm,ww,tp);
        this.naam = nm;
    }



    public void addGeleKaart() { gelKaarten += 1; }

    public void addRodeKaart() { rodeKaarten += 1; }

    public int getGelKaart(){
        return gelKaarten;
    }

    public int getRodeKaart() {
        return rodeKaarten;
    }
}
