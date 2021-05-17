package Domeinlaag;

import java.util.ArrayList;

public class Team {
    private String naam;
    private ArrayList<Speler> spelers = new ArrayList<Speler>();

    public Team(String nm) {
        this.naam = nm;
    }

    public void VoegspelerToe(Speler speler) {
        spelers.add(speler);
    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public String getNaam() {
        return naam;
    }


}
