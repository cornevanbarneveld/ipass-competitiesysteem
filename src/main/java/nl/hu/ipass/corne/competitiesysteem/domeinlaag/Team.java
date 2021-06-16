package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private String naam;
    private Club club;
    private ArrayList<Speler> spelers = new ArrayList<Speler>();
    private static ArrayList<Team> alleTeams = new ArrayList<Team>();

    public Team(String nm) {
        this.naam = nm;
        alleTeams.add(this);
    }

    public void VoegspelerToe(Speler speler) {
        if (!spelers.contains(speler)) {
            spelers.add(speler);
            speler.setTeam(this);
            speler.setClub(club);

        }

    }

    public void setClub(Club clb){
        this.club = clb;
    }

    public Club getClub() {
        return club;
    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public String getNaam() {
        return naam;
    }

    public void addTeam(Team t){
        if (!alleTeams.contains(t)) {
            alleTeams.add(t);
        }

    }

    public static ArrayList<Team> getAlleTeams() {
        return alleTeams;
    }


}
