package nl.hu.ipass.corne.competitiesysteem.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Gebruiker;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Speler;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class PersistanceSpelers {
    private static final String ENDPOINT = "https://bepoplsagco.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2020-02-10&ss=bfqt&srt=sco&sp=rwdlacupx&se=2022-06-04T15:24:48Z&st=2021-06-04T07:24:48Z&spr=https&sig=iEqjlK50CD1vFBcLKKUmMfQ3Dd%2F9NDc7okoPanp%2FPhU%3D";
    private static final String CONTAINER = "container3";


    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();

    private static ArrayList<Speler> geladenSpelers = new ArrayList<Speler>();


    private PersistanceSpelers() {
    }

    public static void loadWorldFromAzure() throws IOException, ClassNotFoundException {


        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("blobspelers9");

            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                ArrayList<Speler> spelers = (ArrayList<Speler>)ois.readObject();
                geladenSpelers.addAll(spelers);

                for(Speler s: geladenSpelers){
                    out.println(s.getNaam());
                    s.addSpeler(s);

                }

                for(Speler s: geladenSpelers){
                    s.addSpeler(s);

                }


                baos.close();
                ois.close();
            } else throw new IllegalStateException("container not found, loading default data");
        }
    }

    public static void saveWorldToAzure() throws IOException {

        if (!blobContainer.exists()) {
            blobContainer.create();
        }

        BlobClient blob = blobContainer.getBlobClient("blobspelers9");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);



        ArrayList<Speler> opslaanspelers = new ArrayList<Speler>(Speler.getSpelers());
        out.println(Speler.getSpelers());
        out.println(opslaanspelers);


        for (Speler s: opslaanspelers) {
            for (Speler speler: geladenSpelers) {
                if(speler.getSpelernummer() == s.getSpelernummer()){
                    opslaanspelers.remove(s);
                }
            }
        }
        if (opslaanspelers != null) {
            out.println(opslaanspelers);
            oos.writeObject(opslaanspelers);
        }

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        oos.close();
        bais.close();
    }

}
