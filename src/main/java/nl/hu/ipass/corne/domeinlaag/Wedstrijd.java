package nl.hu.ipass.corne.domeinlaag;

import nl.hu.ipass.corne.domeinlaag.Team;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Wedstrijd {
    private LocalDateTime datum;
    private int veld;
    private int scoreThuisTeam;
    private int scoreUitTeam;
    private Team uitTeam;
    private Team thuisTeam;
    private Competitie competitie;
    private Map<Speler, String> geboekteSpelers = new HashMap<Speler, String>();


    public Wedstrijd(LocalDateTime dt, int vd, Team ut, Team tt , Competitie ct) {
        this.datum = dt;
        this.veld = vd;
        this.uitTeam= ut;
        this.thuisTeam = tt;
        this.competitie = ct;

        competitie.AddWedstrijd(this);




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


}
