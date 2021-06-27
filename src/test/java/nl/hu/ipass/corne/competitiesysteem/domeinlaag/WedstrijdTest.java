package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WedstrijdTest {

    private Team team1;
    private Team team2;
    private Wedstrijd wedstrijd;
    private Speler speler1;
    private Speler speler2;
    private Competitie competitie;
    private Club club;

    @BeforeEach
    void Initialize() {
        competitie = new Competitie("2020-2021", "2e" , 2020 , 2021);
        team1 = new Team("jo12-3");
        team2 = new Team("jo12-2");
        wedstrijd = new Wedstrijd(LocalDateTime.of(2021,4,4,10,30 ),3 ,team1 , team2 );
        wedstrijd.setCompetitie(competitie);
        speler1 = new Speler("Corné van Barneveld" );
        speler2 = new Speler("naam");
        club = new Club("club1");
    }



    @Test
    void voegscoreToeGetScore() {
        wedstrijd.setScoreThuisTeam(1);
        wedstrijd.setScoreUitTeam(3);

        assertEquals(wedstrijd.getScoreThuisTeam(), 1);
        assertEquals(wedstrijd.getScoreUitTeam(), 3);


    }

    @Test
    void GetEnSetGespeeld() {
        wedstrijd.setScoreThuisTeam(1);
        wedstrijd.setScoreUitTeam(3);
        wedstrijd.setGespeeld();

        assertTrue(wedstrijd.getGespeeld());

    }


    @Test
    void GetEnVervangDatum() {

        LocalDateTime locd = LocalDateTime.of(2021,4,4,10,30 );
        wedstrijd.verVangDatum(locd);

        assertEquals(locd, wedstrijd.getDatum());

    }

    @Test
    void equals() {

        Wedstrijd wedstrijd1 = new Wedstrijd(LocalDateTime.of(2021,4,4,10,30 ),3 ,team1 , team2 );
        Wedstrijd wedstrijd2 = new Wedstrijd(LocalDateTime.of(2021,4,4,10,30 ),3 ,team1 , team2 );


        team1.setClub(club);
        team2.setClub(club);

        if (wedstrijd1.getDatum() == wedstrijd.getDatum()) {
            System.out.println("aaaaa");
        }

        assertTrue(wedstrijd2.equals(wedstrijd1));
    }




}
