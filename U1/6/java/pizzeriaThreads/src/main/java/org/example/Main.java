package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

public class Main  {
    static ArrayList<String> pizzes = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Crear Objecte Runnable
            fil f1 = new fil(1);
            fil f2 = new fil(2);
            fil f3 = new fil(3);
            fil f4 = new fil(4);

            // Crear fils
            Thread t1 = new Thread(f1);
            Thread t2 = new Thread(f2);
            Thread t3 = new Thread(f3);
            Thread t4 = new Thread(f4);

            // Executar fils
            t1.start();
            t2.start();
            t3.start();
            t4.start();


            // Unim els fils
            t1.join();

            System.out.println("Pizzeria\n"+Main.pizzes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    }

