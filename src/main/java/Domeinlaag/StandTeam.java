package Domeinlaag;

public class StandTeam {


    private Team team;
    private int punten;
    private int gespeeld;
    private int gewonnen;
    private int gelijk;
    private int verloren;
    private int doelpuntenVoor;
    private int doelpuntenTegen;
    private int doelsaldo;

    public StandTeam(Team tm){
        this.team = tm;
    }

    public void setresultaten(String uitslag, int doelpuntentv, int doelpuntentt) {

        doelpuntenVoor += doelpuntentv;
        doelpuntenTegen += doelpuntentt;
        doelsaldo = doelpuntenVoor - doelpuntenTegen;
        gespeeld += 1;

        if (uitslag.equals("gewonnen")) {
            punten += 3;
            gewonnen += 1;
        } if (uitslag.equals("gelijk")) {
            punten += 1;
            gelijk += 1;
        } if (uitslag.equals("verloren")) {
            verloren += 1;
        }

    }

    public StandTeam getStandTeam(Team t) {
        return this;
    }

    public Team getTeam() {
        return team;
    }
}
