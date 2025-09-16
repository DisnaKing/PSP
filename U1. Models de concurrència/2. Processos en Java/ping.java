import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Ping {

    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<String> app=new ArrayList<String>();

        app.add("ping");
        app.add("-c");
        app.add("4");
        app.add("ieseljust.com");

        ProcessBuilder pb = new ProcessBuilder(app);
        Process p = pb.start();

        java.io.InputStream is = p.getInputStream();
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        String output = s.hasNext() ? s.next() : "";
        System.out.println("Output:\n" + output);
    }
}