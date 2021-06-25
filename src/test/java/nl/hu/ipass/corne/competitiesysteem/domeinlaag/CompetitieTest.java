package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompetitieTest {


    private Competitie competitie;
    private Team team;
    private Team team2;
    private Wedstrijd wedstrijd;



    @BeforeEach
    void Initialize() {
        competitie = new Competitie("beker" ,"2e" , 2020 , 2021 );
        team = new Team("jo16-2");
        team2 = new Team("jo16-1");
        wedstrijd = new Wedstrijd(LocalDateTime.of(2021,4,4,10,30 ),3 ,team , team2);
        wedstrijd.setCompetitie(competitie);


    }
    @Test
    void addTeamTestEnGetTeamTest() {
        competitie.AddTeam(team);
        competitie.AddTeam(team2);

        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(team);
        teams.add(team2);

        assertEquals(competitie.getTeams(), teams);
    }

    @Test
    void voegWedstrijdToeTestEnGetWedstrijdenTest() {
        ArrayList<Wedstrijd> wedstrijden  = new ArrayList<Wedstrijd>();
        wedstrijden.add(wedstrijd);

        assertEquals(competitie.getAlleWedstrijden(), wedstrijden);
    }
}