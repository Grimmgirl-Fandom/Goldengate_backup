import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;
import java.util.Hashtable;


public class CreateSymbolTable{

    public ParseLine pl;
    public ParseFunctionHeader pfh;
    public ErrorChecker ec;

    public CreateSymbolTable(){
        pl= new ParseLine();
        pfh= new ParseFunctionHeader();
        ec = new ErrorChecker();

    }

    public boolean allGood(ArrayList<String> stuff){
        return ec.allTests(stuff);
    }

    //populate symbol table
    public Hashtable<String,Integer> PST(String fileName){

        GGReader reader = new GGReader();
        ArrayList<String> content = reader.readFile(fileName);
        Hashtable<String,Integer> table = new Hashtable<String,Integer>();
        int offset = 4;
       ArrayList<String> params = pfh.parseHeaderList(content.get(0));
            for(int i = 0;i<params.size();i++){
                table.put(params.get(i),offset);
                offset++;
            }
       offset = 0;
        for(int i = 1;i<content.size();i++){
            String line = pl.GetParsedLine(content.get(i));
            if(line.equals("")){}
             else {
                table.put(line,offset);
                offset--;
             }
        }


    return table;

    }

    public void printTable(Hashtable<String,Integer> table){
        ArrayList<String> list = order(table);
        for(int i =0;i<list.size();i++){
            System.out.println(list.get(i)+" "+table.get(list.get(i)));
        }
    }
    private ArrayList<String> order(Hashtable<String,Integer> table){
        Hashtable<Integer,String> flipped = flip(table);
    
        ArrayList<String> ret = new ArrayList<String>();

        int offset = 4;

        //param
        while(flipped.containsKey(offset)){
            ret.add(flipped.get(offset));
            offset++;
        }
        
        offset =0;
        //local
          while(flipped.containsKey(offset)){
            ret.add(flipped.get(offset));
            offset--;}
        return ret;

    }



        //populate symbol table
    public Hashtable<String,Integer> rePopulate(ArrayList<String> content){
        Hashtable<String,Integer> table = new Hashtable<String,Integer>();
        int offset = 4;
       ArrayList<String> params = pfh.parseHeaderList(content.get(0));
            for(int i = 0;i<params.size();i++){
                table.put(params.get(i),offset);
                offset++;
            }
       offset = 0;
        for(int i = 1;i<content.size();i++){
            String line = pl.GetParsedLine(content.get(i));
            if(line.equals("")){}
             else {
                table.put(line,offset);
                offset--;
             }
        }


    return table;

    }

    private Hashtable<Integer,String> flip(Hashtable<String,Integer> table){
        Hashtable<Integer,String> flipper = new Hashtable<Integer,String>();
        Object[] keysOb = table.keySet().toArray();

        String key ="";
        int val;
        for(int i = 0;i<keysOb.length;i++){
            key = ""+keysOb[i];
            val = table.get(key);
            flipper.put(val,key);
        }


        return flipper;

    }

     public static void main(String[] args) {
        CreateSymbolTable runner = new CreateSymbolTable();
        Scanner keyboard = new Scanner(System.in); 
        System.out.println("Enter file name");
        String file = keyboard.nextLine(); 

        GGReader r = new GGReader();
        ArrayList<String> contents = r.readFile(file);

        if(runner.allGood(contents)==true){
        Hashtable<String,Integer> t = runner.PST(file);
       runner.printTable(t);    
        }
 }
}
