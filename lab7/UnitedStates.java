import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;  // Import the Scanner class
import java.util.Hashtable;
import java.util.ArrayList; // import the ArrayList class




public class UnitedStates{

    public Hashtable<String, Neighbors> theStates;
    public String mostNi;//state with the most neighbors.

    public UnitedStates(){
        theStates = new Hashtable<String, Neighbors>();
        doit();
    }

    private void doit(){
        String hol = "";

        ReadCSV ec;
        try {
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
            int num =0;
            while (ec.hasNext()) 
            {
                //the neighbores of this state
                Neighbors myNeighbor = new Neighbors();
                String[] ss = ec.getLine();//reads this line from file
                String holderState = ss[0];//this line is the state
                int count = 0;

                for (int i = 1; i<ss.length;i++)//neighbors filling up
                {
                    String nene = ss[i];
                    myNeighbor.newNeighbor(nene);
                    count++;
                }
                theStates.put(holderState,myNeighbor);

                if(count>num)
                {
                    num=count;
                    hol = holderState;
                }

                }
                
            }
            mostNiSet(hol);

        }
           catch (IOException ioe) {
            System.err.println("Problem while reading file " + ioe);
            return;
}}

//print neighbors
    public void printN(String key){
        Neighbors holdMyNeigh=theStates.get(key);
        holdMyNeigh.print();

    }

    private void mostNiSet(String name){
        mostNi = name;
    }

    public boolean realState(String key){
        return theStates.containsKey(key);
    }
    
    public void MostBuddies(){
        System.out.println(mostNi+" has the most neighbors");
    }

    public int hmn(String key){

        Neighbors holdMyNeigh=theStates.get(key);
        return holdMyNeigh.howManyNeighbors();

    }

//this method should only be used with objects in the UnitedStates/not user input
public boolean isWomen(String stateName){
char leading = stateName.charAt(0);
if(leading =='W'||leading=='O'||leading=='M'||leading=='A'||leading=='N')
    return true;
else return false;

}

public ArrayList<String> validNeighbor(String aState){
    
     Neighbors hold= theStates.get(aState);
    ArrayList<String> vn = hold.asList();
    
    int i = 0;
        while(i<vn.size()){
    //if its true, keep it and move on,otherwise get rid of it
    if(isWomen(vn.get(i))==true)
        i++;
    else
    vn.remove(i);
        }

    return vn;
}

     public static void main(String[] args) 
   {

    UnitedStates us = new UnitedStates();
    Scanner keyboard = new Scanner(System.in);

    //part 1 part 1
    us.MostBuddies();

        while(true)
       {
       System.out.print("Enter a State or (enter 'STOP' to quit):" );
        String nextState = keyboard.nextLine();
        String stop = "STOP";
        if(nextState.equals(stop))
            break;
      else
      {
        if(us.realState(nextState)==false)
            System.out.println("State not found");
        else if(us.hmn(nextState)==0)
            System.out.println(nextState + " has no neighbors");
        else{
            System.out.println(nextState +" has the following neighbors:");
            us.printN(nextState);
        }
      }

   }
   System.exit(0);

}
}
