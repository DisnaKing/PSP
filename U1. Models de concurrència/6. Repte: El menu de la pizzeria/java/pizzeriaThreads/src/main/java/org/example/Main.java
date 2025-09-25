package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;


public class Main {
    public static void main(String[] args) {

        try {
            // Create processes
            ProcessBuilder p1 = new ProcessBuilder(
                    "curl",
                    "-s", "-H", "Accept: application/json",
                    "https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=1&pageSize=4"
            );

            ProcessBuilder p2 = new ProcessBuilder(
                    "curl",
                    "-s", "-H", "Accept: application/json",
                    "https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=2&pageSize=4"
            );

            ProcessBuilder p3 = new ProcessBuilder(
                    "curl",
                    "-s", "-H", "Accept: application/json",
                    "https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=3&pageSize=4"
            );

            ProcessBuilder p4 = new ProcessBuilder(
                    "curl",
                    "-s", "-H", "Accept: application/json",
                    "https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes?pageNumber=4&pageSize=4"
            );

            // Start processes
            Process curl1 = p1.start();
            Process curl2 = p2.start();
            Process curl3 = p3.start();
            Process curl4 = p4.start();

            // Read each process output
            System.out.println("Pizzeria");
            System.out.println("-------------------------------------------------------------------------------------------------------");
            handleProcess(curl1, 1);
            handleProcess(curl2, 2);
            handleProcess(curl3, 3);
            handleProcess(curl4, 4);
            System.out.println("-------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to handle reading and parsing
    private static void handleProcess (Process process,int pageNumber){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

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

                System.out.println(name + " | Preu: $" + price);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}