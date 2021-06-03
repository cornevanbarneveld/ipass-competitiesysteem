package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.io.Serializable;
import java.util.ArrayList;

public class Gebruiker implements Serializable {
    private String gebruikersnaam;
    private String wachtwoord;
    private String type;
    private static ArrayList<Gebruiker> alleGebruikers = new ArrayList<Gebruiker>();


    public Gebruiker(String gnm, String ww, String tp) {
        this.gebruikersnaam = gnm;
        this.wachtwoord = ww;
        this.type = tp;
        alleGebruikers.add(this);

    }

    public String getGebruikersnaam(){
        return gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void addGebruiker(Gebruiker g) {
        alleGebruikers.add(g);
    }



    public static ArrayList<Gebruiker> getAlleGebruikers() {
        return alleGebruikers;
    }


}
