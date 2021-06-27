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
        Club club = new Club("testclub");
        team = new Team("jo16-2");
        team2 = new Team("jo16-1");
        club.voegTeamToe(team);
        club.voegTeamToe(team2);
        wedstrijd = new Wedstrijd(LocalDateTime.of(2021,4,4,10,30 ),3 ,team , team2);
        competitie.AddWedstrijd(wedstrijd);


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

    @Test
    void GetEindJaarEnGetBeginJaar() {

        assertEquals(2020, competitie.getBeginjaar());
        assertEquals(2021, competitie.getEindjaar());
    }

    @Test
    void Getnaamtest() {
        assertEquals("beker" , competitie.getNaam());
    }


    @Test
    void GetCompetitieVoorTeam() {

        competitie.AddTeam(team);
        competitie.AddTeam(team2);

        ArrayList<Competitie> comps = new ArrayList<Competitie>();
        comps.add(competitie);

        assertEquals(comps, Competitie.getCompetitiesVoorTeam(team));
    }

    @Test
    void eQuals() {
        Competitie c2 = new Competitie("beker" ,"2e" , 2020 , 2021 );

        assertFalse(competitie.equals(c2));
    }
}
