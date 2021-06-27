package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Speler speler1;
    private Speler speler2;
    private Team team;
    private Club club1;


    @BeforeEach
    void Initialize() {
        speler1 = new Speler("Corné van Barneveld");
        speler2 = new Speler("Corné van Barneveld");
        team = new Team("jo14-3");
        club1 = new Club("testClub");

    }

    @Test
    void voegspelerToeEnGetSpelers() {

        team.VoegspelerToe(speler1);
        team.VoegspelerToe(speler2);
        team.VoegspelerToe(speler1);

        ArrayList<Speler> spelers = new ArrayList<Speler>();
        spelers.add(speler1);
        spelers.add(speler2);

        assertEquals(team.getSpelers(), spelers);

    }

    @Test
    void SetEnGetClub() {
        team.setClub(club1);

        assertEquals(team.getClub(), club1);
    }

    @Test
    void getNaam() {
        assertEquals(team.getNaam() , "jo14-3");
    }

    @Test
    void GetTeamOpNaamEnClub() {

        Team team1 = new Team("mannen-1");
        team1.setClub(club1);

        assertEquals(Team.getTeamOpNaamEnClub("mannen-1" , "testClub") , team1);
    }

    @Test
    void equalstest() {
        Team team1 = new Team("jo14-3");
        team1.setClub(club1);
        team.setClub(club1);

        assertTrue(team1.equals(team));
    }


}
