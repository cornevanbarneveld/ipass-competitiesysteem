package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

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
        speler1 = new Speler("Corné van Barneveld");
        speler2 = new Speler("Corné van Barneveld");
        team = new Team("jo14-3");
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