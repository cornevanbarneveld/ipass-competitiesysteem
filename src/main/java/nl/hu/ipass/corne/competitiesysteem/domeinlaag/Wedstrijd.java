package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wedstrijd implements Serializable {
    private LocalDateTime datum;
    private int veld;
    private int scoreThuisTeam;
    private int scoreUitTeam;
    private boolean gespeeld;
    private Team uitTeam;
    private Team thuisTeam;
    private Competitie competitie;
    private Map<Speler, String> geboekteSpelers = new HashMap<Speler, String>();

    private static ArrayList<Wedstrijd> alleWedstrijden = new ArrayList<Wedstrijd>();

    public Wedstrijd(LocalDateTime dt, int vd, Team ut, Team tt) {
        if (!ut.equals(tt)) {
            this.datum = dt;
            this.veld = vd;
            this.uitTeam= ut;
            this.thuisTeam = tt;
            alleWedstrijden.add(this);
        }



    }

    public void setGespeeld() {
        gespeeld = true;
        if (competitie != null) {
            competitie.AddWedstrijd(this);
        }

    }

    public void setCompetitie (Competitie c) {
        this.competitie = c;

    }

    public boolean getGespeeld() {
        return gespeeld;
    }

    public void setScoreThuisTeam(int score) {
        scoreThuisTeam = score;
    }

    public void setScoreUitTeam(int score) {
        scoreUitTeam = score;
    }

    public int getScoreThuisTeam() {
        return scoreThuisTeam;
    }

    public int getScoreUitTeam() {
        return scoreUitTeam;
    }

    public void verVangDatum(LocalDateTime d) {
        this.datum = d;
    }

    public void setBoekingSpeler(Speler speler , String boeking ) {

        if (!geboekteSpelers.containsKey(speler)) {
            if (boeking.equals("geel")) {
                speler.addGeleKaart();
                geboekteSpelers.put(speler, boeking);
            } if (boeking.equals("rood")) {
                speler.addRodeKaart();
                geboekteSpelers.put(speler, boeking);
            }
        }
    }

    public Map<Speler , String> getGeboekteSpelers() {
        return geboekteSpelers;
    }

    public void addWedstrijd(Wedstrijd w) {
        alleWedstrijden.add(this);
    }

    public static ArrayList<Wedstrijd> getAlleWedstrijden() {
        return alleWedstrijden;
    }

    public Team getThuisTeam() {
        return thuisTeam;
    }

    public Team getUitTeam() {
        return uitTeam;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    @Override
    public boolean equals(Object andereObject) {
        boolean gelijkeObjecten = false;
        if (andereObject instanceof Wedstrijd) {
            Wedstrijd andereWedstrijd = (Wedstrijd) andereObject;
            if (this.thuisTeam.equals(andereWedstrijd.getThuisTeam()) && this.uitTeam.equals(andereWedstrijd.getUitTeam()) && this.datum == andereWedstrijd.getDatum()) {
                gelijkeObjecten = true;
            }
        }
        return gelijkeObjecten;
    }
}
