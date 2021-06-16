package nl.hu.ipass.corne.domeinlaag;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Speler;
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
    void addEnGetGeleKaart() {
        speler.addGeleKaart();
        assertEquals(speler.getGelKaart(), 1);
    }

    @Test
    void addEnGetRodeKaart() {
        speler.addRodeKaart();
        assertEquals(speler.getRodeKaart(), 1);
    }


}
