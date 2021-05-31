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
    private static final String ENDPOINT = "https://ipasscorne.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2020-02-10&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-06-01T03:25:46Z&st=2021-05-31T19:25:46Z&spr=https&sig=V8ET34Qgx6YS6qvcCrev32d5fckrs7F1E5H2vpZpGVo%3D";
    private static final String CONTAINER = "containeripass";



    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();


    private PersistanceManager() {
    }

    public static void loadWorldFromAzure() throws IOException, ClassNotFoundException {


        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("blob2");



            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);


                //Gebruiker loadedGebruiker = (Gebruiker)ois.readObject();
                //loadedGebruiker.addGebruiker(loadedGebruiker);






                baos.close();
                ois.close();
            } else throw new IllegalStateException("container not found, loading default data");
        }
    }

    public static void saveWorldToAzure() throws IOException {



        if (!blobContainer.exists()) {
            blobContainer.create();
        }



        BlobClient blob = blobContainer.getBlobClient("blob2");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);





        oos.writeObject(Team.getAlleTeams());





        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        oos.close();
        bais.close();
    }

}
