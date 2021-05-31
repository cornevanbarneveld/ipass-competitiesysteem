package nl.hu.ipass.corne.domeinlaag;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Speler;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Speler speler1;
    private Speler speler2;
    private Team team;

    @BeforeEach
    void Initialize() {
        speler1 = new Speler("Corné van Barneveld", "cornevb" ,"wachtwoord1" , "speler");
        speler2 = new Speler("Corné van Barneveld", "cornevb" ,"wachtwoord1" , "speler");
        team = new Team("jo14-3","jongens");
    }

    @Test
    void voegspelerToeMeerdereKeren() {

        team.VoegspelerToe(speler1);
        team.VoegspelerToe(speler2);
        team.VoegspelerToe(speler1);

        ArrayList<Speler> spelers = new ArrayList<Speler>();
        spelers.add(speler1);
        spelers.add(speler2);

        assertEquals(team.getSpelers(), spelers);

    }


}
