package Domeinlaag;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class ClubTest extends TestCase {

    private Club club;
    private Team team;



    @BeforeEach
    public void aanmaak() {
        club = new Club("FCclub");
        team = new Team("jo15-4");


    }


    @Test
    public void ClubtestVoegTeamToe() {

        club.voegTeamToe(team);

        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(team);

        assertEquals(club.getTeam(), teams);

    }



}
