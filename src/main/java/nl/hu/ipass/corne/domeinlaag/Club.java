package nl.hu.ipass.corne.domeinlaag;

import java.util.ArrayList;

public class Club {
    private String naam;
    private ArrayList<Team> alleTeams = new ArrayList<Team>();

    public Club(String nm){
        this.naam = nm;
    }

    public void voegTeamToe(Team t) {
        alleTeams.add(t);
    }

    public ArrayList<Team> getTeams() {
        return alleTeams;
    }


}
