import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Pizzeria\n\n");
            for (int i = 1; i < 5; i++) {
                // Crear process
                ProcessBuilder p = new ProcessBuilder(
                        "curl",
                        "-H", "Accept: application/json",
                        "https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=" + i + "&pageSize=4"
                );

                // Start
                Process curl = p.start();

                // Leer la salida
                BufferedReader reader = new BufferedReader(new InputStreamReader(curl.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    String jsonString = line;

                    // Parsear JSON
                    Gson gson = new Gson();
                    JsonObject json = gson.fromJson(jsonString, JsonObject.class);  // <-- use jsonString

                    System.out.println(json);                // imprime todo el JSON
                    System.out.println(json.get("data"));    // imprime un campo específico
                    System.out.println("-------------------------------------------------------------------------------------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
