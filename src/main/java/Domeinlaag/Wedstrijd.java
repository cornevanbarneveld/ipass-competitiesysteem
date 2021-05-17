package Domeinlaag;

import java.util.Date;

public class Wedstrijd {
    private Date datum;
    private int veld;
    private int scoreThuisTeam;
    private int scoreUitTeam;
    private Team uitTeam;
    private Team thuisTeam;
    private StandTeam standTeam;


    public Wedstrijd(Date dt, int vd, Team ut, Team tt) {
        this.datum = dt;
        this.veld = vd;
        this.uitTeam= ut;
        this.thuisTeam = tt;
    }

    public void addScore(int scorett, int scoreut) {
        scoreThuisTeam = scorett;
        scoreUitTeam = scoreut;

        String resultaat = "";

        //thuisteam
        standTeam.getStandTeam(thuisTeam);
        if (scoreThuisTeam > scoreUitTeam) {
            resultaat = "gewonnen";

        } if (scoreThuisTeam == scoreUitTeam) {
            resultaat = "gelijk";
        } if (scoreThuisTeam < scoreUitTeam) {
            resultaat = "verloren";
        }
        standTeam.setresultaten(resultaat, scorett, scoreut);

        //uitTeam
        standTeam.getStandTeam(uitTeam);
        if (scoreThuisTeam < scoreUitTeam) {
            resultaat = "gewonnen";

        } if (scoreThuisTeam == scoreUitTeam) {
            resultaat = "gelijk";
        } if (scoreThuisTeam > scoreUitTeam) {
            resultaat = "verloren";
        }
        standTeam.setresultaten(resultaat,scorett,scoreut);
    }









}
