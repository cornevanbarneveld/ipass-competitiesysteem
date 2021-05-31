package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.util.ArrayList;

public class Team {
    private String naam;
    private String type;
    private ArrayList<Speler> spelers = new ArrayList<Speler>();
    private static ArrayList<Team> alleTeams = new ArrayList<Team>();

    public Team(String nm, String tp) {
        this.naam = nm;
        this.type = tp;
        alleTeams.add(this);
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

    public String getLeeftijdsGroep() {
        return type;
    }

    public void addTeam(Team t){
        alleTeams.add(t);
    }

    public static ArrayList<Team> getAlleTeams() {
        return alleTeams;
    }


}
