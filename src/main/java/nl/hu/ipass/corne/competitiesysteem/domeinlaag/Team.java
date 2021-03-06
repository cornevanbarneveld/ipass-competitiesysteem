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

    public static Team getTeamOpNaamEnClub(String t, String c) {
        for (Team team: alleTeams) {
            if (t.equals(team.getNaam()))  {
                if (c.equals(team.getClub().Getnaam())) {
                    return team;
                }

            }
        }

        return null;
    }

    @Override
    public boolean equals(Object andereObject) {
        boolean gelijkeObjecten = false;
        if (andereObject instanceof Team) {
            Team anderteam = (Team) andereObject;
            if (this.naam.equals(anderteam.naam) && this.club.Getnaam().equals(anderteam.getClub().Getnaam())) {
                gelijkeObjecten = true;
            }
        }
        return gelijkeObjecten;
    }
}
