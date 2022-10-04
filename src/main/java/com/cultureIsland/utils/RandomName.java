package com.cultureIsland.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class RandomName {
    private String name;

    public RandomName() throws IOException {
        Random random = new Random();
        String[] strArray = new String[508];
        String[] array = new String[35];
        BufferedReader br = new BufferedReader(new FileReader("web/file/surname"));
        String line;
        while ((line = br.readLine()) != null) {
            strArray = line.split(",");
        }
        String surname = strArray[random.nextInt(508)];
        BufferedReader be = new BufferedReader(new FileReader("web/file/name"));
        while ((line = be.readLine()) != null) {
            array = line.split(",");
        }
        this.name = surname + array[random.nextInt(35)];
    }

    public String getName() {
        return name;
    }
}
