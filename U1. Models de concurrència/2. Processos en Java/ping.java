import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Ping2 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("ping", "-c", "4", "ieseljust.com");
        try {
            Process p = pb.start();

            // Obtenim l'eixida del procés amb getInputStream,
            // i la bolquem per pantalla, passant-la per un InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            BufferedWriter bw = new BufferedWriter(new FileWriter("archivo.txt"));

            // Llegim línia a línia fins a null
            while ((line = br.readLine()) != null) {
                bw.write(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}