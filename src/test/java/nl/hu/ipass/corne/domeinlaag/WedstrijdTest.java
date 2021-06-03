package nl.hu.ipass.corne.domeinlaag;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Competitie;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Speler;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Wedstrijd;
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

    @BeforeEach
    void Initialize() {
        competitie = new Competitie("2020-2021", "2e");
        team1 = new Team("jo12-3");
        team2 = new Team("jo12-2");
        wedstrijd = new Wedstrijd(LocalDateTime.of(2021,4,4,10,30 ),3 ,team1 , team2 , competitie );
        speler1 = new Speler("Corné van Barneveld", "cornevb" ,"wachtwoord1" , "speler" );
        speler2 = new Speler("naam", "voorbeeldnaam" ,"wachtwoord2" , "speler" );
    }



    @Test
    void voegscoreToe() {
        wedstrijd.setScoreThuisTeam(1);
        wedstrijd.setScoreUitTeam(3);

        assertEquals(wedstrijd.getScoreThuisTeam(), 1);
        assertEquals(wedstrijd.getScoreUitTeam(), 3);


    }

    @Test
    void setBoekingSpelerTestFouteinvoer() {

        wedstrijd.setBoekingSpeler(speler1 , "foute invoer");
        assertFalse(wedstrijd.getGeboekteSpelers().containsKey(speler1));


    }

    @Test
    void setBoekingenSpeltest2eKeer() {
        wedstrijd.setBoekingSpeler(speler1, "geel");
        wedstrijd.setBoekingSpeler(speler1, "rood" );

        Map<Speler , String > spelersboekingen = new HashMap<Speler, String>();

        spelersboekingen.put(speler1, "geel");

        assertEquals(wedstrijd.getGeboekteSpelers(), spelersboekingen);

    }
}
