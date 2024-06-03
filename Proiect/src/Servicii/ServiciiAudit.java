package Servicii;
import java.io.*;
import java.time.Instant;
import java.sql.Timestamp;

public class ServiciiAudit { //singleton

    private static ServiciiAudit sg_instance = null;
    private BufferedWriter buffer;

    private ServiciiAudit() {
        try{
            String path = "C:\\Users\\nicol\\Desktop\\Proiect\\Files\\Audit.csv";

            //sterge continut dinaintea pornirii programul
            new FileWriter(path, false).close();

            buffer = new BufferedWriter(new FileWriter(path, true));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ServiciiAudit getInstance() {
        if (sg_instance == null)
            sg_instance = new ServiciiAudit();
        return sg_instance;
    }

    public void WriteTimeStamps(String text){

        try{
            Timestamp instant = Timestamp.from(Instant.now());

            buffer.write(text+ "," + instant + "\n");
            buffer.flush();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
