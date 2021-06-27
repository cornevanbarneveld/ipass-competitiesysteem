package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpelerTest {

    private Speler speler;

    @BeforeEach
    void Intialize() {

        speler = new Speler("Corné van Barneveld" );

    }

    @Test
    void getNaamtest() {
        assertEquals("Corné van Barneveld" , speler.getNaam());

    }

    @Test
    void GetEnSetTeam() {
        Club club1 = new Club("testclubje");
        Team team1 = new Team("jongens-19-1");
        club1.voegTeamToe(team1);
        speler.setTeam(team1);

        assertEquals(team1, speler.getTeam());


    }



}
