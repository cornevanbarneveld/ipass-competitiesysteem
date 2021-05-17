package Domeinlaag;

import java.util.ArrayList;
import java.util.Date;

public class Competitie {
    private String naam;
    private Date datumSeizoen;
    private String klasse;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<StandTeam> standTeams = new ArrayList<StandTeam>();
    private ArrayList<Wedstrijd> alleWedstrijden = new ArrayList<Wedstrijd>();


    public Competitie(String nm, Date dszn) {
        this.naam = nm;
        this.datumSeizoen = dszn;
    }

    public void AddTeam(Team t) {
        teams.add(t);
        standTeams.add(new StandTeam(t));
    }










}
