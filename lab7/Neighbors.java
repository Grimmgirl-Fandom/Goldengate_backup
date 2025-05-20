import java.util.ArrayList; // import the ArrayList class


public class Neighbors{

public ArrayList<String> neighbor;

public Neighbors(){
   neighbor = new ArrayList<String>(); // Create an ArrayList object
}

public int howManyNeighbors(){
    return neighbor.size();
}

public void newNeighbor(String ne){
    neighbor.add(ne);
}

public void print(){
    for(int n =0; n<howManyNeighbors();n++){
        System.out.println(neighbor.get(n));
    }
}

public ArrayList<String> asList(){
    return neighbor;
}


    
}
