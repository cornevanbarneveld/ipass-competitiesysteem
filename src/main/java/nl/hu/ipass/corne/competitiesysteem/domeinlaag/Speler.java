package nl.hu.ipass.corne.competitiesysteem.domeinlaag;

public class Speler extends Gebruiker {
    private String naam;
    private int gelKaarten;
    private int rodeKaarten;


    public Speler(String nm , String gnm, String ww, String tp){
        super(gnm,ww,tp);
        this.naam = nm;
    }

    public String getNaam() {
        return naam;
    }

    public void addGeleKaart() { gelKaarten += 1; }

    public void addRodeKaart() { rodeKaarten += 1; }

    public int getGelKaart(){
        return gelKaarten;
    }

    public int getRodeKaart() {
        return rodeKaarten;
    }
}
