//Grace Swenson Hollis
//Febuary 18th 2022

import java.util.Scanner;
import java.io.*;

public class Runner 
{
    public static void main(String[] args) throws IOException {

        try (Scanner scan = new Scanner(System.in)) {
            String fileURL = "https://cs.brynmawr.edu/cs151/Data/Hw2/shuffzip.csv"; 
SortedArrayList<Place> placeList = new SortedArrayList<Place>(100);

ReadCSV file = new ReadCSV(fileURL); 
            String[] lineInfo; 

            while (file.hasNext()){
                lineInfo = file.getLine();
                //Case one: Population is known
                if (lineInfo[10].length() > 0){
                    PopulatedPlace popPlace = new PopulatedPlace(lineInfo[0].replace("\"", ""), lineInfo[2].replace("\"", ""), 
                                                lineInfo[3].replace("\"", ""), lineInfo[5], lineInfo[6], lineInfo[10]);
                    placeList.add(popPlace);
                }
                //Case two: Location is known
                else if (lineInfo[5].length() > 0){
                    LocatedPlace locPlace = new LocatedPlace(lineInfo[0].replace("\"", ""), lineInfo[2].replace("\"", ""), 
                                                lineInfo[3].replace("\"", ""), lineInfo[5], lineInfo[6]);
                    placeList.add(locPlace);
                }
                //Case three: Neither Population or Location is known
                else {
                    Place place = new Place(lineInfo[0].replace("\"", ""), lineInfo[2].replace("\"", ""), 
                                        lineInfo[3].replace("\"", ""));
                    placeList.add(place);
                }
            } 

            while (true){ 
                System.out.print("Zipcode (q to quit): "); 
                String inputZip = scan.next(); 
                if (inputZip.equals("q")) { 
                    System.out.println("Goodbye!"); 
                    break;
                }
                Place foundPlace = placeList.getInstance(new Place(inputZip, null, null));
                if (foundPlace != null){
                    System.out.println(foundPlace);
                } else {
                    System.out.println("Zip code not found!");
                }
            }
        } 
    } 
} 
