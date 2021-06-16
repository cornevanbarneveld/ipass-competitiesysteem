package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;

public class Gebruiker implements Principal, Serializable {
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


    public String getWachtwoord() {
        return wachtwoord;
    }

    public void addGebruiker(Gebruiker g) {
        alleGebruikers.add(g);
    }


    public static ArrayList<Gebruiker> getAlleGebruikers() {
        return alleGebruikers;
    }

    @Override
    public String getName() {
        return gebruikersnaam;
    }

    public String getType() {
        return type;
    }

    public static Gebruiker getUserByName(String username) {
        for (Gebruiker g :alleGebruikers) {
            if(g.getName().equals(username)) {
                return g;
            }
        }
        return null;

    }

    public static String validateLogin(String username, String password){
        if(username==null || username.isBlank() || password == null || password.isBlank()) {
            return null;
        }

        var Mijngebruiker = Gebruiker.getUserByName(username);

        if(Mijngebruiker == null) {
            return null;
        }

        if (Mijngebruiker.getWachtwoord().equals(password)) {
            return Mijngebruiker.getType();
        }
        return null;

    }

}
