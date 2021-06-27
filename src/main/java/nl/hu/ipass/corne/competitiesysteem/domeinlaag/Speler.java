package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.io.Serializable;
import java.util.ArrayList;


public class Speler implements Serializable {
    private String naam;
    private int spelernummer;
    private Team team;
    private static ArrayList<Speler> spelers = new ArrayList<Speler>();

    private static int spelersAantal;


    public Speler(String nm){
        this.naam = nm;
        this.spelernummer = spelersAantal++;
        spelers.add(this);
    }

    public String getNaam() {
        return naam;
    }
    public void setTeam(Team t) { this.team = t; }
    public Team getTeam() {
        return team;
    }

    public void addSpeler( Speler s) {
        if (!spelers.contains(s)) {
            spelers.add(s);
        }
    }
    public static ArrayList<Speler> getSpelers() { return spelers; }
    public int getSpelernummer() { return spelernummer; }

}
