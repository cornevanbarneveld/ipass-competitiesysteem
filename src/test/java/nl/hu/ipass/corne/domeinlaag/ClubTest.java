package nl.hu.ipass.corne.domeinlaag;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
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


}
