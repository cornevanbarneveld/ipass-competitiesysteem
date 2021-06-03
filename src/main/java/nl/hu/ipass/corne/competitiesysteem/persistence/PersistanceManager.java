package nl.hu.ipass.corne.competitiesysteem.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Gebruiker;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;

import static java.lang.System.out;
import java.io.*;
import java.util.ArrayList;

public class PersistanceManager {
    private static final String ENDPOINT = "https://bepoplsagco.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2020-02-10&ss=bfqt&srt=sco&sp=rwdlacupx&se=2021-06-04T01:11:34Z&st=2021-06-03T17:11:34Z&spr=https&sig=QxMsBw4q2j7GYedTLZf23bqb6mbUwqlcWwcmZ%2FjInmE%3D";
    private static final String CONTAINER = "container3";





    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();

    private static ArrayList<Gebruiker> geladenGebruikers = new ArrayList<Gebruiker>();
    private static ArrayList<Team> geladenTeams = new ArrayList<Team>();




    private PersistanceManager() {
    }

    public static void loadWorldFromAzure() throws IOException, ClassNotFoundException {


        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("blobgebruiker");



            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);






                ArrayList<Gebruiker> gebruikers = (ArrayList<Gebruiker>)ois.readObject();
                geladenGebruikers.addAll(gebruikers);

                for(Gebruiker g: geladenGebruikers){
                    g.addGebruiker(g);

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



        BlobClient blob = blobContainer.getBlobClient("blobgebruiker");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);



        ArrayList<Gebruiker> opslaangebruikers = Gebruiker.getAlleGebruikers();

        for (Gebruiker g: opslaangebruikers) {
            for (Gebruiker gebr: geladenGebruikers) {
                if(g.getGebruikersnaam().equals(gebr.getGebruikersnaam())){
                    opslaangebruikers.remove(g);

                }

            }



        }

        if (opslaangebruikers != null) {
            oos.writeObject(opslaangebruikers);
        }







        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        oos.close();
        bais.close();
    }

}
