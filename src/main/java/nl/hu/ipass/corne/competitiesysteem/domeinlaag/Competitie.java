package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.io.Serializable;
import java.util.ArrayList;

public class Competitie implements Serializable {
    private int nummer;
    private String naam;
    private String klasse;
    private int beginjaar;
    private int eindjaar;
    private static int competitieaantal;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<Wedstrijd> alleWedstrijden = new ArrayList<Wedstrijd>();
    private static ArrayList<Competitie> competities = new ArrayList<Competitie>();


    public Competitie( String naam,  String  ks , int bgj, int eij) {
        this.beginjaar = bgj;
        this.eindjaar = eij;
        this.nummer = competitieaantal++;
        this.naam = naam;
        this.klasse = ks;
        competities.add(this);


    }

    public void AddTeam(Team t) {
        if (!teams.contains(t)) {
            teams.add(t);
        }

    }

    public int getBeginjaar() {
        return beginjaar;
    }
    public int getEindjaar() {
        return eindjaar;
    }

    public String getNaam() {
        return naam;
    }
    public int getNummer() { return nummer;}

    public void addCompetitie(Competitie c){
        if (!competities.contains(c)) {
            competities.add(c);
            this.nummer = competitieaantal++;
        }
    }

    public static ArrayList<Competitie> getCompetities() { return competities; }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void AddWedstrijd(Wedstrijd wedstrijd) {
        if (alleWedstrijden.contains(wedstrijd)) {
            alleWedstrijden.remove(wedstrijd);
        }

        alleWedstrijden.add(wedstrijd);
        wedstrijd.setCompetitie(this);

    }

    public static ArrayList<Competitie> getCompetitiesVoorTeam(Team t) {
        ArrayList<Competitie> comps = new ArrayList<Competitie>();

        for (Competitie c: competities) {
            for (Team team: c.getTeams()) {
                if (team.equals(t)) {
                    comps.add(c);
                }
            }
        }
        return comps;
    }



    public ArrayList<Wedstrijd> getAlleWedstrijden() {
        return alleWedstrijden;
    }

    @Override
    public boolean equals(Object andereObject) {
        boolean gelijkeObjecten = false;
        if (andereObject instanceof Competitie) {
            Competitie andereCompetitie = (Competitie) andereObject;
            if (this.nummer == andereCompetitie.nummer) {
                gelijkeObjecten = true;
            }
        }
        return gelijkeObjecten;
    }

}

