package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.util.ArrayList;

public class Competitie {
    private int nummer;
    private String datumseizoen;
    private String klasse;
    private static int competitieaantal;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<Wedstrijd> alleWedstrijden = new ArrayList<Wedstrijd>();


    public Competitie( String szn, String  ks) {
        this.nummer = competitieaantal++;
        this.datumseizoen = szn;
        this.klasse = ks;


    }

    public void AddTeam(Team t) {
        if (!teams.contains(t)) {
            teams.add(t);
        }

    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void AddWedstrijd(Wedstrijd wedstrijd) {
        if (!alleWedstrijden.contains(wedstrijd)) {
            alleWedstrijden.add(wedstrijd);
        }

    }

    public ArrayList<Wedstrijd> getAlleWedstrijden() {
        return alleWedstrijden;
    }
}
