package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClubTest {

    private Club club;
    private Team team;


    @BeforeEach
    void Initialize() {
        club = new Club("fcclub");
        team = new Team("team");
    }


    @Test
    void testVoegTeamToeEnGetTeams() {


        club.voegTeamToe(team);

        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(team);

        assertEquals(club.getTeams(), teams);
    }

    @Test
    void getNaamTest() {

        assertEquals("fcclub" , club.Getnaam());
    }

    @Test
    void testEquals() {

        Club club1 = new Club("fcclub");

        assertTrue(club1.equals(club));
    }


}
