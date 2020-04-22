package edu.depaul.dennysdvds;

//import com.opencsv.*;
//import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class DennysDVDs {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello denny! Part 2! Checking here");

        System.out.println(System.getProperty("user.dir"));

        File file = new File("files/Users.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }

//        CSVReader reader = new CSVReader();
//
//        List<String[]> lines = reader.readAll();
//
//        for(String[] ele : lines){
//            System.out.println(ele);
//        }



    }

    public int getFive(){
        return 5;
    }
}
