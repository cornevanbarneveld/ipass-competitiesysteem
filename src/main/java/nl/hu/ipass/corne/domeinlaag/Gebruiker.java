package nl.hu.ipass.corne.domeinlaag;

public class Gebruiker {
    private String gebruikersnaam;
    private String wachtwoord;
    private String type;


    public Gebruiker(String gnm, String ww, String tp) {
        this.gebruikersnaam = gnm;
        this.wachtwoord = ww;
        this.type = tp;
    }


}
