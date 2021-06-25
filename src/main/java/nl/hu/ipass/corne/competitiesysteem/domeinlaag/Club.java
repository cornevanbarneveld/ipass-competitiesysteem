package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.io.Serializable;
import java.util.ArrayList;

public class Club implements Serializable {
    private String naam;
    private ArrayList<Team> alleTeams = new ArrayList<Team>();
    private static ArrayList<Club> alleClubs = new ArrayList<Club>();


    public Club(String nm){

        this.naam = nm;
        alleClubs.add(this);
    }

    public void voegTeamToe(Team t) {
        if (!alleTeams.contains(t)) {
            alleTeams.add(t);
        }

        t.setClub(this);
    }

    public String Getnaam() {
        return naam;
    }

    public ArrayList<Team> getTeams() {
        return alleTeams;
    }

    public void addClub(Club c) {
        if (!alleClubs.contains(c)) {
            alleClubs.add(c);

        }
    }

    public static ArrayList<Club> getalleClubs() {
        return alleClubs;
    }

    public static Club getClubOpNaam(String c) {
        for (Club cl: alleClubs) {
            if (cl.Getnaam().equals(c)) {
                return cl;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object andereObject) {
        boolean gelijkeObjecten = false;
        if (andereObject instanceof Club) {
            Club andereClub = (Club) andereObject;
            if (andereClub.Getnaam().equals(this.naam)) {
                gelijkeObjecten = true;
            }
        }
        return gelijkeObjecten;
    }



}
