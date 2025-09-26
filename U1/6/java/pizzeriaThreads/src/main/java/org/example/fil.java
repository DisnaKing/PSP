package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class fil implements Runnable{

    int numpag;

    fil(int numpag){
        this.numpag = numpag;
    }

    @Override
    public void run() {
        // Create processes
        ProcessBuilder p = new ProcessBuilder(
                "curl",
                "-s", "-H", "Accept: application/json",
                "https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber="+ this.numpag +"&pageSize=4"
        );
        try {
            Process curl = p.start();try (BufferedReader reader = new BufferedReader(new InputStreamReader(curl.getInputStream()))) {

                // Read full JSON response
                String jsonString = reader.lines().collect(Collectors.joining());

                Gson gson = new Gson();
                JsonObject json = gson.fromJson(jsonString, JsonObject.class);

                // Get the "records" array
                JsonArray records = json.getAsJsonArray("records");


                for (JsonElement element : records) {
                    JsonObject pizza = element.getAsJsonObject();
                    String name = pizza.get("nom").getAsString();
                    double price = pizza.get("preu").getAsDouble();
                    Main.pizzes.add(name + " | Preu: $" + price + "\n");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
