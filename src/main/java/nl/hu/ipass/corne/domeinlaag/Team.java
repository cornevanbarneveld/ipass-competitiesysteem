package nl.hu.ipass.corne.domeinlaag;

import nl.hu.ipass.corne.domeinlaag.Speler;

import java.util.ArrayList;

public class Team {
    private String naam;
    private ArrayList<Speler> spelers = new ArrayList<Speler>();

    public Team(String nm) {
        this.naam = nm;
    }

    public void VoegspelerToe(Speler speler) {
        if (!spelers.contains(speler)) {
            spelers.add(speler);
        }

    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public String getNaam() {
        return naam;
    }


}
