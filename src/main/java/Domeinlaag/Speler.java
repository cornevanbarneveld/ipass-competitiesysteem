package Domeinlaag;

public class Speler extends Gebruiker {
    private String naam;
    private int gelKaart;
    private int rodeKaart;

    public Speler(String nm , String gnm, String ww, String tp){
        super(gnm,ww,tp);
        this.naam = nm;
    }

    public void addGeleKaart() {
        gelKaart += 1;
    }

    public void addRodeKaart() {
        rodeKaart += 1;
    }

    public int getGelKaart(){
        return gelKaart;
    }

    public int getRodeKaart() {
        return rodeKaart;
    }
}
