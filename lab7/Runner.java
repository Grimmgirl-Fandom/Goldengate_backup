import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;


public class Runner{
     public static void main(String[] args) 
    {

        State[] us = new State[51];
        

        ReadCSV ec;
        try {
            // get a new instance of the class -- through a static maker.
            ec = new ReadCSV("/home/gtowell/Public/337/USStates.csv");
        }
        catch (IOException ioe) {
            System.err.println("Ending. Cannot read. " + ioe.toString());
            return;
        }

        try {{
            int lineCount = -1;
            //get ride of first//discription line
            String[] discard = ec.getLine();
            while (ec.hasNext()) 
            {
                Neighbors myNeighbor = new Neighbors();
                lineCount ++;
                String[] ss = ec.getLine();
                String sta = ss[0];
                us[lineCount]= new State(sta);
                for (int i = 1; i<ss.length;i++)
                {
                    String nene = ss[i];
                    State stay = new State(nene);
                    myNeighbor.newNeighbor(stay);
                }
                }
                
            }
        }
           catch (IOException ioe) {
            System.err.println("Problem while reading file " + ioe);
            return;
        }}
}
